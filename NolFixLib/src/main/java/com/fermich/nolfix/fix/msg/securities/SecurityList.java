package com.fermich.nolfix.fix.msg.securities;

import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.fermich.nolfix.fix.msg.common.Instrument;
import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

/**
 * List containing securities
 * <SecList RptID="0" ReqID="0" MktID="WE" ReqRslt="0" TotNoReltdSym="1">
 * <SecL>
 * <Instrmt Sym="COMARCH" Src="4" ID="PLCOMAR00012" SecGrp="C7" CFI="ESXXXX"/>
 * </SecL>
 * </SecList>
 */
@XStreamAlias("SecList")
public class SecurityList extends FixmlMessage {

    @XStreamAlias("RptID")
    @XStreamAsAttribute
    private Integer rptId;          //ID

    @XStreamAlias("ReqID")
    @XStreamAsAttribute
    private String reqId;    //ID zlecenia

    @XStreamAlias("MktID")
    @XStreamAsAttribute
    private String mktId;    //kod rynku, wartosci z MarketID

    @XStreamAlias("TotNoReltdSym")
    @XStreamAsAttribute
    private Integer totNoReltdSym;    //ilosc instrumentow

    @XStreamAlias("ReqRslt")
    @XStreamAsAttribute
    private Integer reqRslt;    //wartosci z SecurityRequestResult

    @XStreamAlias("SecL")
    List<Instrument> instrmts;

    @Override
    public String getMsgName() {
        return "SecList";
    }

    public enum SecurityRequestResult {
        RESP_OK(0), //Poprawna odpowiedz
        NO_SEC(1), //Brak papieru
        NO_SEC_LIST(4), //Brak listy instrumentow
        NO_SEC_LIST_OF_TYPE(5); //Brak papierow danego typu

        private final int value;

        private SecurityRequestResult(int val) {
            this.value = val;
        }

        public static SecurityRequestResult getSecurityRequestResult(int value) throws FixmlElementNotFound {
            for (SecurityRequestResult secReqRes : values()) {
                if (secReqRes.getValue() == value) {
                    return secReqRes;
                }
            }
            throw new FixmlElementNotFound("Security Request Result not found for value: " + value);
        }

        public int getValue() {
            return value;
        }
    }

    public List<Instrument> getInstrmts() {
        return instrmts;
    }

    public SecurityList setInstrmts(List<Instrument> instrmts) {
        this.instrmts = instrmts;
        return this;
    }

    public String getMktId() {
        return mktId;
    }

    public SecurityList setMktId(String mktId) {
        this.mktId = mktId;
        return this;
    }

    public String getReqId() {
        return reqId;
    }

    public SecurityList setReqId(String reqId) {
        this.reqId = reqId;
        return this;
    }

    public Integer getReqRstl() {
        return reqRslt;
    }

    public SecurityList setReqRstl(Integer reqRstl) {
        this.reqRslt = reqRstl;
        return this;
    }

    public Integer getRptId() {
        return rptId;
    }

    public SecurityList setRptId(Integer rptId) {
        this.rptId = rptId;
        return this;
    }

    public Integer getTotNoReltdSym() {
        return totNoReltdSym;
    }

    public SecurityList setTotNoReltdSym(Integer totNoReltdSym) {
        this.totNoReltdSym = totNoReltdSym;
        return this;
    }

    @Override
    public String toString() {
        return String.format("SecurityList{rptId=%d, reqId='%s', mktId='%s', totNoReltdSym=%d, reqRslt=%d, instrmts=%s}",
                rptId, reqId, mktId, totNoReltdSym, reqRslt, instrmts);
    }
}
