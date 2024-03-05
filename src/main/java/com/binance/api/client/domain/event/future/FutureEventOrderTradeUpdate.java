package com.binance.api.client.domain.event.future;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FutureEventOrderTradeUpdate {
    @JsonProperty("s")
    String symbol;
    @JsonProperty("c")
    String clientOrderId;
    @JsonProperty("i")
    long orderId;
    @JsonProperty("S")
    OrderSide side;
    @JsonProperty("o")
    OrderType orderType;
    @JsonProperty("ps")
    PositionSide positionSide;
    @JsonProperty("X")
    OrderStatus orderStatus;
    @JsonProperty("f")
    TimeInForce timeInForce;

    @JsonProperty("q")
    String originalQuantity;
    @JsonProperty("p")
    String originalPrice;
    @JsonProperty("ap")
    String averagePrice;
    @JsonProperty("sp")
    String stopPrice;

    @JsonProperty("rp")
    String realizedProfit;

    @JsonProperty("l")
    String quantityLastFilledTrade;
    @JsonProperty("z")
    String accumulatedQuantity;
    @JsonProperty("L")
    String priceOfLastFilledTrade;
    @JsonProperty("n")
    String commission;

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("symbol", symbol)
                .append("clientOrderId", clientOrderId)
                .append("side", side)
                .append("orderType", orderType)
                .append("positionSide", positionSide)
                .append("orderStatus", orderStatus)
                .append("timeInForce", timeInForce)
                .append("originalQuantity", originalQuantity)
                .append("originalPrice", originalPrice)
                .append("averagePrice", averagePrice)
                .append("stopPrice", stopPrice)
                .append("realizedProfit", realizedProfit)
                .toString();
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public OrderSide getSide() {
        return side;
    }

    public void setSide(OrderSide side) {
        this.side = side;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public PositionSide getPositionSide() {
        return positionSide;
    }

    public void setPositionSide(PositionSide positionSide) {
        this.positionSide = positionSide;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    public String getOriginalQuantity() {
        return originalQuantity;
    }

    public void setOriginalQuantity(String originalQuantity) {
        this.originalQuantity = originalQuantity;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
    }

    public String getRealizedProfit() {
        return realizedProfit;
    }

    public void setRealizedProfit(String realizedProfit) {
        this.realizedProfit = realizedProfit;
    }

    public String getQuantityLastFilledTrade() {
        return quantityLastFilledTrade;
    }

    public String getAccumulatedQuantity() {
        return accumulatedQuantity;
    }

    public String getPriceOfLastFilledTrade() {
        return priceOfLastFilledTrade;
    }

    public String getCommission() {
        return commission;
    }
}
