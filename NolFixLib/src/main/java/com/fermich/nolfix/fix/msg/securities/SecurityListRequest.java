package com.fermich.nolfix.fix.msg.securities;

import com.fermich.nolfix.fix.msg.Fixml;
import com.fermich.nolfix.fix.msg.FixmlRequest;
import com.fermich.nolfix.fix.msg.common.Instrument;
import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Query for securities list
 * <SecListReq ReqID="0" ListReqTyp="0">
 * <Instrmt Sym="COMARCH"/>
 * </SecListReq>
 */
@XStreamAlias("SecListReq")
public class SecurityListRequest extends FixmlRequest {
    @XStreamAlias("ReqID")
    @XStreamAsAttribute
    private String reqId;    //ID zlecenia

    @XStreamAlias("ListReqTyp")
    @XStreamAsAttribute
    private Integer listReqTyp;    //wartosc z SecurityListRequestType

    @XStreamAlias("MktID")
    @XStreamAsAttribute
    private String mktId;    //kod rynku, wartosci z MarketID

    @XStreamImplicit(itemFieldName = "Instrmt")
    private List<Instrument> instrmts; //instruments

    @Override
    public Fixml pack() {
        return super.pack().addSecListReq(this);
    }

    @Override
    public String getMsgName() {
        return "SecListReq";
    }

    public enum SecurityListRequestType {
        ONE_INSTRMT(0), //Jeden instrument (po ISIN lub nazwie)
        ONE_TYPE_LIST(1), //Lista jednego typu instrumentu
        FULL_LIST(4), //Cala lista
        ONE_MARKET_TYPE_LIST(5); //Lista dla jednego kodu rynku

        private SecurityListRequestType(int val) {
            this.value = val;
        }

        private final int value;

        public static SecurityListRequestType getSecurityListRequestType(int value) throws FixmlElementNotFound {
            for (SecurityListRequestType secReqType : values()) {
                if (secReqType.getValue() == value) {
                    return secReqType;
                }
            }
            throw new FixmlElementNotFound("Security List Request Type not found for value: " + value);
        }

        public int getValue() {
            return value;
        }
    }

    public Integer getListReqTyp() {
        return listReqTyp;
    }

    public SecurityListRequest setListReqTyp(Integer listReqTyp) {
        this.listReqTyp = listReqTyp;
        return this;
    }

    public List<Instrument> getInstrmts() {
        return instrmts;
    }

    public SecurityListRequest setInstrmts(List<Instrument> instrmts) {
        this.instrmts = instrmts;
        return this;
    }

    public String getMktId() {
        return mktId;
    }

    public SecurityListRequest setMktId(String mktId) {
        this.mktId = mktId;
        return this;
    }

    public String getReqId() {
        return reqId;
    }

    public SecurityListRequest setReqId(String reqId) {
        this.reqId = reqId;
        return this;
    }

}
