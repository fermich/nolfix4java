package com.fermich.nolfix.fix.msg.order;

import com.fermich.nolfix.fix.msg.Fixml;
import com.fermich.nolfix.fix.msg.FixmlRequest;
import com.fermich.nolfix.fix.msg.common.Instrument;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Order cancellation message
 * <OrdCxlReq ID="1" OrdID="168193517" Acct="00-55-006638" Side="1"
 * TxnTm="20100628-08:01:23">
 * <Instrmt Sym="COMARCH" />
 * <OrdQty Qty="1"/>
 * </OrdCxlReq>
 */
@XStreamAlias("OrdCxlReq")
public class OrderCancelRequest extends FixmlRequest {

    @XStreamAlias("ID")
    @XStreamAsAttribute
    private String id; //ID anulaty

    @XStreamAlias("OrigID")
    @XStreamAsAttribute
    private String origId; //ID zlecenia nadawane przez klienta lub bilioteke kliencka, wazne przez jedna sesje API

    @XStreamAlias("OrdID")
    @XStreamAsAttribute
    private String ordId; //ID zlecenia nadawany przez DM

    @XStreamAlias("OrdID2")
    @XStreamAsAttribute
    private String ordId2; //Numer zlecenia nadawany przez DM

    @XStreamAlias("Acct")
    @XStreamAsAttribute
    private String acct; //numer rachunku

    @XStreamAlias("Instrmt")
    private Instrument instrmt; //instrument

    @XStreamAlias("Side")
    @XStreamAsAttribute
    private Character side; //strona (wartosci z SideType)

    @XStreamAlias("TxnTm")
    @XStreamAsAttribute
    private String txnTm; //czas utworzenia zlecenia YYYYMMDD-HH:MM:SS

    @XStreamAlias("OrdQty")
    private OrderQtyData ordQty; //ilosc

    @XStreamAlias("Txt")
    @XStreamAsAttribute
    private String txt; //dowolny tekst

    @Override
    public String getMsgName() {
        return "OrdCxlReq";
    }

    @Override
    public Fixml pack() {
        return super.pack().addOrderCncReqs(this);
    }

    public String getId() {
        return id;
    }

    public OrderCancelRequest setId(String id) {
        this.id = id;
        return this;
    }

    public String getOrigId() {
        return origId;
    }

    public OrderCancelRequest setOrigId(String origId) {
        this.origId = origId;
        return this;
    }

    public String getOrdId() {
        return ordId;
    }

    public OrderCancelRequest setOrdId(String ordId) {
        this.ordId = ordId;
        return this;
    }

    public String getOrdId2() {
        return ordId2;
    }

    public OrderCancelRequest setOrdId2(String ordId2) {
        this.ordId2 = ordId2;
        return this;
    }

    public String getAcct() {
        return acct;
    }

    public OrderCancelRequest setAcct(String acct) {
        this.acct = acct;
        return this;
    }

    public Instrument getInstrmt() {
        return instrmt;
    }

    public OrderCancelRequest setInstrmt(Instrument instrmt) {
        this.instrmt = instrmt;
        return this;
    }

    public Character getSide() {
        return side;
    }

    public OrderCancelRequest setSide(Character side) {
        this.side = side;
        return this;
    }

    public String getTxnTm() {
        return txnTm;
    }

    public OrderCancelRequest setTxnTm(String txnTm) {
        this.txnTm = txnTm;
        return this;
    }

    public OrderQtyData getOrdQty() {
        return ordQty;
    }

    public OrderCancelRequest setOrdQty(OrderQtyData ordQty) {
        this.ordQty = ordQty;
        return this;
    }

    public String getTxt() {
        return txt;
    }

    public OrderCancelRequest setTxt(String txt) {
        this.txt = txt;
        return this;
    }

    @Override
    public String toString() {
        return "OrderCancelRequest [id=" + id + ", origId=" + origId
                + ", ordId=" + ordId + ", ordId2=" + ordId2 + ", acct=" + acct
                + ", instrmt=" + instrmt + ", side=" + side + ", txnTm="
                + txnTm + ", ordQty=" + ordQty + ", txt=" + txt + "]";
    }
}
