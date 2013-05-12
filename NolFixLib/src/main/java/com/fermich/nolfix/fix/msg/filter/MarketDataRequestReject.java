package com.fermich.nolfix.fix.msg.filter;

import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Response with reason of query rejection
 * <MktDataReqRej ReqID="1" ReqRejResn="0"/>
 */
@XStreamAlias("MktDataReqRej")
public class MarketDataRequestReject extends FixmlMessage {

    @XStreamAlias("ReqID")
    @XStreamAsAttribute
    private String reqId; // ID MarketDataRequest
    @XStreamAlias("ReqRejResn")
    @XStreamAsAttribute
    private Character reqRejResn; // Powod odmowy, see enum MDReqRejReason
    @XStreamAlias("Txt")
    @XStreamAsAttribute
    private String txt;

    @Override
    public String getMsgName() {
        return "MktDataReqRej";
    }

    public enum MDReqRejReason {

        UNKNOWN_SECURITY('0'), //Nieznany walor
        ID_DUPLICATE('1'), //Duplikat MDReqID
        ERROR_SUBSCR_REQ('4'), //Blad w polu SubscriptionRequestType
        UNSUPPORTED_NUM_OF_OFFERS('5'), //Niewspierana liczba ofert
        ERROR_MDUPDATE_TYPE('6'), //Blad w polu MDUpdateType
        UNSUPPORTED_QUOTES('8'); //Niewspierane notowania

        private MDReqRejReason(char reqRejResnVal) {
            this.value = reqRejResnVal;
        }

        public static MDReqRejReason getMDReqRejReason(char value) throws FixmlElementNotFound {
            for (MDReqRejReason item : values()) {
                if (item.getValue() == value) {
                    return item;
                }
            }
            throw new FixmlElementNotFound("Market Data Request Reject Type not found for value: " + value);
        }

        public char getValue() {
            return value;
        }

        private char value;
    }

    public String getReqId() {
        return reqId;
    }

    public MarketDataRequestReject setReqId(String reqId) {
        this.reqId = reqId;
        return this;
    }

    public char getReqRejResn() {
        return reqRejResn;
    }

    public MarketDataRequestReject setReqRejResn(char reqRejResn) {
        this.reqRejResn = reqRejResn;
        return this;
    }

    public String getTxt() {
        return txt;
    }

    public MarketDataRequestReject setTxt(String txt) {
        this.txt = txt;
        return this;
    }

    @Override
    public String toString() {
        return String.format("MarketDataRequestReject [reqId=%s, reqRejResn=%s]", reqId, reqRejResn);
    }
}
