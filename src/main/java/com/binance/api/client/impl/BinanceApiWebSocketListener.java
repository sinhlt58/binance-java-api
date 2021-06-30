package com.binance.api.client.impl;

import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.exception.BinanceApiException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.io.IOException;

/**
 * Binance API WebSocket listener.
 */
public class BinanceApiWebSocketListener<T> extends WebSocketListener {

  private BinanceApiCallback<T> callback;

  private static final ObjectMapper mapper = new ObjectMapper();

  private final ObjectReader objectReader;

  private boolean closing = false;

  public BinanceApiWebSocketListener(BinanceApiCallback<T> callback, Class<T> eventClass) {
    this.callback = callback;
    this.objectReader = mapper.readerFor(eventClass);
  }

  public BinanceApiWebSocketListener(BinanceApiCallback<T> callback, TypeReference<T> eventTypeReference) {
    this.callback = callback;
    this.objectReader = mapper.readerFor(eventTypeReference);
  }

  @Override
  public void onMessage(WebSocket webSocket, String text) {
    try {
      System.out.println("onMessage: " + text);
      T event = objectReader.readValue(text);
      callback.onResponse(event);
    } catch (IOException e) {
      System.out.println("onMessage catch");
      throw new BinanceApiException(e);
    }
  }

  @Override
  public void onClosing(final WebSocket webSocket, final int code, final String reason) {
    System.out.println("onClosing: " + reason);
    closing = true;
  }

  @Override
  public void onFailure(WebSocket webSocket, Throwable t, Response response) {
    System.out.println("Throwable: " + t.getMessage());
    System.out.println("Throwable: " + t.getCause());
    if (!closing) {
      System.out.println("onFailure 222222");
      callback.onFailure(t);
    }
  }
}