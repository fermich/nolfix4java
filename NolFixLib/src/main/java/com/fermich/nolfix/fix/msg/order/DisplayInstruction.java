package com.fermich.nolfix.fix.msg.order;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("DsplyInstr")
public class DisplayInstruction {

    @XStreamAlias("DisplayQty")
    @XStreamAsAttribute
    private Float displayQty;

    public Float getDisplayQty() {
        return displayQty;
    }

    public DisplayInstruction setDisplayQty(Float displayQty) {
        this.displayQty = displayQty;
        return this;
    }

    @Override
    public String toString() {
        return String.format("DisplayInstruction{displayQty=%s}", displayQty);
    }
}
