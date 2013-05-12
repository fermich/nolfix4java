package com.fermich.nolfix.fix.msg.session;

import com.fermich.nolfix.fix.msg.Fixml;
import com.fermich.nolfix.fix.msg.FixmlRequest;
import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * <TrdgSesStatReq ReqID="1" SubReqTyp="1"/>
 */
@XStreamAlias("TrdgSesStatReq")
public class TradingSessionStatusRequest extends FixmlRequest {

    @XStreamAlias("ReqID")
    @XStreamAsAttribute
    private String reqId;     //ReqID

    @XStreamAlias("SubReqTyp")
    @XStreamAsAttribute
    private Character subReqTyp;     //values from SubscriptionRequestType

    @Override
    public Fixml pack() {
        return super.pack().addTradSessStatReq(this);
    }

    @Override
    public String getMsgName() {
        return "TrdgSesStatReq";
    }

    public enum SubscriptionRequestType {
        MSG_ONLINE_ON('1'), //Komunikaty online
        MSG_ONLINE_OFF('2'); //Anulowanie komunikatow online

        private SubscriptionRequestType(char subReqTypVal) {
            this.value = subReqTypVal;
        }

        private char value;

        public static SubscriptionRequestType getSubscriptionRequestType(char value) throws FixmlElementNotFound {
            for (SubscriptionRequestType subsReqType : values()) {
                if (subsReqType.getValue() == value) {
                    return subsReqType;
                }
            }
            throw new FixmlElementNotFound("Subscription Request Type not found for value: " + value);
        }

        public char getValue() {
            return value;
        }

    }

    public String getReqId() {
        return reqId;
    }

    public TradingSessionStatusRequest setReqId(String reqId) {
        this.reqId = reqId;
        return this;
    }

    public Character getSubReqTyp() {
        return subReqTyp;
    }

    public TradingSessionStatusRequest setSubReqTyp(Character subReqTyp) {
        this.subReqTyp = subReqTyp;
        return this;
    }

    @Override
    public String toString() {
        return String.format("TradingSessionStatusRequest [reqId=%s, subReqTyp=%s]", reqId, subReqTyp);
    }
}
