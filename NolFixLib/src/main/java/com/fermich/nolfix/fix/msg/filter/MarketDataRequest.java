package com.fermich.nolfix.fix.msg.filter;

import com.fermich.nolfix.fix.msg.Fixml;
import com.fermich.nolfix.fix.msg.FixmlRequest;
import com.fermich.nolfix.fix.msg.common.Instrument;
import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Performs filter operations
 * <FIXML v="5.0" r="20080317" s="20080314">
 * <MktDataReq ReqID="1" SubReqTyp="1" MktDepth="0">
 * <req Typ="C"/> <req Typ="4"/> <req Typ="5"/>
 * <InstReq> <Instrmt Sym="COMARCH" ID="PLCOMAR00012" Src="4" />
 * <Instrmt Sym="TPSA" ID="PLTLKPL00017" Src="4" />
 * </InstReq>
 * </MktDataReqt>
 * </FIXML>
 */
@XStreamAlias("MktDataReq")
public class MarketDataRequest extends FixmlRequest {

    @XStreamAlias("ReqID")
    @XStreamAsAttribute
    private String reqId;

    @XStreamAlias("SubReqTyp")
    @XStreamAsAttribute
    private Character subReqTyp; //typ operacji (wartosci z SubscriptionRequestType)

    @XStreamAlias("MktDepth")
    @XStreamAsAttribute
    private Integer mktDepth; //liczba ofert (wartosci z MarketDepth)

    @XStreamImplicit(itemFieldName = "req")
    private List<MdReqGrp> req; //notowania

    @XStreamAlias("InstReq")
    private List<Instrument> instReq; //instrumenty

    @Override
    public String getMsgName() {
        return "MktDataReq";
    }

    @Override
    public Fixml pack() {
        return super.pack().addMktDataRequest(this);
    }

    @XStreamAlias("req")
    public static class MdReqGrp { //MDReqGrp

        @XStreamAlias("Typ")
        @XStreamAsAttribute
        private char typ;        //wartosc z EntryType

        public char getTyp() {
            return typ;
        }

        public MdReqGrp setTyp(char typ) {
            this.typ = typ;
            return this;
        }
    }

    public enum SubscriptionRequestType {

        ADD_TO_FILTER('1'), //Zadanie otrzymania komunikatow online dla papierow
        CLEAR_FILTER('2'); //Wyczyszczenie filtra

        private SubscriptionRequestType(char value) {
            this.value = value;
        }

        public static SubscriptionRequestType getSubscriptionRequestType(char value) throws FixmlElementNotFound {
            for (SubscriptionRequestType item : values()) {
                if (item.getValue() == value) {
                    return item;
                }
            }
            throw new FixmlElementNotFound("Subscription Request Type not found for value: " + value);
        }

        public char getValue() {
            return value;
        }

        private char value;
    }

    public String getReqId() {
        return reqId;
    }

    public MarketDataRequest setReqId(String reqId) {
        this.reqId = reqId;
        return this;
    }

    public Character getSubReqTyp() {
        return subReqTyp;
    }

    public MarketDataRequest setSubReqTyp(Character subReqTyp) {
        this.subReqTyp = subReqTyp;
        return this;
    }

    public int getMktDepth() {
        return mktDepth;
    }

    public MarketDataRequest setMktDepth(int mktDepth) {
        this.mktDepth = mktDepth;
        return this;
    }

    public List<MdReqGrp> getReq() {
        return req;
    }

    public MarketDataRequest setReq(List<MdReqGrp> req) {
        this.req = req;
        return this;
    }

    public List<? extends Instrument> getInstReq() {
        return instReq;
    }

    public MarketDataRequest setInstReq(List<Instrument> instReq) {
        this.instReq = instReq;
        return this;
    }

    @Override
    public String toString() {
        return String.format("MarketDataRequest [reqId=%s, subReqTyp=%s, mktDepth=%d, req=%s, instReq=%s]", reqId, subReqTyp, mktDepth, req, instReq);
    }
}
