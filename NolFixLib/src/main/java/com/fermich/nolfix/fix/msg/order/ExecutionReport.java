package com.fermich.nolfix.fix.msg.order;

import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.fermich.nolfix.fix.msg.common.Instrument;
import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Asynchronous response to NewOrderSingle
 * <ExecRpt OrdID="1" OrdID2="3" ID="2" Stat="4" Acct="00-55-006638" side="1"
 * TxnTm="20080910" OrdTyp="L" Px="200" Ccy="PLN" >
 * <Instrument Symbol= SymbolSfx="WI" SecurityID=" " SecurityIDSource="4" />
 * <OrderQtyData OrderQty= „200"/>
 */
@XStreamAlias("ExecRpt")
public class ExecutionReport extends FixmlMessage {

    @XStreamAlias("OrdID")
    @XStreamAsAttribute
    private String ordId; //ID zlecenia nadawany przez DM

    @XStreamAlias("OrdID2")
    @XStreamAsAttribute
    private String ordId2; //numer zlecenia nadawny przez DM

    @XStreamAlias("ID")
    @XStreamAsAttribute
    private String id; //ID zlecenia nadawane przez klienta lub biblioteke kliencka

    @XStreamAlias("StatReqID")
    @XStreamAsAttribute
    private String statReqId; //identyfikator requestu z OrderStatusRequest

    @XStreamAlias("ExecID")
    @XStreamAsAttribute
    private String execId; //ID wykonania zlecenia

    @XStreamAlias("ExecTyp")
    @XStreamAsAttribute
    private Character execTyp; //Typ wykonania (wartosci z ExecType)

    @XStreamAlias("Stat")
    @XStreamAsAttribute
    private Character stat; //status zlecenia (wartosci z OrderStatus)

    @XStreamAlias("RejRsn")
    @XStreamAsAttribute
    private Integer rejRsn; //powod odrzucenia zlecenia (99 - inny)

    @XStreamAlias("Acct")
    @XStreamAsAttribute
    private String acct; //konto

    @XStreamImplicit(itemFieldName = "Instrmt")
    private List<Instrument> instrmts; //instrument

    @XStreamAlias("Side")
    @XStreamAsAttribute
    private Character side; //strona (wartosci z SideType)

    @XStreamAlias("OrdQty")
    private OrderQtyData ordQty; //ilosc

    @XStreamAlias("OrdTyp")
    @XStreamAsAttribute
    private Character typ; //typ zlecenia (wartosci z OrderType)

    @XStreamAlias("Px")
    @XStreamAsAttribute
    private String px; //cena

    @XStreamAlias("StopPx")
    @XStreamAsAttribute
    private Float stopPx; //limit aktywacji

    @XStreamAlias("Ccy")
    @XStreamAsAttribute
    private String ccy; //waluta

    @XStreamAlias("TmInForce")
    @XStreamAsAttribute
    private Character tmInForce; //typ daty (wartosci z TimeInForce)

    @XStreamAlias("ExpireDt")
    @XStreamAsAttribute
    private String expireDt; //dla TmInForce=6, YYYYMMDD

    @XStreamAlias("LastPx")
    @XStreamAsAttribute
    private Float lastPx; //cena ostatniej transakcji

    @XStreamAlias("LastQty")
    @XStreamAsAttribute
    private Float lastQty; //ilosc zrealizowana podczas ostatniej transakcji

    @XStreamAlias("LeavesQty")
    @XStreamAsAttribute
    private Float leavesQty; //ilosc pozostala w zleceniu

    @XStreamAlias("CumQty")
    @XStreamAsAttribute
    private Float cumQty; //ilosc wypelniona w zleceniu

    @XStreamAlias("TxnTm")
    @XStreamAsAttribute
    private String txnTm; //czas transakcji YYYYMMDD-HH:MM:SS

    @XStreamImplicit(itemFieldName = "Comm")
    private List<CommisionData> comms; //prowizje 

    @XStreamAlias("NetMny")
    @XStreamAsAttribute
    private Float netMny; //wartosc netto transakcji / zlecenia

    @XStreamAlias("MinQty")
    @XStreamAsAttribute
    private Float minQty; //wartosc minimalna

    @XStreamImplicit(itemFieldName = "DsplyInstr")
    private List<DisplayInstruction> dsplyInstrs; //ilosc ujawniona

    @XStreamAlias("Text")
    @XStreamAsAttribute
    private String text; //dowolny tekst

    @XStreamImplicit(itemFieldName = "TrgrInstr")
    private List<TrigerringInstruction> trgrInstrs; //zlecenia DDM+

    @XStreamAlias("DefPayTyp")
    @XStreamAsAttribute
    private Character defPayTyp; //OTP (deferred payment type)

    @Override
    public String getMsgName() {
        return "ExecRpt";
    }

    @XStreamAlias("Comm")
    public static class CommisionData {
        @XStreamAlias("Comm")
        @XStreamAsAttribute
        private float comm; //prowizja dla transakcji / zlecenia

        @XStreamAlias("CommTyp")
        @XStreamAsAttribute
        private char commTyp;  //typ prowizji (wartosci z CommType)

        public enum CommType {
            FACTOR('1'), //Na jednostke
            PERCENT('2'), //Procent
            ABS('3'); //Wartosc absolutna

            private CommType(char value) {
                this.value = value;
            }

            public static CommType getCommType(char value) throws FixmlElementNotFound {
                for (CommType item : values()) {
                    if (item.getValue() == value) {
                        return item;
                    }
                }
                throw new FixmlElementNotFound("Commission Type not found for value: " + value);
            }

            private char value;

            public char getValue() {
                return value;
            }
        }

        public float getComm() {
            return comm;
        }

        public CommisionData setComm(float comm) {
            this.comm = comm;
            return this;
        }

        public char getCommTyp() {
            return commTyp;
        }

        public CommisionData setCommTyp(char commTyp) {
            this.commTyp = commTyp;
            return this;
        }
    }

    public enum ExecType {
        NEW('0'), //Nowy
        TRANSACTION('F'), //Transakcja
        CANCELLATION('4'), //Anulowanie
        MODIFIED('E'), //Modyfikacja
        BEING_CANCELLED('6'), //W trakcie anulowania
        REJECTED('8'), //Odrzucone
        ORDER_STATUS('I'); //Status zlecenia

        private ExecType(char value) {
            this.value = value;
        }

        public static ExecType getExecType(char value) throws FixmlElementNotFound {
            for (ExecType item : values()) {
                if (item.getValue() == value) {
                    return item;
                }
            }
            throw new FixmlElementNotFound("Execution Type not found for value: " + value);
        }

        private char value;

        public char getValue() {
            return value;
        }
    }


    public String getAcct() {
        return acct;
    }

    public ExecutionReport setAcct(String acct) {
        this.acct = acct;
        return this;
    }

    public String getCcy() {
        return ccy;
    }

    public ExecutionReport setCcy(String ccy) {
        this.ccy = ccy;
        return this;
    }

    public List<CommisionData> getComms() {
        return comms;
    }

    public ExecutionReport setComms(List<CommisionData> comms) {
        this.comms = comms;
        return this;
    }

    public Float getCumQty() {
        return cumQty;
    }

    public ExecutionReport setCumQty(Float cumQty) {
        this.cumQty = cumQty;
        return this;
    }

    public Character getDefPayTyp() {
        return defPayTyp;
    }

    public ExecutionReport setDefPayTyp(Character defPayTyp) {
        this.defPayTyp = defPayTyp;
        return this;
    }

    public List<DisplayInstruction> getDsplyInstrs() {
        return dsplyInstrs;
    }

    public ExecutionReport setDsplyInstrs(List<DisplayInstruction> dsplyInstrs) {
        this.dsplyInstrs = dsplyInstrs;
        return this;
    }

    public String getExecId() {
        return execId;
    }

    public ExecutionReport setExecId(String execId) {
        this.execId = execId;
        return this;
    }

    public Character getExecTyp() {
        return execTyp;
    }

    public ExecutionReport setExecTyp(Character execTyp) {
        this.execTyp = execTyp;
        return this;
    }

    public String getExpireDt() {
        return expireDt;
    }

    public ExecutionReport setExpireDt(String expireDt) {
        this.expireDt = expireDt;
        return this;
    }

    public String getId() {
        return id;
    }

    public ExecutionReport setId(String id) {
        this.id = id;
        return this;
    }

    public List<Instrument> getInstrmts() {
        return instrmts;
    }

    public ExecutionReport setInstrmts(List<Instrument> instrmts) {
        this.instrmts = instrmts;
        return this;
    }

    public Float getLastPx() {
        return lastPx;
    }

    public ExecutionReport setLastPx(Float lastPx) {
        this.lastPx = lastPx;
        return this;
    }

    public Float getLastQty() {
        return lastQty;
    }

    public ExecutionReport setLastQty(Float lastQty) {
        this.lastQty = lastQty;
        return this;
    }

    public Float getLeavesQty() {
        return leavesQty;
    }

    public ExecutionReport setLeavesQty(Float leavesQty) {
        this.leavesQty = leavesQty;
        return this;
    }

    public Float getMinQty() {
        return minQty;
    }

    public ExecutionReport setMinQty(Float minQty) {
        this.minQty = minQty;
        return this;
    }

    public Float getNetMny() {
        return netMny;
    }

    public ExecutionReport setNetMny(Float netMny) {
        this.netMny = netMny;
        return this;
    }

    public String getOrdId() {
        return ordId;
    }

    public ExecutionReport setOrdId(String ordId) {
        this.ordId = ordId;
        return this;
    }

    public String getOrdId2() {
        return ordId2;
    }

    public ExecutionReport setOrdId2(String ordId2) {
        this.ordId2 = ordId2;
        return this;
    }

    public OrderQtyData getOrdQty() {
        return ordQty;
    }

    public ExecutionReport setOrdQty(OrderQtyData ordQty) {
        this.ordQty = ordQty;
        return this;
    }

    public String getPx() {
        return px;
    }

    public ExecutionReport setPx(String px) {
        this.px = px;
        return this;
    }

    public Integer getRejRsn() {
        return rejRsn;
    }

    public ExecutionReport setRejRsn(Integer rejRsn) {
        this.rejRsn = rejRsn;
        return this;
    }

    public Character getSide() {
        return side;
    }

    public ExecutionReport setSide(Character side) {
        this.side = side;
        return this;
    }

    public Character getStat() {
        return stat;
    }

    public ExecutionReport setStat(Character stat) {
        this.stat = stat;
        return this;
    }

    public String getStatReqId() {
        return statReqId;
    }

    public ExecutionReport setStatReqId(String statReqId) {
        this.statReqId = statReqId;
        return this;
    }

    public Float getStopPx() {
        return stopPx;
    }

    public ExecutionReport setStopPx(Float stopPx) {
        this.stopPx = stopPx;
        return this;
    }

    public String getText() {
        return text;
    }

    public ExecutionReport setText(String text) {
        this.text = text;
        return this;
    }

    public Character getTmInForce() {
        return tmInForce;
    }

    public ExecutionReport setTmInForce(Character tmInForce) {
        this.tmInForce = tmInForce;
        return this;
    }

    public List<TrigerringInstruction> getTrgrInstrs() {
        return trgrInstrs;
    }

    public ExecutionReport setTrgrInstrs(List<TrigerringInstruction> trgrInstrs) {
        this.trgrInstrs = trgrInstrs;
        return this;
    }

    public String getTxnTm() {
        return txnTm;
    }

    public ExecutionReport setTxnTm(String txnTm) {
        this.txnTm = txnTm;
        return this;
    }

    public Character getTyp() {
        return typ;
    }

    public ExecutionReport setTyp(Character typ) {
        this.typ = typ;
        return this;
    }

    @Override
    public String toString() {
        return "ExecutionReport{" +
                "ordId='" + ordId + '\'' +
                ", ordId2='" + ordId2 + '\'' +
                ", id='" + id + '\'' +
                ", statReqId='" + statReqId + '\'' +
                ", execId='" + execId + '\'' +
                ", execTyp=" + execTyp +
                ", stat=" + stat +
                ", rejRsn=" + rejRsn +
                ", acct='" + acct + '\'' +
                ", instrmts=" + instrmts +
                ", side=" + side +
                ", ordQty=" + ordQty +
                ", typ=" + typ +
                ", px=" + px +
                ", stopPx=" + stopPx +
                ", ccy='" + ccy + '\'' +
                ", tmInForce=" + tmInForce +
                ", expireDt='" + expireDt + '\'' +
                ", lastPx=" + lastPx +
                ", lastQty=" + lastQty +
                ", leavesQty=" + leavesQty +
                ", cumQty=" + cumQty +
                ", txnTm='" + txnTm + '\'' +
                ", comms=" + comms +
                ", netMny=" + netMny +
                ", minQty=" + minQty +
                ", dsplyInstrs=" + dsplyInstrs +
                ", text='" + text + '\'' +
                ", trgrInstrs=" + trgrInstrs +
                ", defPayTyp=" + defPayTyp +
                '}';
    }
}
