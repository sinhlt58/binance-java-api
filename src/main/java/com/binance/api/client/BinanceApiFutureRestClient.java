package com.binance.api.client;

import com.binance.api.client.domain.account.NewFutureOrder;
import com.binance.api.client.domain.account.NewFutureOrderResponse;
import com.binance.api.client.domain.event.ListenKey;
import com.binance.api.client.domain.general.ExchangeFutureInfo;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;

import java.util.List;

public interface BinanceApiFutureRestClient {
    /**
     * Test connectivity to the Rest API.
     */
    void ping();

    /**
     * @return Current exchange trading rules and symbol information
     */
    ExchangeFutureInfo getExchangeInfo();

    /**
     * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
     *
     * @param symbol symbol to aggregate (mandatory)
     * @param interval candlestick interval (mandatory)
     * @param limit Default 500; max 1000 (optional)
     * @param startTime Timestamp in ms to get candlestick bars from INCLUSIVE (optional).
     * @param endTime Timestamp in ms to get candlestick bars until INCLUSIVE (optional).
     * @return a candlestick bar for the given symbol and interval
     */
    List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit, Long startTime, Long endTime);

    /**
     * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
     *
     * @param symbol symbol to aggregate (mandatory)
     * @param interval candlestick interval (mandatory)
     * @param limit Default 500; max 1000 (optional)
     * @return a candlestick bar for the given symbol and interval
     */
    List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit);

    /**
     * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
     *
     * @see #getCandlestickBars(String, CandlestickInterval, Integer, Long, Long)
     */
    List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval);

    /**
     * Get 24 hour price change statistics.
     *
     * @param symbol ticker symbol (e.g. ETHBTC)
     */
    TickerStatistics get24HrPriceStatistics(String symbol);

    /**
     * Get 24 hour price change statistics for all symbols.
     */
    List<TickerStatistics> getAll24HrPriceStatistics();

    /**
     * Get Latest price for all symbols.
     */
    TickerPrice getPrice(String symbol);

    /**
     * Get Latest price for all symbols.
     */
    List<TickerPrice> getAllPrices();

    // User stream endpoints

    /**
     * Start a new user data stream.
     *
     * @return a listen key that can be used with data streams
     */
    ListenKey startUserDataStream();

    /**
     * PING a user data stream to prevent a time out.
     *
     */
    void keepAliveUserDataStream();

    /**
     * Close out a new user data stream.
     *
     */
    void closeUserDataStream();

    // Orders, positions API
    List<NewFutureOrderResponse> openLimitLongPosition(String symbol, String quantity, String price, String stopLossPrice, String takeProfitPrice);
    List<NewFutureOrderResponse> openLimitShortPosition(String symbol, String quantity, String price, String stopLossPrice, String takeProfitPrice);
}
