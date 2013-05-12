package com.fermich.nolfix.fix.msg.exception;

import com.fermich.nolfix.fix.msg.common.BusinessMessageReject;

public class BusinessMessageRejectException extends RuntimeException {
    private BusinessMessageReject errorMessage;

    public BusinessMessageRejectException(BusinessMessageReject errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BusinessMessageReject getErrorMessage() {
        return errorMessage;
    }
}
