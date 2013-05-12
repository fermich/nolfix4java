package com.fermich.nolfix.fix.msg;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class FixmlMessage {

    @XStreamOmitField
    private String rawMessage;

    public String getRawMessage() {
        return rawMessage;
    }

    public FixmlMessage setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
        return this;
    }

    public String getMsgName() {
        return "";
    }

}
