package com.binance.api.client.impl;

import com.binance.api.client.BinanceApiFutureRestClient;
import com.binance.api.client.config.BinanceApiConfig;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.account.NewFutureOrder;
import com.binance.api.client.domain.account.NewFutureOrderResponse;
import com.binance.api.client.domain.event.ListenKey;
import com.binance.api.client.domain.general.ExchangeFutureInfo;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;

import java.util.List;

import static com.binance.api.client.impl.BinanceApiServiceGenerator.createService;
import static com.binance.api.client.impl.BinanceApiServiceGenerator.executeSync;

public class BinanceApiFutureRestClientImpl implements BinanceApiFutureRestClient {
    private final BinanceApiFutureService binanceApiFutureService;

    public BinanceApiFutureRestClientImpl(String apiKey, String secret){
        this(apiKey, secret, BinanceApiConfig.getFutureApiBaseUrl());
    }

    public BinanceApiFutureRestClientImpl(String apiKey, String secret, String baseUrl){
        binanceApiFutureService = createService(BinanceApiFutureService.class, apiKey, secret, baseUrl);
    }

    @Override
    public void ping() {
        executeSync(binanceApiFutureService.ping());
    }

    @Override
    public ExchangeFutureInfo getExchangeInfo() {
        return executeSync(binanceApiFutureService.getExchangeInfo());
    }

    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit, Long startTime, Long endTime) {
        return executeSync(binanceApiFutureService.getCandlestickBars(symbol, interval.getIntervalId(), limit, startTime, endTime));
    }

    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit) {
        return getCandlestickBars(symbol, interval, limit, null, null);
    }

    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval) {
        return getCandlestickBars(symbol, interval, null, null, null);
    }

    @Override
    public TickerStatistics get24HrPriceStatistics(String symbol) {
        return executeSync(binanceApiFutureService.get24HrPriceStatistics(symbol));
    }

    @Override
    public List<TickerStatistics> getAll24HrPriceStatistics() {
        return executeSync(binanceApiFutureService.getAll24HrPriceStatistics());
    }

    @Override
    public TickerPrice getPrice(String symbol) {
        return executeSync(binanceApiFutureService.getLatestPrice(symbol));
    }

    @Override
    public List<TickerPrice> getAllPrices() {
        return executeSync(binanceApiFutureService.getAllLatestPrices());
    }

    @Override
    public ListenKey startUserDataStream() {
        return executeSync(binanceApiFutureService.startUserDataStream());
    }

    @Override
    public void keepAliveUserDataStream() {
        executeSync(binanceApiFutureService.keepAliveUserDataStream());
    }

    @Override
    public void closeUserDataStream() {
        executeSync(binanceApiFutureService.closeAliveUserDataStream());
    }

    @Override
    public List<NewFutureOrderResponse> openLimitLongPosition(String symbol, String quantity, String price, String takeProfitPrice, String stopLossPrice) {
        return executeSync(binanceApiFutureService.createBatchOrders(
                NewFutureOrder.openLongPositionString(symbol, quantity, price, takeProfitPrice, stopLossPrice),
                BinanceApiConstants.DEFAULT_RECEIVING_WINDOW,
                System.currentTimeMillis()));
    }

    @Override
    public List<NewFutureOrderResponse> openLimitShortPosition(String symbol, String quantity, String price, String takeProfitPrice, String stopLossPrice) {
        return executeSync(binanceApiFutureService.createBatchOrders(
                NewFutureOrder.openShortPositionString(symbol, quantity, price, takeProfitPrice, stopLossPrice),
                BinanceApiConstants.DEFAULT_RECEIVING_WINDOW,
                System.currentTimeMillis()));
    }


}
