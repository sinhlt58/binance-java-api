package com.binance.api.client.impl;

import com.binance.api.client.BinanceApiFutureRestClient;
import com.binance.api.client.config.BinanceApiConfig;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.ChangeLeverageResponse;
import com.binance.api.client.domain.account.CountDownResponse;
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
    public NewFutureOrderResponse cancelOrder(String symbol, String origClientOrderId) {
        return executeSync(binanceApiFutureService.cancelOrder(symbol, origClientOrderId, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
    }

    @Override
    public CountDownResponse setOrderCountdownCancelAll(String symbol, Long countdownTime) {
        return executeSync(binanceApiFutureService.setOrderCountdownCancelAll(
                symbol,
                countdownTime,
                BinanceApiConstants.DEFAULT_RECEIVING_WINDOW,
                System.currentTimeMillis()
        ));
    }

    public NewFutureOrderResponse placeOrder(NewFutureOrder order) {
        if (order.getType() == OrderType.MARKET) {
            return executeSync(binanceApiFutureService.newMarketOrder(
                    order.getSymbol(),
                    order.getSide(),
                    order.getPositionSide(),
                    order.getType(),
                    order.getQuantity(),
                    order.getNewClientOrderId(),
                    order.getNewOrderRespType(),
                    order.getRecvWindow(),
                    System.currentTimeMillis()
            ));
        }

        return executeSync(binanceApiFutureService.newOrder(
                order.getSymbol(),
                order.getSide(),
                order.getPositionSide(),
                order.getType(),
                order.getTimeInForce(),
                order.getQuantity(),
                order.getPrice(),
                order.getNewClientOrderId(),
                order.getNewOrderRespType(),
                order.getRecvWindow(),
                System.currentTimeMillis()
        ));
    }

    @Override
    public NewFutureOrderResponse placeLongOrder(String symbol, String quantity, String price, OrderType orderType, String clientOrderId, TimeInForce timeInForce) {
        NewFutureOrder order = NewFutureOrder.longOrder(symbol, quantity, price, orderType, clientOrderId, timeInForce);
        return placeOrder(order);
    }

    @Override
    public NewFutureOrderResponse placeShortOrder(String symbol, String quantity, String price, OrderType orderType, String clientOrderId, TimeInForce timeInForce) {
        NewFutureOrder order = NewFutureOrder.shortOrder(symbol, quantity, price, orderType, clientOrderId, timeInForce);
        return placeOrder(order);
    }

    @Override
    public NewFutureOrderResponse placeCloseLongOrder(String symbol, String quantity, String price, OrderType orderType, String clientOrderId, TimeInForce timeInForce) {
        NewFutureOrder order = NewFutureOrder.closeLongOrder(symbol, quantity, price, orderType, clientOrderId, timeInForce);
        return placeOrder(order);
    }

    @Override
    public NewFutureOrderResponse placeCloseShortOrder(String symbol, String quantity, String price, OrderType orderType, String clientOrderId, TimeInForce timeInForce) {
        NewFutureOrder order = NewFutureOrder.closeShortOrder(symbol, quantity, price, orderType, clientOrderId, timeInForce);
        return placeOrder(order);
    }

    @Override
    public ChangeLeverageResponse changeInitialLeverage(String symbol, int leverage) {
        return executeSync(binanceApiFutureService.changeInitialLeverage(symbol, leverage, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
    }
}
