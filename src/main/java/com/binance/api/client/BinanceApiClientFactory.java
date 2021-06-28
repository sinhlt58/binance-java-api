package com.binance.api.client;

import com.binance.api.client.config.BinanceApiConfig;
import com.binance.api.client.impl.*;

import static com.binance.api.client.impl.BinanceApiServiceGenerator.getSharedClient;

/**
 * A factory for creating BinanceApi client objects.
 */
public class BinanceApiClientFactory {

  /**
   * API Key
   */
  private String apiKey;

  /**
   * Secret.
   */
  private String secret;

  /**
   * Instantiates a new binance api client factory.
   *
   * @param apiKey the API key
   * @param secret the Secret
   */
  private BinanceApiClientFactory(String apiKey, String secret) {
    this.apiKey = apiKey;
    this.secret = secret;
  }

  /**
   * New instance.
   *
   * @param apiKey the API key
   * @param secret the Secret
   *
   * @return the binance api client factory
   */
  public static BinanceApiClientFactory newInstance(String apiKey, String secret) {
    return new BinanceApiClientFactory(apiKey, secret);
  }

  /**
   * New instance without authentication.
   *
   * @return the binance api client factory
   */
  public static BinanceApiClientFactory newInstance() {
    return new BinanceApiClientFactory(null, null);
  }

  /**
   * Creates a new synchronous/blocking REST client.
   */
  public BinanceApiRestClient newRestClient() {
    return new BinanceApiRestClientImpl(apiKey, secret);
  }

  /**
   * Creates a new asynchronous/non-blocking REST client.
   */
  public BinanceApiAsyncRestClient newAsyncRestClient() {
    return new BinanceApiAsyncRestClientImpl(apiKey, secret);
  }

  /**
   * Creates a new asynchronous/non-blocking Margin REST client.
   */
  public BinanceApiAsyncMarginRestClient newAsyncMarginRestClient() {
    return new BinanceApiAsyncMarginRestClientImpl(apiKey, secret);
  }

  /**
   * Creates a new synchronous/blocking Margin REST client.
   */
  public BinanceApiMarginRestClient newMarginRestClient() {
    return new BinanceApiMarginRestClientImpl(apiKey, secret);
  }

  /**
   * Creates a new web socket client used for handling data streams.
   */
  public BinanceApiWebSocketClient newWebSocketClient() {
    return new BinanceApiWebSocketClientImpl(getSharedClient());
  }

  /**
   * Creates a new synchronous/blocking Swap REST client.
   */
  public BinanceApiSwapRestClient newSwapRestClient() {
    return new BinanceApiSwapRestClientImpl(apiKey, secret);
  }

  // sinhlt start
  /**
   * Creates a new synchronous/blocking Future REST client.
   */
  public BinanceApiFutureRestClient newFutureRestClient() {
    return new BinanceApiFutureRestClientImpl(apiKey, secret);
  }

  /**
   * Creates a new future web socket client used for handling data streams.
   */
  public BinanceApiFutureWebSocketApi newFutureWebSocketClient() {
    return new BinanceApiFutureWebSocketApiImpl(getSharedClient());
  }
  /**
   * Creates a new synchronous/blocking Future REST client.
   */
  public BinanceApiFutureRestClient newTestnetFutureRestClient() {
    return new BinanceApiFutureRestClientImpl(apiKey, secret, BinanceApiConfig.getTestnetFutureApiBaseUrl());
  }

  /**
   * Creates a new future web socket client used for handling data streams.
   */
  public BinanceApiFutureWebSocketApi newTestnetFutureWebSocketClient() {
    return new BinanceApiFutureWebSocketApiImpl(getSharedClient(), BinanceApiConfig.getTestnetFutureStreamApiBaseUrl());
  }
  // sinhlt end
}
