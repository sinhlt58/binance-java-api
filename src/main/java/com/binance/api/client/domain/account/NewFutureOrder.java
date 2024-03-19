package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A trade order to enter or exit a position.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewFutureOrder {
    private String symbol;
    private OrderSide side;
    private PositionSide positionSide;

    private OrderType type;
    private String quantity;
    private String price;
    private String stopPrice;
    private String closePrice;
    private PriceProtect priceProtect;

    private String newClientOrderId;
    private NewOrderResponseType newOrderRespType;
    private TimeInForce timeInForce;
    private Long recvWindow;
    private Long timestamp;

    public NewFutureOrder(String symbol, boolean isForBatch){
        this.symbol = symbol;
        this.timeInForce = TimeInForce.GTX; // post only order
        this.newOrderRespType = NewOrderResponseType.RESULT;
        if (!isForBatch){
            this.timestamp = System.currentTimeMillis();
            this.recvWindow = BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
        }
    }

    public NewFutureOrder(String symbol){
        this(symbol, true);
    }

    public static NewFutureOrder longOrder(String symbol, String quantity, String price, OrderType orderType, String clientOrderId, TimeInForce timeInForce){
        NewFutureOrder order = new NewFutureOrder(symbol);
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.BUY;
        order.positionSide = PositionSide.LONG;
        order.type = orderType;
        order.newClientOrderId = Objects.requireNonNullElseGet(clientOrderId, () -> "MARKETLONG" + symbol);
        if (timeInForce == null) {
            order.timeInForce = TimeInForce.GTC;
        } else {
            order.timeInForce = timeInForce;
        }

        return order;
    }

    public static NewFutureOrder shortOrder(String symbol, String quantity, String price, OrderType orderType, String clientOrderId, TimeInForce timeInForce){
        NewFutureOrder order = new NewFutureOrder(symbol);
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.SELL;
        order.positionSide = PositionSide.SHORT;
        order.type = orderType;
        order.newClientOrderId = Objects.requireNonNullElseGet(clientOrderId, () -> "MARKETSHORT" + symbol);
        if (timeInForce == null) {
            order.timeInForce = TimeInForce.GTC;
        } else {
            order.timeInForce = timeInForce;
        }

        return order;
    }

    public static NewFutureOrder closeLongOrder(String symbol, String quantity, String price, OrderType orderType, String clientOrderId, TimeInForce timeInForce){
        NewFutureOrder order = new NewFutureOrder(symbol);
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.SELL;
        order.positionSide = PositionSide.LONG;
        order.type = orderType;
        order.newClientOrderId = Objects.requireNonNullElseGet(clientOrderId, () -> "CLOSEMARKETLONG" + symbol);
        if (timeInForce == null) {
            order.timeInForce = TimeInForce.GTC;
        } else {
            order.timeInForce = timeInForce;
        }

        return order;
    }

    public static NewFutureOrder closeShortOrder(String symbol, String quantity, String price, OrderType orderType, String clientOrderId, TimeInForce timeInForce){
        NewFutureOrder order = new NewFutureOrder(symbol);
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.BUY;
        order.positionSide = PositionSide.SHORT;
        order.type = orderType;
        order.newClientOrderId = Objects.requireNonNullElseGet(clientOrderId, () -> "CLOSEMARKETSHORT" + symbol);
        if (timeInForce == null) {
            order.timeInForce = TimeInForce.GTC;
        } else {
            order.timeInForce = timeInForce;
        }

        return order;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public OrderSide getSide() {
        return side;
    }

    public void setSide(OrderSide side) {
        this.side = side;
    }

    public PositionSide getPositionSide() {
        return positionSide;
    }

    public void setPositionSide(PositionSide positionSide) {
        this.positionSide = positionSide;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
    }

    public String getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(String closePrice) {
        this.closePrice = closePrice;
    }

    public PriceProtect getPriceProtect() {
        return priceProtect;
    }

    public void setPriceProtect(PriceProtect priceProtect) {
        this.priceProtect = priceProtect;
    }

    public String getNewClientOrderId() {
        return newClientOrderId;
    }

    public void setNewClientOrderId(String newClientOrderId) {
        this.newClientOrderId = newClientOrderId;
    }

    public NewOrderResponseType getNewOrderRespType() {
        return newOrderRespType;
    }

    public void setNewOrderRespType(NewOrderResponseType newOrderRespType) {
        this.newOrderRespType = newOrderRespType;
    }

    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    public Long getRecvWindow() {
        return recvWindow;
    }

    public void setRecvWindow(Long recvWindow) {
        this.recvWindow = recvWindow;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
