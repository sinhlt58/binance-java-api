package com.binance.api.client.impl;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.PositionSide;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.event.ListenKey;
import com.binance.api.client.domain.general.ExchangeFutureInfo;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BinanceApiFutureService {
    @GET("/fapi/v1/ping")
    Call<Void> ping();

    @GET("/fapi/v1/exchangeInfo")
    Call<ExchangeFutureInfo> getExchangeInfo();

    @GET("/fapi/v1/ticker/24hr")
    Call<TickerStatistics> get24HrPriceStatistics(@Query("symbol") String symbol);

    @GET("/fapi/v1/ticker/24hr")
    Call<List<TickerStatistics>> getAll24HrPriceStatistics();

    @GET("/fapi/v1/ticker/price")
    Call<TickerPrice> getLatestPrice(@Query("symbol") String symbol);

    @GET("/fapi/v1/ticker/price")
    Call<List<TickerPrice>> getAllLatestPrices();

    @GET("/fapi/v1/klines")
    Call<List<Candlestick>> getCandlestickBars(
            @Query("symbol") String symbol,
            @Query("interval") String interval,
            @Query("limit") Integer limit,
            @Query("startTime") Long startTime,
            @Query("endTime") Long endTime
    );

    @GET("/fapi/v1/continuousKlines")
    Call<List<Candlestick>> getContractCandlestickBars(
            @Query("pair") String pair,
            @Query("contractType") String contractType,
            @Query("interval") String interval,
            @Query("limit") Integer limit,
            @Query("startTime") Long startTime,
            @Query("endTime") Long endTime
    );

    // User stream endpoints

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @POST("/fapi/v1/listenKey")
    Call<ListenKey> startUserDataStream();

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @PUT("/fapi/v1/listenKey")
    Call<Void> keepAliveUserDataStream();

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @DELETE("/fapi/v1/listenKey")
    Call<Void> closeAliveUserDataStream();

    // orders, positions
    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @POST("/fapi/v1/batchOrders")
    Call<List<NewFutureOrderResponse>> createBatchOrders(@Query("batchOrders") String batchOrders,
                                                         @Query("recvWindow") Long recvWindow,
                                                         @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @DELETE("/fapi/v1/order")
    Call<NewFutureOrderResponse> cancelOrder(@Query("symbol") String symbol,
                                             @Query("origClientOrderId") String origClientOrderId,
                                             @Query("recvWindow") Long recvWindow,
                                             @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/fapi/v1/order")
    Call<NewFutureOrderResponse> newOrder(@Query("symbol") String symbol,
                                          @Query("side") OrderSide side,
                                          @Query("positionSide") PositionSide positionSide,
                                          @Query("type") OrderType type,
                                          @Query("timeInForce") TimeInForce timeInForce, // only require for limit order
                                          @Query("quantity") String quantity,
                                          @Query("price") String price, // only require for limit order
                                          @Query("newClientOrderId") String newClientOrderId,
                                          @Query("newOrderRespType") NewOrderResponseType newOrderRespType,
                                          @Query("recvWindow") Long recvWindow,
                                          @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/fapi/v1/order")
    Call<NewFutureOrderResponse> newMarketOrder(@Query("symbol") String symbol,
                                          @Query("side") OrderSide side,
                                          @Query("positionSide") PositionSide positionSide,
                                          @Query("type") OrderType type,
                                          @Query("quantity") String quantity,
                                          @Query("newClientOrderId") String newClientOrderId,
                                          @Query("newOrderRespType") NewOrderResponseType newOrderRespType,
                                          @Query("recvWindow") Long recvWindow,
                                          @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/fapi/v1/countdownCancelAll")
    Call<CountDownResponse> setOrderCountdownCancelAll(@Query("symbol") String symbol,
                                                       @Query("countdownTime") Long countdownTime,
                                                       @Query("recvWindow") Long recvWindow,
                                                       @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/fapi/v1/leverage")
    Call<ChangeLeverageResponse> changeInitialLeverage(@Query("symbol") String symbol,
                                                       @Query("leverage") int leverage,
                                                       @Query("recvWindow") Long recvWindow,
                                                       @Query("timestamp") Long timestamp);
}
