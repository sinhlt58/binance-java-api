package com.binance.api.client.impl;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.NewFutureOrderResponse;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.account.NewOrderResponseType;
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
    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @POST("/fapi/v1/batchOrders")
    Call<List<NewFutureOrderResponse>> createBatchOrders(@Query("batchOrders") String batchOrders,
                                                         @Query("recvWindow") Long recvWindow,
                                                         @Query("timestamp") Long timestamp);
}
