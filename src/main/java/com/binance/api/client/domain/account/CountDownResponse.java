package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountDownResponse {
    String symbol;
    String countdownTime;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCountdownTime() {
        return countdownTime;
    }

    public void setCountdownTime(String countdownTime) {
        this.countdownTime = countdownTime;
    }
}
