package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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

    public static ObjectMapper objectMapper = new ObjectMapper();

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

    public static NewFutureOrder limitLongOrder(String symbol, String quantity, String price){
        NewFutureOrder order = new NewFutureOrder(symbol);
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.BUY;
        order.positionSide = PositionSide.LONG;
        order.type = OrderType.LIMIT;
        order.newClientOrderId = "LIMITLONG" + symbol;

        return order;
    }

    public static NewFutureOrder closeLimitLongOrder(String symbol, String quantity, String price){
        NewFutureOrder order = new NewFutureOrder(symbol);
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.SELL;
        order.positionSide = PositionSide.LONG;
        order.type = OrderType.LIMIT;
        order.newClientOrderId = "CLOSELIMITLONG" + symbol;

        return order;
    }

    public static NewFutureOrder limitLongStopLossOrder(String symbol, String quantity, String stopPrice){
        NewFutureOrder order = new NewFutureOrder(symbol);
        order.quantity = quantity;
        order.price = stopPrice;
        order.side = OrderSide.SELL;
        order.positionSide = PositionSide.LONG;
        order.type = OrderType.STOP;
        order.stopPrice = stopPrice;
        order.newClientOrderId = "STOPLOSSLONG" + symbol;

        return order;
    }

    public static NewFutureOrder limitLongTakeProfitOrder(String symbol, String quantity, String takeProfitPrice){
        NewFutureOrder order = new NewFutureOrder(symbol);
        order.quantity = quantity;
        order.price = takeProfitPrice;
        order.side = OrderSide.SELL;
        order.positionSide = PositionSide.LONG;
        order.type = OrderType.TAKE_PROFIT;
        order.stopPrice = takeProfitPrice;
        order.newClientOrderId = "TAKEPROFITLONG" + symbol;

        return order;
    }

    public static NewFutureOrder limitShortOrder(String symbol, String quantity, String price){
        NewFutureOrder order = new NewFutureOrder(symbol);
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.SELL;
        order.positionSide = PositionSide.SHORT;
        order.type = OrderType.LIMIT;
        order.newClientOrderId = "LIMITSHORT" + symbol;

        return order;
    }

    public static NewFutureOrder closeLimitShortOrder(String symbol, String quantity, String price){
        NewFutureOrder order = new NewFutureOrder(symbol);
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.BUY;
        order.positionSide = PositionSide.SHORT;
        order.type = OrderType.LIMIT;
        order.newClientOrderId = "CLOSELIMITSHORT" + symbol;

        return order;
    }

    public static NewFutureOrder limitShortStopLossOrder(String symbol, String quantity, String stopPrice){
        NewFutureOrder order = new NewFutureOrder(symbol);
        order.quantity = quantity;
        order.price = stopPrice;
        order.side = OrderSide.BUY;
        order.positionSide = PositionSide.SHORT;
        order.type = OrderType.STOP;
        order.stopPrice = stopPrice;
        order.newClientOrderId = "STOPLOSSSHORT" + symbol;

        return order;
    }

    public static NewFutureOrder limitShortTakeProfitOrder(String symbol, String quantity, String takeProfitPrice){
        NewFutureOrder order = new NewFutureOrder(symbol);
        order.quantity = quantity;
        order.price = takeProfitPrice;
        order.side = OrderSide.BUY;
        order.positionSide = PositionSide.SHORT;
        order.type = OrderType.TAKE_PROFIT;
        order.stopPrice = takeProfitPrice;
        order.newClientOrderId = "TAKEPROFITSHORT" + symbol;

        return order;
    }

    public static String openLongPositionString(String symbol, String quantity, String price, String stopLossPrice, String takeProfitPrice){
        List<NewFutureOrder> orders = new ArrayList<>();
        orders.add(limitLongOrder(symbol, quantity, price));
        orders.add(limitLongStopLossOrder(symbol, quantity, stopLossPrice));
        orders.add(limitLongTakeProfitOrder(symbol, quantity, takeProfitPrice));

        return ordersToString(orders);
    }

    public static String openShortPositionString(String symbol, String quantity, String price, String stopLossPrice, String takeProfitPrice){
        List<NewFutureOrder> orders = new ArrayList<>();
        orders.add(limitShortOrder(symbol, quantity, price));
        orders.add(limitShortStopLossOrder(symbol, quantity, stopLossPrice));
        orders.add(limitShortTakeProfitOrder(symbol, quantity, takeProfitPrice));

        return ordersToString(orders);
    }

    public static String limitLongTakeProfitAndStopLossOrdersString(String symbol, String quantity, String stopLossPrice, String takeProfitPrice){
        List<NewFutureOrder> orders = new ArrayList<>();
        orders.add(limitLongStopLossOrder(symbol, quantity, stopLossPrice));
        orders.add(limitLongTakeProfitOrder(symbol, quantity, takeProfitPrice));

        return ordersToString(orders);
    }

    public static String limitShortTakeProfitAndStopLossOrdersString(String symbol, String quantity, String stopLossPrice, String takeProfitPrice){
        List<NewFutureOrder> orders = new ArrayList<>();
        orders.add(limitShortStopLossOrder(symbol, quantity, stopLossPrice));
        orders.add(limitShortTakeProfitOrder(symbol, quantity, takeProfitPrice));

        return ordersToString(orders);
    }

    public static String ordersToString(List<NewFutureOrder> orders){
        try{
            return objectMapper.writeValueAsString(orders);
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static void main(String[] args){
        // tests
        String p1 = openLongPositionString("BTCUSDT", "0.003", "30000.00", "31000.00", "29000.00");
        String p2 = openShortPositionString("BTCUSDT", "0.0017", "60000.00", "59000.00", "61000.00");

        System.out.println(p1);
        System.out.println(p2);
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
