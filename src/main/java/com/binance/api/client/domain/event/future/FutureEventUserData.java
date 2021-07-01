package com.binance.api.client.domain.event.future;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FutureEventUserData {
    @JsonProperty("e")
    private FutureEventUserType eventType;
    @JsonProperty("E")
    private Long eventTime;
    @JsonProperty("T")
    private Long transactionTime;
    @JsonProperty("o")
    private FutureEventOrderTradeUpdate orderTradeUpdate;

    private int code;
    private String msg;

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("eventType", eventType)
                .append("eventTime", eventTime)
                .append("transactionTime", transactionTime)
                .append("orderTradeUpdate", orderTradeUpdate)
                .append("code", code)
                .append("msg", msg)
                .toString();
    }

    public FutureEventUserType getEventType() {
        return eventType;
    }

    public void setEventType(FutureEventUserType eventType) {
        this.eventType = eventType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    public Long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Long transactionTime) {
        this.transactionTime = transactionTime;
    }

    public FutureEventOrderTradeUpdate getOrderTradeUpdate() {
        return orderTradeUpdate;
    }

    public void setOrderTradeUpdate(FutureEventOrderTradeUpdate orderTradeUpdate) {
        this.orderTradeUpdate = orderTradeUpdate;
    }
}
