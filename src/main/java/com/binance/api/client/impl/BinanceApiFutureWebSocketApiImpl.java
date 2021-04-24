package com.binance.api.client.impl;

import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiFutureWebSocketApi;
import com.binance.api.client.config.BinanceApiConfig;
import com.binance.api.client.domain.event.CandlestickEvent;
import com.binance.api.client.domain.market.CandlestickInterval;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BinanceApiFutureWebSocketApiImpl implements BinanceApiFutureWebSocketApi {
    private final OkHttpClient client;

    public BinanceApiFutureWebSocketApiImpl(OkHttpClient client){
        this.client = client;
    }

    @Override
    public Closeable onCandlestickEvent(String symbols, CandlestickInterval interval, BinanceApiCallback<CandlestickEvent> callback) {
        final String channel = Arrays.stream(symbols.split(","))
                .map(String::trim)
                .map(s -> String.format("%s@kline_%s", s, interval.getIntervalId()))
                .collect(Collectors.joining("/"));
        return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, CandlestickEvent.class));
    }

    /**
     * @deprecated This method is no longer functional. Please use the returned {@link Closeable} from any of the other methods to close the web socket.
     */
    @Override
    public void close() throws IOException {

    }

    private Closeable createNewWebSocket(String channel, BinanceApiWebSocketListener<?> listener) {
        String streamingUrl = String.format("%s/%s", BinanceApiConfig.getFutureStreamApiBaseUrl(), channel);
        Request request = new Request.Builder().url(streamingUrl).build();
        final WebSocket webSocket = client.newWebSocket(request, listener);
        return () -> {
            final int code = 1000;
            listener.onClosing(webSocket, code, null);
            webSocket.close(code, null);
            listener.onClosed(webSocket, code, null);
        };
    }
}
