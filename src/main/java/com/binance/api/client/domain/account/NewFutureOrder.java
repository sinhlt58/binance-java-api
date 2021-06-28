package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * A trade order to enter or exit a position.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
    private long timestamp;

    public NewFutureOrder(){
        this.timeInForce = TimeInForce.GTC;
        this.newOrderRespType = NewOrderResponseType.RESULT;
        this.timestamp = System.currentTimeMillis();
        this.recvWindow = BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
    }

    public static NewFutureOrder limitLongOrder(String quantity, String price){
        NewFutureOrder order = new NewFutureOrder();
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.BUY;
        order.positionSide = PositionSide.LONG;
        order.type = OrderType.LIMIT;

        return order;
    }

    public static NewFutureOrder limitLongTakeProfitOrder(String quantity, String price, String stopPrice){
        NewFutureOrder order = new NewFutureOrder();
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.SELL;
        order.positionSide = PositionSide.LONG;
        order.type = OrderType.TAKE_PROFIT;
        order.stopPrice = stopPrice;
        order.priceProtect = PriceProtect.TRUE;

        return order;
    }

    public static NewFutureOrder limitLongStopLossOrder(String quantity, String price, String stopPrice){
        NewFutureOrder order = new NewFutureOrder();
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.SELL;
        order.positionSide = PositionSide.LONG;
        order.type = OrderType.STOP_LOSS;
        order.stopPrice = stopPrice;
        order.priceProtect = PriceProtect.TRUE;

        return order;
    }

    public static NewFutureOrder limitShortOrder(String quantity, String price){
        NewFutureOrder order = new NewFutureOrder();
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.SELL;
        order.positionSide = PositionSide.SHORT;
        order.type = OrderType.LIMIT;

        return order;
    }

    public static NewFutureOrder limitShortTakeProfitOrder(String quantity, String price, String stopPrice){
        NewFutureOrder order = new NewFutureOrder();
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.BUY;
        order.positionSide = PositionSide.SHORT;
        order.type = OrderType.TAKE_PROFIT;
        order.stopPrice = stopPrice;
        order.priceProtect = PriceProtect.TRUE;

        return order;
    }

    public static NewFutureOrder limitShortStopLossOrder(String quantity, String price, String stopPrice){
        NewFutureOrder order = new NewFutureOrder();
        order.quantity = quantity;
        order.price = price;
        order.side = OrderSide.BUY;
        order.positionSide = PositionSide.SHORT;
        order.type = OrderType.STOP_LOSS;
        order.stopPrice = stopPrice;
        order.priceProtect = PriceProtect.TRUE;

        return order;
    }

    public static List<NewFutureOrder> openLongPosition(String quantity, String price, String takeProfitPrice, String stopLossPrice){
        List<NewFutureOrder> orders = new ArrayList<>();
        orders.add(limitLongOrder(quantity, price));
        orders.add(limitLongStopLossOrder(quantity, price, stopLossPrice));
        orders.add(limitLongTakeProfitOrder(quantity, price, takeProfitPrice));

        return orders;
    }

    public static List<NewFutureOrder> openShortPosition(String quantity, String price, String takeProfitPrice, String stopLossPrice){
        List<NewFutureOrder> orders = new ArrayList<>();
        orders.add(limitShortOrder(quantity, price));
        orders.add(limitShortStopLossOrder(quantity, price, stopLossPrice));
        orders.add(limitShortTakeProfitOrder(quantity, price, takeProfitPrice));

        return orders;
    }
}
