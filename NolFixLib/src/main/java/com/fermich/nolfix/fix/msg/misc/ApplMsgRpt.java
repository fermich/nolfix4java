package com.fermich.nolfix.fix.msg.misc;

import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Information about delay
 * <ApplMsgRpt ApplRepID="1" Txt="22"/>
 */
@XStreamAlias("ApplMsgRpt")
public class ApplMsgRpt extends FixmlMessage {

    @XStreamAlias("ApplRepID")
    @XStreamAsAttribute
    private String applRepId;    //ID komunikatu

    @XStreamAlias("Txt")
    @XStreamAsAttribute
    private String txt;            //string zawierajacy informacje o opoznieniu (milisekundy)

    @Override
    public String getMsgName() {
        return "ApplMsgRpt";
    }

    public String getApplRepId() {
        return applRepId;
    }

    public ApplMsgRpt setApplRepId(String applRepId) {
        this.applRepId = applRepId;
        return this;
    }

    public String getTxt() {
        return txt;
    }

    public ApplMsgRpt setTxt(String txt) {
        this.txt = txt;
        return this;
    }

    @Override
    public String toString() {
        return String.format("ApplMsgRpt{applRepId='%s', txt='%s'}", applRepId, txt);
    }
}
