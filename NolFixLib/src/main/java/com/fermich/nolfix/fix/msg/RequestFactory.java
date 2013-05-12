package com.fermich.nolfix.fix.msg;

import com.fermich.nolfix.fix.id.IdGenerator;
import com.fermich.nolfix.fix.msg.access.UserRequest;
import com.fermich.nolfix.fix.msg.filter.MarketDataRequest;
import com.fermich.nolfix.fix.msg.order.NewOrderSingle;
import com.fermich.nolfix.fix.msg.order.OrderCancelReplaceRequest;
import com.fermich.nolfix.fix.msg.order.OrderCancelRequest;
import com.fermich.nolfix.fix.msg.order.OrderStatusRequest;
import com.fermich.nolfix.fix.msg.securities.SecurityListRequest;
import com.fermich.nolfix.fix.msg.session.TradingSessionStatusRequest;

public class RequestFactory {

    private IdGenerator idGen;

    public RequestFactory(IdGenerator idGen) {
        this.idGen = idGen;
    }

    public UserRequest createUserRequest() {
        return new UserRequest().setUserReqId(String.valueOf(idGen.nextId()));
    }

    public NewOrderSingle createNewOrderSingle() {
        return new NewOrderSingle().setId(String.valueOf(idGen.nextId()));
    }

    public OrderCancelReplaceRequest createOrderCancelReplaceRequest() {
        return new OrderCancelReplaceRequest().setId(String.valueOf(idGen.nextId()));
    }

    public OrderCancelRequest createOrderCancelRequest() {
        return new OrderCancelRequest().setId(String.valueOf(idGen.nextId()));
    }

    public OrderStatusRequest createOrderStatusRequest() {
        return new OrderStatusRequest().setId(String.valueOf(idGen.nextId()));
    }

    public TradingSessionStatusRequest createTradingSessionStatusRequest() {
        return new TradingSessionStatusRequest().setReqId(String.valueOf(idGen.nextId()));
    }

    public MarketDataRequest createMarketDataRequest() {
        return new MarketDataRequest().setReqId(String.valueOf(idGen.nextId()));
    }

    public SecurityListRequest createSecurityListRequest() {
        return new SecurityListRequest().setReqId(String.valueOf(idGen.nextId()));
    }
}
