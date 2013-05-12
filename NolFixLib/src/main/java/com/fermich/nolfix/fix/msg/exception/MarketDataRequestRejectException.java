package com.fermich.nolfix.fix.msg.exception;

import com.fermich.nolfix.fix.msg.filter.MarketDataRequestReject;

public class MarketDataRequestRejectException extends RuntimeException {
    private MarketDataRequestReject errorMessage;

    public MarketDataRequestRejectException(MarketDataRequestReject errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MarketDataRequestReject getErrorMessage() {
        return errorMessage;
    }
}
