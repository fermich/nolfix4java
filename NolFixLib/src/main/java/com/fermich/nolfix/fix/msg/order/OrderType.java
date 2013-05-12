package com.fermich.nolfix.fix.msg.order;

import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;

public enum OrderType {

    PKC('1'), //Po kazdej cenie
    LIMIT('L'), //Limit
    STOP_STOPLOSS('3'), //Stop / Stop Loss
    STOP_LIMIT('4'), //Stop Limit
    PCR('K'), //Po cenie rynkowej
    PEG('E'), //PEG
    PEGL('G'); //PEG z limitem

    private OrderType(char value) {
        this.value = value;
    }

    public static OrderType getOrderType(char value) throws FixmlElementNotFound {
        for (OrderType item : values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        throw new FixmlElementNotFound("Order Type not found for value: " + value);
    }

    private char value;

    public char getValue() {
        return value;
    }
}
