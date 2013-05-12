package com.fermich.nolfix.fix.msg.order;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("OrdQty")
public class OrderQtyData { //OrdQty

    @XStreamAlias("Qty")
    @XStreamAsAttribute
    private Float qty;

    public Float getQty() {
        return qty;
    }

    public OrderQtyData setQty(Float qty) {
        this.qty = qty;
        return this;
    }

    @Override
    public String toString() {
        return String.format("OrderQtyData{qty=%s}", qty);
    }
}
