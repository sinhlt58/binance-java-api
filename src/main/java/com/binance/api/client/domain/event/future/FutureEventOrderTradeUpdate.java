package com.binance.api.client.domain.event.future;

import com.binance.api.client.domain.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FutureEventOrderTradeUpdate {
    @JsonProperty("s")
    String symbol;
    @JsonProperty("c")
    String clientOrderId;
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
}
