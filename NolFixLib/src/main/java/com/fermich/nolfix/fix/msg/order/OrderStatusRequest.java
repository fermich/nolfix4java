package com.fermich.nolfix.fix.msg.order;

import com.fermich.nolfix.fix.msg.Fixml;
import com.fermich.nolfix.fix.msg.FixmlRequest;
import com.fermich.nolfix.fix.msg.common.Instrument;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Query for order status
 * <OrdStatReq StatReqID="1" OrdID="23212" Acct="00-55-006638" Side="1" >
 * <Instrmt Sym="COMARCH" />
 * </OrdStatReq>
 */
@XStreamAlias("OrdStatReq")
public class OrderStatusRequest extends FixmlRequest {

    @XStreamAlias("OrdID")
    @XStreamAsAttribute
    private String ordId; //ID zlecenia nadawane przez DM o ktorego status sie pytamy

    @XStreamAlias("OrigID")
    @XStreamAsAttribute
    private String origId; //ID zlecenia nadawane przez klienta lub biblioteke kliencka. Wazne przez jedna sesje API

    @XStreamAlias("ID")
    @XStreamAsAttribute
    private String id; //ID zlecenia nadawany przez klienta lub biblioteke kliencka

    @XStreamAlias("StatReqID")
    @XStreamAsAttribute
    private String statReqId; //Identyfikator requestu o status

    @XStreamAlias("Acct")
    @XStreamAsAttribute
    private String acct; //nr konta

    @XStreamAlias("Instrmt")
    private Instrument instrmt; //instrument

    @XStreamAlias("Side")
    @XStreamAsAttribute
    private Character side; //strona (wartosci z SideType)

    @Override
    public String getMsgName() {
        return "OrdStatReq";
    }

    @Override
    public Fixml pack() {
        return super.pack().addOrderStatReq(this);
    }

    public String getOrdId() {
        return ordId;
    }

    public OrderStatusRequest setOrdId(String ordId) {
        this.ordId = ordId;
        return this;
    }

    public String getOrigId() {
        return origId;
    }

    public OrderStatusRequest setOrigId(String origId) {
        this.origId = origId;
        return this;
    }

    public String getId() {
        return id;
    }

    public OrderStatusRequest setId(String id) {
        this.id = id;
        return this;
    }

    public String getStatReqId() {
        return statReqId;
    }

    public OrderStatusRequest setStatReqId(String statReqId) {
        this.statReqId = statReqId;
        return this;
    }

    public String getAcct() {
        return acct;
    }

    public OrderStatusRequest setAcct(String acct) {
        this.acct = acct;
        return this;
    }

    public Instrument getInstrmt() {
        return instrmt;
    }

    public OrderStatusRequest setInstrmt(Instrument instrmt) {
        this.instrmt = instrmt;
        return this;
    }

    public Character getSide() {
        return side;
    }

    public OrderStatusRequest setSide(Character side) {
        this.side = side;
        return this;
    }

    @Override
    public String toString() {
        return "OrderStatusRequest [ordId=" + ordId + ", origId=" + origId
                + ", id=" + id + ", statReqId=" + statReqId + ", acct=" + acct
                + ", instrmt=" + instrmt + ", side=" + side + "]";
    }
}
