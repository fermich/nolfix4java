package com.fermich.nolfix.fix.msg.order;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("TrgrInstr")
public class TrigerringInstruction {

    @XStreamAlias("TrgrTyp")
    @XStreamAsAttribute
    private Character trgrTyp; //zlecenia DDM+ - po cenie

    @XStreamAlias("TrgrActn")
    @XStreamAsAttribute
    private Character trgrActn; //zlecenia DDM+ - uaktywnienie

    @XStreamAlias("TrgrPx")
    @XStreamAsAttribute
    private Float trgrPx; //zlecenia DDM+ - cena uaktywnienia

    @XStreamAlias("TrgrPxTyp")
    @XStreamAsAttribute
    private Character trgrPxTyp; //zlecenia DDM+ - ostatnia transakcja

    public Character getTrgrTyp() {
        return trgrTyp;
    }

    public TrigerringInstruction setTrgrTyp(Character trgrTyp) {
        this.trgrTyp = trgrTyp;
        return this;
    }

    public Character getTrgrActn() {
        return trgrActn;
    }

    public TrigerringInstruction setTrgrActn(Character trgrActn) {
        this.trgrActn = trgrActn;
        return this;
    }

    public Float getTrgrPx() {
        return trgrPx;
    }

    public TrigerringInstruction setTrgrPx(Float trgrPx) {
        this.trgrPx = trgrPx;
        return this;
    }

    public Character getTrgrPxTyp() {
        return trgrPxTyp;
    }

    public TrigerringInstruction setTrgrPxTyp(Character trgrPxTyp) {
        this.trgrPxTyp = trgrPxTyp;
        return this;
    }

    @Override
    public String toString() {
        return String.format("TrigerringInstruction{trgrTyp=%s, trgrActn=%s, trgrPx=%s, trgrPxTyp=%s}", trgrTyp, trgrActn, trgrPx, trgrPxTyp);
    }
}
