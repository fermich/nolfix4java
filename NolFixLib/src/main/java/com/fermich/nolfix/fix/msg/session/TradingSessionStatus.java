package com.fermich.nolfix.fix.msg.session;

import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.fermich.nolfix.fix.msg.common.Instrument;
import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * <TrdgSesStat ReqID="1" SesSub="3">
 * <Instrmt Sym="TPSA" ID=" PLTLKPL00017" Src="4" />
 * </TrdgSesStat>
 */
@XStreamAlias("TrdgSesStat")
public class TradingSessionStatus extends FixmlMessage {

    @XStreamAlias("ReqID")
    @XStreamAsAttribute
    private String reqId; //ID, 1 dzien

    @XStreamAlias("SesID")
    @XStreamAsAttribute
    private String sesId; //identyfikator sesji

    @XStreamAlias("SesSub")
    @XStreamAsAttribute
    private String sesSub; //faza podczas sesji (wartosci z SessionPhase)

    @XStreamAlias("Stat")
    @XStreamAsAttribute
    private String stat; //status sesji (wartosci z SessionState)

    @XStreamAlias("StatRejRsn")
    @XStreamAsAttribute
    private Integer statRejRsn; //powod odrzucenia zadania (wartosci z SessionRejectReason)

    @XStreamImplicit(itemFieldName = "Instrmt")
    private List<Instrument> instrmts; //instrumenty

    @Override
    public String getMsgName() {
        return "TrdgSesStat";
    }

    public enum SessionPhase {
        HALTED("HALT"),
        EARLY_MONITORING("EAMO"),
        CORE_CALL("COCA"),
        CORE_AUCTION("COAU"),
        CORE_CONTINUOUS("COCO"),
        CLOSING_CALL("CLCA"),
        CLOSING_AUCTION("CLAU"),
        LATE_TRADING_AT_LAST("LTAL"),
        CORE_TRADING_AT_LAST("CTAL"),
        CORE_MONITORING("COMO"),
        LATE_MONITORING("LAMO"),
        CLOSED("CLSD");

        private SessionPhase(String value) {
            this.value = value;
        }

        private String value;

        public static SessionPhase getSessionPhase(String value) throws FixmlElementNotFound {
            for (SessionPhase sesPhase : values()) {
                if (sesPhase.getValue().equals(value)) {
                    return sesPhase;
                }
            }
            throw new FixmlElementNotFound("Session Phase not found for value: " + value);
        }

        public String getValue() {
            return value;
        }

    }

    public enum SessionState {
        BALANCING("AR"), //Rownowazenie
        SEC_IN_USE("A"), //Papier w obrocie
        SEC_LOCKED("AG"), //Zamrozenie instrumentu
        NO_PLAY_OFF("IR"), //Bez dogrywki- zlecenia nie
        MARKET_CLOSED_AS("AS"), //Zakaz obrotu, zlecenia tak
        MARKET_CLOSED_IS("IS"), //Zakaz obrotu
        MARKET_CLOSED_I("I"); //Zakaz obrotu

        private SessionState(String value) {
            this.value = value;
        }

        private String value;

        public static SessionState getSessionState(String value) throws FixmlElementNotFound {
            for (SessionState sesState : values()) {
                if (sesState.getValue().equals(value)) {
                    return sesState;
                }
            }
            throw new FixmlElementNotFound("Session State not found for value: " + value);
        }

        public String getValue() {
            return value;
        }

    }

    public enum SessionRejectReason {
        WRONG_SESSION_ID(1), //Bledny ID sesji
        OTHER(99); //Inny

        private SessionRejectReason(Integer value) {
            this.value = value;
        }

        private Integer value;

        public static SessionRejectReason getSessionRejectReason(int value) throws FixmlElementNotFound {
            for (SessionRejectReason sesRejRsn : values()) {
                if (sesRejRsn.getValue() == value) {
                    return sesRejRsn;
                }
            }
            throw new FixmlElementNotFound("Session Reject Reason not found for value: " + value);
        }

        public Integer getValue() {
            return value;
        }
    }

    public String getReqId() {
        return reqId;
    }

    public TradingSessionStatus setReqId(String reqId) {
        this.reqId = reqId;
        return this;
    }

    public String getSesId() {
        return sesId;
    }

    public TradingSessionStatus setSesId(String sesId) {
        this.sesId = sesId;
        return this;
    }

    public String getSesSub() {
        return sesSub;
    }

    public TradingSessionStatus setSesSub(String sesSub) {
        this.sesSub = sesSub;
        return this;
    }

    public List<Instrument> getInstrmts() {
        return instrmts;
    }

    public TradingSessionStatus setInstrmts(List<Instrument> instrmts) {
        this.instrmts = instrmts;
        return this;
    }

    public String getStat() {
        return stat;
    }

    public TradingSessionStatus setStat(String stat) {
        this.stat = stat;
        return this;
    }

    public Integer getStatRejRsn() {
        return statRejRsn;
    }

    public TradingSessionStatus setStatRejRsn(Integer statRejRsn) {
        this.statRejRsn = statRejRsn;
        return this;
    }

    @Override
    public String toString() {
        return String.format("TradingSessionStatus{reqId='%s', sesId='%s', sesSub='%s', stat='%s', statRejRsn=%d, instrmts=%s}",
                reqId, sesId, sesSub, stat, statRejRsn, instrmts);
    }

}
