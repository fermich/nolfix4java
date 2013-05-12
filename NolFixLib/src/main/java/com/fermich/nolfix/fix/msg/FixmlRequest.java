package com.fermich.nolfix.fix.msg;

import com.fermich.nolfix.broker.ApiSettings;

abstract public class FixmlRequest extends FixmlMessage {
    public Fixml pack() {
        return new Fixml()
                .setV(ApiSettings.FIXML_V)
                .setR(ApiSettings.FIXML_R)
                .setS(ApiSettings.FIXML_S);
    }
}
