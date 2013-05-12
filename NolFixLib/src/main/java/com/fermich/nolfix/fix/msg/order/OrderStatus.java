package com.fermich.nolfix.fix.msg.order;

import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;

public enum OrderStatus {
    NEW('0'), //Nowe
    ARCHIVE('C'), //Archiwalne
    BEING_MODIFIED('E'), //W trakcie modyfikacji
    ACTIVE('1'), //Wykonane/aktywne
    DONE('2'), //Wykonane
    CANCELED('4'), //Anulowane
    BEING_CANCELED('6'), //W trakcie anulaty
    REJECTED('8'); //Odrzucone

    private OrderStatus(char value) {
        this.value = value;
    }

    public static OrderStatus getOrderStatus(char value) throws FixmlElementNotFound {
        for (OrderStatus item : values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        throw new FixmlElementNotFound("Order Status not found for value: " + value);
    }

    private char value;

    public char getValue() {
        return value;
    }
}