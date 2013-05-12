package com.fermich.nolfix.fix.msg.order;

import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;

public enum TimeInForce {

    DAY('0'), //Dzien
    TO_BE_CANCELLED('1'), //do anulowania
    BEFORE_OPENNING('2'), //Przed otwarciem
    EXECUTE_OR_CANCEL_WIA('3'), //Wykonaj albo anuluj WiA
    EXECUTE_OR_CANCEL_WUA('4'), //Wykonaj albo anuluj WuA
    TO_DAY('6'), //Do dnia
    ON_CLOSE('7'), //na zamkniecie
    ON_FIXING('f'), //na najbliższy fixing
    TO_TIME('t'); //do czasu

    private TimeInForce(char value) {
        this.value = value;
    }

    public static TimeInForce getTimeInForce(char value) throws FixmlElementNotFound {
        for (TimeInForce item : values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        throw new FixmlElementNotFound("Time In Force not found for value: " + value);
    }

    private char value;

    public char getValue() {
        return value;
    }
}
