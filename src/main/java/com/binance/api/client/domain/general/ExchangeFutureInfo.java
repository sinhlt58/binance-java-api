package com.binance.api.client.domain.general;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.exception.BinanceApiException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeFutureInfo {
    private String timezone;

    private Long serverTime;

    private List<RateLimit> rateLimits;

    private List<SymbolFutureInfo> symbols;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Long getServerTime() {
        return serverTime;
    }

    public void setServerTime(Long serverTime) {
        this.serverTime = serverTime;
    }

    public List<RateLimit> getRateLimits() {
        return rateLimits;
    }

    public void setRateLimits(List<RateLimit> rateLimits) {
        this.rateLimits = rateLimits;
    }

    public List<SymbolFutureInfo> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<SymbolFutureInfo> symbols) {
        this.symbols = symbols;
    }

    /**
     * @param symbol the symbol to obtain information for (e.g. ETHBTC)
     * @return symbol exchange information
     */
    public SymbolFutureInfo getSymbolInfo(String symbol) {
        return symbols.stream().filter(symbolInfo -> symbolInfo.getSymbol().equals(symbol))
                .findFirst()
                .orElseThrow(() -> new BinanceApiException("Unable to obtain information for symbol " + symbol));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("timezone", timezone)
                .append("serverTime", serverTime)
                .append("rateLimits", rateLimits)
                .append("symbols", symbols)
                .toString();
    }
}
