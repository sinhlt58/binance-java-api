package com.binance.api.client.impl;

import com.binance.api.client.domain.general.ExchangeFutureInfo;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

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
}
