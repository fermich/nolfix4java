package com.fermich.nolfix.fix.msg.filter;

import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Response to MarketDataRequest
 * <FIXML v="5.0" r="20080317" s="20080314">
 * <MktDataFull>
 *   <req Typ="C"/>
 *   <req Typ="4"/>
 * </MktDataFull> </FIXML>
 */
@XStreamAlias("MktDataFull")
public class MarketDataSnapshotFullRefresh extends FixmlMessage { //MktDataFull

    @XStreamAlias("ReqID")
    @XStreamAsAttribute
    private String reqId; //ReqID; ID zadania wyczyszczenia filtra lub dodania papierow

    @Override
    public String getMsgName() {
        return "MktDataFull";
    }

    public String getReqId() {
        return reqId;
    }

    public MarketDataSnapshotFullRefresh setReqId(String reqId) {
        this.reqId = reqId;
        return this;
    }

    @Override
    public String toString() {
        return String.format("MarketDataSnapshotFullRefresh{reqId='%s'}", reqId);
    }
}
