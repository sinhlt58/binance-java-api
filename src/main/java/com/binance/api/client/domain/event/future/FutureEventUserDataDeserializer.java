package com.binance.api.client.domain.event.future;

import com.binance.api.client.exception.BinanceApiException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class FutureEventUserDataDeserializer extends JsonDeserializer<FutureEventUserData> {

    private ObjectMapper mapper;

    @Override
    public FutureEventUserData deserialize(JsonParser jp, DeserializationContext ctx) throws IOException, JsonProcessingException {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }

        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);

        // handle combine stream case
        if (node.get("stream") != null){
            node = node.get("data");
        }

        FutureEventUserData event = new FutureEventUserData();
        event.setEventType(FutureEventUserType.valueOf(node.get("e").asText()));
        event.setEventTime(node.get("E").asLong());
        event.setTransactionTime(node.get("T").asLong());
        if (event.getEventType() == FutureEventUserType.ORDER_TRADE_UPDATE && node.get("o") != null){
            FutureEventOrderTradeUpdate orderTradeUpdate = getUserDataUpdateEventDetail(node.get("o").toString(), FutureEventOrderTradeUpdate.class, mapper);
            event.setOrderTradeUpdate(orderTradeUpdate);
        }

        return event;
//        {
//            "e":"ORDER_TRADE_UPDATE",     // Event Type
//            "E":1568879465651,            // Event Time
//            "T":1568879465650,            // Transaction Time
//            "o":{
//                "s":"BTCUSDT",              // Symbol
//                "c":"TEST",                 // Client Order Id
//                // special client order id:
//                // starts with "autoclose-": liquidation order
//                // "adl_autoclose": ADL auto close order
//                "S":"SELL",                 // Side
//                "o":"TRAILING_STOP_MARKET", // Order Type
//                "f":"GTC",                  // Time in Force
//                "q":"0.001",                // Original Quantity
//                "p":"0",                    // Original Price
//                "ap":"0",                   // Average Price
//                "sp":"7103.04",             // Stop Price. Please ignore with TRAILING_STOP_MARKET order
//                "x":"NEW",                  // Execution Type
//                "X":"NEW",                  // Order Status
//                "i":8886774,                // Order Id
//                "l":"0",                    // Order Last Filled Quantity
//                "z":"0",                    // Order Filled Accumulated Quantity
//                "L":"0",                    // Last Filled Price
//                "N":"USDT",             // Commission Asset, will not push if no commission
//                "n":"0",                // Commission, will not push if no commission
//                "T":1568879465651,          // Order Trade Time
//                "t":0,                      // Trade Id
//                "b":"0",                    // Bids Notional
//                "a":"9.91",                 // Ask Notional
//                "m":false,                  // Is this trade the maker side?
//                "R":false,                  // Is this reduce only
//                "wt":"CONTRACT_PRICE",      // Stop Price Working Type
//                "ot":"TRAILING_STOP_MARKET",    // Original Order Type
//                "ps":"LONG",                        // Position Side
//                "cp":false,                     // If Close-All, pushed with conditional order
//                "AP":"7476.89",             // Activation Price, only puhed with TRAILING_STOP_MARKET order
//                "cr":"5.0",                 // Callback Rate, only puhed with TRAILING_STOP_MARKET order
//                "rp":"0"                            // Realized Profit of the trade
//            }
//        }
    }

    public <T> T getUserDataUpdateEventDetail(String json, Class<T> clazz, ObjectMapper mapper) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new BinanceApiException(e);
        }
    }
}
