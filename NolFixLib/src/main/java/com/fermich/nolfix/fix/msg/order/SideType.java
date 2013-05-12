package com.fermich.nolfix.fix.msg.order;

import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;

public enum SideType {

    BUY('1'), //Kupno
    SELL('2'); //Sprzedaz

    private SideType(char value) {
        this.value = value;
    }

    public static SideType getSideType(char value) throws FixmlElementNotFound {
        for (SideType item : values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        throw new FixmlElementNotFound("Side Type not found for value: " + value);
    }

    private char value;

    public char getValue() {
        return value;
    }
}
