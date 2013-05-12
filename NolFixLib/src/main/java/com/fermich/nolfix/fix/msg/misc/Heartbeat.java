package com.fermich.nolfix.fix.msg.misc;

import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <FIXML v="5.0" r="20080317" s="20080314">
 * <Heartbeat/>
 * </FIXML>
 */
@XStreamAlias("Heartbeat")
public class Heartbeat extends FixmlMessage {

    @Override
    public String getMsgName() {
        return "Heartbeat";
    }

    @Override
    public String toString() {
        return "Heartbeat{}";
    }
}
