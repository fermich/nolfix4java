package com.fermich.nolfix.fix.msg.common;

import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;

public enum MarketDepth {

    ALL_OFFERS(0), //Wszystkie oferty
    BEST_OFFER(1), //Najlepsza oferta
    BEST_5_OFFERS(2); //5 ofert

    private MarketDepth(int value) {
        this.value = value;
    }

    public static MarketDepth getMarketDepth(int value) throws FixmlElementNotFound {
        for (MarketDepth item : values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        throw new FixmlElementNotFound("Market Depth not found for value: " + value);
    }

    public int getValue() {
        return value;
    }

    private int value;
}
