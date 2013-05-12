package com.fermich.nolfix.fix.msg.filter;

import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.fermich.nolfix.fix.msg.common.Instrument;
import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Response containing changes in a filter
 * <FIXML v="5.0" r="20080317" s="20080314"> <MktDataInc ReqID="20">
 * <Inc Typ="C" Sz="10"> <Instrmt Sym="COMARCH’ ID="PLCOMAR00012" SRC="4"/></Inc>
 * <Inc Typ="C" Sz="10"/> <Instrmt Sym="TPSA" ID=" PLTLKPL00017" Src="4" /></Inc>
 * </MktDataInc> </FIXML>
 */
@XStreamAlias("MktDataInc")
public class MarketDataIncrementalRefresh extends FixmlMessage {

    @XStreamAlias("MDReqID")
    @XStreamAsAttribute
    private String reqId;

    @XStreamImplicit(itemFieldName = "Inc")
    private List<MdIncGrp> incs;

    @Override
    public String getMsgName() {
        return "MktDataInc";
    }

    @XStreamAlias("Inc")
    public static class MdIncGrp { //niektore pola nullem

        @XStreamAlias("UpdtAct")
        @XStreamAsAttribute
        private Character updtAct; //wartosc z MdUpdateAction

        @XStreamAlias("Typ")
        @XStreamAsAttribute
        private Character typ; //wartosc z EntryType

        @XStreamImplicit(itemFieldName = "Instrmt")
        private List<Instrument> instrmts;

        @XStreamAlias("Px")
        @XStreamAsAttribute
        private Float px; //cena

        @XStreamAlias("MDPxLvl")
        @XStreamAsAttribute
        private Integer mdPxLv; //pozycja w arkuszu

        @XStreamAlias("Ccy")
        @XStreamAsAttribute
        private Float ccy; //waluta

        @XStreamAlias("Sz")
        @XStreamAsAttribute
        private Float sz; //rozmiar

        @XStreamAlias("NumOfOrds")
        @XStreamAsAttribute
        private Integer numOfOrds; //liczba ofert

        @XStreamAlias("Dt")
        @XStreamAsAttribute
        private String dt; //data notowania YYYYMMDD

        @XStreamAlias("Tm")
        @XStreamAsAttribute
        private String tm; //czas notowania HH:MM:SS

        @XStreamAlias("Tov")
        @XStreamAsAttribute
        private Float tov; //wartosc obrotu

        public enum MdUpdateAction {

            NEW('0'), //Nowy
            CHANGE('1'), //Zmiana
            DELETE('2'); //Usun

            private MdUpdateAction(char value) {
                this.value = value;
            }

            public static MdUpdateAction getMdUpdateAction(char value) throws FixmlElementNotFound {
                for (MdUpdateAction item : values()) {
                    if (item.getValue() == value) {
                        return item;
                    }
                }
                throw new FixmlElementNotFound("Market Data Action not found for value: " + value);
            }

            private char value;

            public char getValue() {
                return value;
            }
        }

        public Float getCcy() {
            return ccy;
        }

        public MdIncGrp setCcy(Float ccy) {
            this.ccy = ccy;
            return this;
        }

        public String getDt() {
            return dt;
        }

        public MdIncGrp setDt(String dt) {
            this.dt = dt;
            return this;
        }

        public List<Instrument> getInstrmts() {
            return instrmts;
        }

        public MdIncGrp setInstrmts(List<Instrument> instrmts) {
            this.instrmts = instrmts;
            return this;
        }

        public Integer getMdPxLv() {
            return mdPxLv;
        }

        public MdIncGrp setMdPxLv(Integer mdPxLv) {
            this.mdPxLv = mdPxLv;
            return this;
        }

        public Integer getNumOfOrds() {
            return numOfOrds;
        }

        public MdIncGrp setNumOfOrds(Integer numOfOrds) {
            this.numOfOrds = numOfOrds;
            return this;
        }

        public Float getPx() {
            return px;
        }

        public MdIncGrp setPx(Float px) {
            this.px = px;
            return this;
        }

        public Float getSz() {
            return sz;
        }

        public MdIncGrp setSz(Float sz) {
            this.sz = sz;
            return this;
        }

        public String getTm() {
            return tm;
        }

        public MdIncGrp setTm(String tm) {
            this.tm = tm;
            return this;
        }

        public Float getTov() {
            return tov;
        }

        public MdIncGrp setTov(Float tov) {
            this.tov = tov;
            return this;
        }

        public Character getTyp() {
            return typ;
        }

        public MdIncGrp setTyp(Character typ) {
            this.typ = typ;
            return this;
        }

        public Character getUpdtAct() {
            return updtAct;
        }

        public MdIncGrp setUpdtAct(Character updtAct) {
            this.updtAct = updtAct;
            return this;
        }
    }

    public List<MdIncGrp> getIncs() {
        return incs;
    }

    public MarketDataIncrementalRefresh setIncs(List<MdIncGrp> incs) {
        this.incs = incs;
        return this;
    }

    public String getReqId() {
        return reqId;
    }

    public MarketDataIncrementalRefresh setReqId(String reqId) {
        this.reqId = reqId;
        return this;
    }

    @Override
    public String toString() {
        return String.format("MarketDataIncrementalRefresh{reqId='%s', incs=%s}", reqId, incs);
    }
}
