package com.fermich.nolfix.fix.msg.order;

import com.fermich.nolfix.fix.msg.Fixml;
import com.fermich.nolfix.fix.msg.FixmlRequest;
import com.fermich.nolfix.fix.msg.common.Instrument;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Order modification
 * <OrdCxlRplcReq ID="2" OrdID="166278462" TrdDt="20090722" TmInForce="0" Acct="00-55-006638"
 * Side="1" TxnTm="20090722-11:34:32" OrdTyp="L" Px="24.70" >
 * <Instrmt ID="PLCOMAR00012" Src="4"/>
 * <OrdQty Qty="3"/>
 * </OrdCxlRplcReq>
 */
@XStreamAlias("OrdCxlRplcReq")
public class OrderCancelReplaceRequest extends FixmlRequest {

    @XStreamAlias("TrdDt")
    @XStreamAsAttribute
    private String trdDt; //dzien sesji YYYYMMDD

    @XStreamAlias("ID")
    @XStreamAsAttribute
    private String id; //ID zlecenia

    @XStreamAlias("OrigID")
    @XStreamAsAttribute
    private String origId; //ID zlecenia nadawane przez bilioteke kliencka. Wazne przez jedna sesje API.

    @XStreamAlias("OrdID")
    @XStreamAsAttribute
    private String ordId; //ID zlecenia nadawane przez DM

    @XStreamAlias("OrdID2")
    @XStreamAsAttribute
    private String ordId2; //Numer zlecenia nadawny przez DM

    @XStreamAlias("Acct")
    @XStreamAsAttribute
    private String acct; //Numer konta

    @XStreamAlias("MinQty")
    @XStreamAsAttribute
    private Float minQty; //wartosc minimalna

    @XStreamAlias("DsplyInstr")
    private DisplayInstruction dsplyInstr; //ilosc ujawniona

    @XStreamAlias("Instrmt")
    private Instrument instrmt; //instrument

    @XStreamAlias("Side")
    @XStreamAsAttribute
    private Character side; //strona - wartosci z SiteType

    @XStreamAlias("TxnTm")
    @XStreamAsAttribute
    private String txnTm; //czas transakcji YYYYMMDD-HH:MM:SS

    @XStreamAlias("OrdQty")
    private OrderQtyData ordQty; //ilosc

    @XStreamAlias("OrdTyp")
    @XStreamAsAttribute
    private Character ordTyp; //ordTyp zlecenia (wartosci z OrderType)

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
    private Character tmInForce; //ordTyp daty

    @XStreamAlias("ExpireDt")
    @XStreamAsAttribute
    private String expireDt; //data waznosci YYYYMMDD

    @XStreamAlias("ExpireTm")
    @XStreamAsAttribute
    private String expireTm; // hh:mm:ss

    @XStreamAlias("Txt")
    @XStreamAsAttribute
    private String txt; //dowolny tekst

    @XStreamAlias("TrgrInstr")
    private TrigerringInstruction trgrInstr; //zlecenia DDM+

    @XStreamAlias("DefPayTyp")
    @XStreamAsAttribute
    private Character defPayTyp; //OTP

    @Override
    public String getMsgName() {
        return "OrdCxlRplcReq";
    }

    @Override
    public Fixml pack() {
        return super.pack().addOrderRplcReq(this);
    }

    public String getTrdDt() {
        return trdDt;
    }

    public OrderCancelReplaceRequest setTrdDt(String trdDt) {
        this.trdDt = trdDt;
        return this;
    }

    public String getId() {
        return id;
    }

    public OrderCancelReplaceRequest setId(String id) {
        this.id = id;
        return this;
    }

    public String getOrigId() {
        return origId;
    }

    public OrderCancelReplaceRequest setOrigId(String origId) {
        this.origId = origId;
        return this;
    }

    public String getOrdId() {
        return ordId;
    }

    public OrderCancelReplaceRequest setOrdId(String ordId) {
        this.ordId = ordId;
        return this;
    }

    public String getOrdId2() {
        return ordId2;
    }

    public OrderCancelReplaceRequest setOrdId2(String ordId2) {
        this.ordId2 = ordId2;
        return this;
    }

    public String getAcct() {
        return acct;
    }

    public OrderCancelReplaceRequest setAcct(String acct) {
        this.acct = acct;
        return this;
    }

    public Float getMinQty() {
        return minQty;
    }

    public OrderCancelReplaceRequest setMinQty(Float minQty) {
        this.minQty = minQty;
        return this;
    }

    public DisplayInstruction getDsplyInstr() {
        return dsplyInstr;
    }

    public OrderCancelReplaceRequest setDsplyInstr(DisplayInstruction dsplyInstr) {
        this.dsplyInstr = dsplyInstr;
        return this;
    }

    public Instrument getInstrmt() {
        return instrmt;
    }

    public OrderCancelReplaceRequest setInstrmt(Instrument instrmt) {
        this.instrmt = instrmt;
        return this;
    }

    public Character getSide() {
        return side;
    }

    public OrderCancelReplaceRequest setSide(Character side) {
        this.side = side;
        return this;
    }

    public String getTxnTm() {
        return txnTm;
    }

    public OrderCancelReplaceRequest setTxnTm(String txnTm) {
        this.txnTm = txnTm;
        return this;
    }

    public OrderQtyData getOrdQty() {
        return ordQty;
    }

    public OrderCancelReplaceRequest setOrdQty(OrderQtyData ordQty) {
        this.ordQty = ordQty;
        return this;
    }

    public Character getOrdTyp() {
        return ordTyp;
    }

    public OrderCancelReplaceRequest setOrdTyp(Character ordTyp) {
        this.ordTyp = ordTyp;
        return this;
    }

    public String getPx() {
        return px;
    }

    public OrderCancelReplaceRequest setPx(String px) {
        this.px = px;
        return this;
    }

    public Float getStopPx() {
        return stopPx;
    }

    public OrderCancelReplaceRequest setStopPx(Float stopPx) {
        this.stopPx = stopPx;
        return this;
    }

    public String getCcy() {
        return ccy;
    }

    public OrderCancelReplaceRequest setCcy(String ccy) {
        this.ccy = ccy;
        return this;
    }

    public Character getTmInForce() {
        return tmInForce;
    }

    public OrderCancelReplaceRequest setTmInForce(Character tmInForce) {
        this.tmInForce = tmInForce;
        return this;
    }

    public String getExpireDt() {
        return expireDt;
    }

    public OrderCancelReplaceRequest setExpireDt(String expireDt) {
        this.expireDt = expireDt;
        return this;
    }

    public String getExpireTm() {
        return expireTm;
    }

    public OrderCancelReplaceRequest setExpireTm(String expireTm) {
        this.expireTm = expireTm;
        return this;
    }

    public String getTxt() {
        return txt;
    }

    public OrderCancelReplaceRequest setTxt(String txt) {
        this.txt = txt;
        return this;
    }

    public TrigerringInstruction getTrgrInstr() {
        return trgrInstr;
    }

    public OrderCancelReplaceRequest setTrgrInstr(TrigerringInstruction trgrInstr) {
        this.trgrInstr = trgrInstr;
        return this;
    }

    public Character getDefPayTyp() {
        return defPayTyp;
    }

    public OrderCancelReplaceRequest setDefPayTyp(Character defPayTyp) {
        this.defPayTyp = defPayTyp;
        return this;
    }

    @Override
    public String toString() {
        return "OrderCancelReplaceRequest [trdDt=" + trdDt + ", id=" + id
                + ", origId=" + origId + ", ordId=" + ordId + ", ordId2="
                + ordId2 + ", acct=" + acct + ", minQty=" + minQty
                + ", dsplyInstr=" + dsplyInstr + ", instrmt=" + instrmt
                + ", side=" + side + ", txnTm=" + txnTm + ", ordQty=" + ordQty
                + ", ordTyp=" + ordTyp + ", px=" + px + ", stopPx=" + stopPx
                + ", ccy=" + ccy + ", tmInForce=" + tmInForce + ", expireDt="
                + expireDt + ", txt=" + txt + ", trgrInstr=" + trgrInstr
                + ", defPayTyp=" + defPayTyp + "]";
    }
}
