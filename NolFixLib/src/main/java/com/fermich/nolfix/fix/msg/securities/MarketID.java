package com.fermich.nolfix.fix.msg.securities;

import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;

public enum MarketID {
    NM("NM"), //Rynek kasowy
    DN("DN"); //Rynek pochodny

    private final String value;

    private MarketID(String val) {
        this.value = val;
    }

    public static MarketID getMarketId(String value) throws FixmlElementNotFound {
        for (MarketID mktId : values()) {
            if (mktId.getValue().equals(value)) {
                return mktId;
            }
        }
        throw new FixmlElementNotFound("Market Id not found for value: " + value);
    }

    public String getValue() {
        return value;
    }

}
