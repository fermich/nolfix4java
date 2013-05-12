package com.fermich.nolfix.fix.msg.order;

import com.fermich.nolfix.fix.msg.Fixml;
import com.fermich.nolfix.fix.msg.FixmlRequest;
import com.fermich.nolfix.fix.msg.common.Instrument;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Single order
 * <Order ID="2" TrdDt="20110307"
 * Acct="00-55-006638" Side="1" TxnTm="20110307-11:34:32" OrdTyp="L" Px="0.15"
 * StopPx="199" Ccy="PLN" TmInForce="6" ExpireDt="20110331">
 * <DsplyInstr DisplayQty="100" />
 * <Instrmt ID="PLCOMAR00012" Src="4"/>
 * <OrdQty Qty="200"/>
 * </Order>
 */
@XStreamAlias("Order")
public class NewOrderSingle extends FixmlRequest {

    @XStreamAlias("ID")
    @XStreamAsAttribute
    private String id;        //ID zlecenia

    @XStreamAlias("TrdDt")
    @XStreamAsAttribute
    private String trdDt;    //dzien sesji

    @XStreamAlias("Acct")
    @XStreamAsAttribute
    private String acct;    //nr konta

    @XStreamAlias("MinQty")
    @XStreamAsAttribute
    private Float minQty;    //ilosc minimalna

    @XStreamAlias("DsplyInstr")
    private DisplayInstruction dsplyInstr; //wartosc ujawniona

    @XStreamAlias("Instrmt")
    private Instrument instrmt; //instrument

    @XStreamAlias("Side")
    @XStreamAsAttribute
    private Character side; //strona (wartosc z SideType)

    @XStreamAlias("TxnTm")
    @XStreamAsAttribute
    private String txnTm; //czas wygenerowania zlecenia przez klienta YYYYMMDD-HH:MM:SS

    @XStreamAlias("OrdQty")
    private OrderQtyData ordQty; //ilosc

    @XStreamAlias("OrdTyp")
    @XStreamAsAttribute
    private Character ordTyp; //typ zlecenia (wartosci z OrderType)

    @XStreamAlias("Px")
    @XStreamAsAttribute
    private Float px; //cena

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
    private String expireDt; //data waznosci (do) YYYYMMDD

    @XStreamAlias("TrgrInstr")
    private TrigerringInstruction trgrInstr;

    @XStreamAlias("DefPayTyp")
    @XStreamAsAttribute
    private Character defPayTyp; //OTP

    @Override
    public String getMsgName() {
        return "Order";
    }

    @Override
    public Fixml pack() {
        return super.pack().addNewOrder(this);
    }

    public String getId() {
        return id;
    }

    public NewOrderSingle setId(String id) {
        this.id = id;
        return this;
    }

    public String getTrdDt() {
        return trdDt;
    }

    public NewOrderSingle setTrdDt(String trdDt) {
        this.trdDt = trdDt;
        return this;
    }

    public String getAcct() {
        return acct;
    }

    public NewOrderSingle setAcct(String acct) {
        this.acct = acct;
        return this;
    }

    public Float getMinQty() {
        return minQty;
    }

    public NewOrderSingle setMinQty(Float minQty) {
        this.minQty = minQty;
        return this;
    }

    public DisplayInstruction getDsplyInstr() {
        return dsplyInstr;
    }

    public NewOrderSingle setDsplyInstr(DisplayInstruction dsplyInstr) {
        this.dsplyInstr = dsplyInstr;
        return this;
    }

    public Instrument getInstrmt() {
        return instrmt;
    }

    public NewOrderSingle setInstrmt(Instrument instrmt) {
        this.instrmt = instrmt;
        return this;
    }

    public Character getSide() {
        return side;
    }

    public NewOrderSingle setSide(Character side) {
        this.side = side;
        return this;
    }

    public String getTxnTm() {
        return txnTm;
    }

    public NewOrderSingle setTxnTm(String txnTm) {
        this.txnTm = txnTm;
        return this;
    }

    public OrderQtyData getOrdQty() {
        return ordQty;
    }

    public NewOrderSingle setOrdQty(OrderQtyData ordQty) {
        this.ordQty = ordQty;
        return this;
    }

    public Character getOrdTyp() {
        return ordTyp;
    }

    public NewOrderSingle setOrdTyp(Character ordTyp) {
        this.ordTyp = ordTyp;
        return this;
    }

    public Float getPx() {
        return px;
    }

    public NewOrderSingle setPx(Float px) {
        this.px = px;
        return this;
    }

    public Float getStopPx() {
        return stopPx;
    }

    public NewOrderSingle setStopPx(Float stopPx) {
        this.stopPx = stopPx;
        return this;
    }

    public String getCcy() {
        return ccy;
    }

    public NewOrderSingle setCcy(String ccy) {
        this.ccy = ccy;
        return this;
    }

    public Character getTmInForce() {
        return tmInForce;
    }

    public NewOrderSingle setTmInForce(Character tmInForce) {
        this.tmInForce = tmInForce;
        return this;
    }

    public String getExpireDt() {
        return expireDt;
    }

    public NewOrderSingle setExpireDt(String expireDt) {
        this.expireDt = expireDt;
        return this;
    }

    public TrigerringInstruction getTrgrInstr() {
        return trgrInstr;
    }

    public NewOrderSingle setTrgrInstr(TrigerringInstruction trgrInstr) {
        this.trgrInstr = trgrInstr;
        return this;
    }

    public Character getDefPayTyp() {
        return defPayTyp;
    }

    public NewOrderSingle setDefPayTyp(Character defPayTyp) {
        this.defPayTyp = defPayTyp;
        return this;
    }

    @Override
    public String toString() {
        return "NewOrderSingle{" +
                "id='" + id + '\'' +
                ", trdDt='" + trdDt + '\'' +
                ", acct='" + acct + '\'' +
                ", minQty=" + minQty +
                ", dsplyInstr=" + dsplyInstr +
                ", instrmt=" + instrmt +
                ", side=" + side +
                ", txnTm='" + txnTm + '\'' +
                ", ordQty=" + ordQty +
                ", ordTyp=" + ordTyp +
                ", px=" + px +
                ", stopPx=" + stopPx +
                ", ccy='" + ccy + '\'' +
                ", tmInForce=" + tmInForce +
                ", expireDt='" + expireDt + '\'' +
                ", trgrInstr=" + trgrInstr +
                ", defPayTyp=" + defPayTyp +
                '}';
    }
}
