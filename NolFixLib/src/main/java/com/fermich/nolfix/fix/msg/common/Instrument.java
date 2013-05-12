package com.fermich.nolfix.fix.msg.common;

import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Instrmt")
public class Instrument {

    @XStreamAlias("Sym")
    @XStreamAsAttribute
    private String sym;

    @XStreamAlias("ID")
    @XStreamAsAttribute
    private String id;

    @XStreamAlias("Src")
    @XStreamAsAttribute
    private Integer src;                //rodzaj kodu dla ID, 4 dla SecurityListRequest

    @XStreamAlias("CFI")
    @XStreamAsAttribute
    private String cfi;             //typ instrumentu, wartosci z CfiCode

    @XStreamAlias("SecGrp")
    @XStreamAsAttribute
    private String secGrp;             //grupa instrumentow

    public static enum CfiCode {

        FFXXXX("FFXXXX"), // Futures
        OCAXXX("OCAXXX"), // Opcja amerykanska call
        OPAXXX("OPAXXX"), // Opcja amerykanska put
        OCEXXX("OCEXXX"), // Opcja europejska call
        OPEXXX("OPEXXX"), // Opcja europejska put
        ESXXXX("ESXXXX"), // Akcje, warranty, prawa pierwszenstwa, certyfikaty inwestycyjne i indeksy
        DBXXXX("DBXXXX"); // Obligacje i listy zastawne

        private CfiCode(String value) {
            this.value = value;
        }

        public static CfiCode getCfiCode(String value) throws FixmlElementNotFound {
            for (CfiCode item : values()) {
                if (item.getValue().equals(value)) {
                    return item;
                }
            }
            throw new FixmlElementNotFound("Cfi (Instrument Type) not found for value: " + value);
        }

        private String value;

        public String getValue() {
            return value;
        }
    }

    public String getSym() {
        return sym;
    }

    public Instrument setSym(String Sym) {
        this.sym = Sym;
        return this;
    }

    public String getId() {
        return id;
    }

    public Instrument setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getSrc() {
        return src;
    }

    public Instrument setSrc(Integer src) {
        this.src = src;
        return this;
    }

    public String getCfi() {
        return cfi;
    }

    public Instrument setCfi(String cfi) {
        this.cfi = cfi;
        return this;
    }

    public String getSecGrp() {
        return secGrp;
    }

    public Instrument setSecGrp(String secGrp) {
        this.secGrp = secGrp;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Instrument{sym='%s', id='%s', src=%d, cfi='%s', secGrp='%s'}",
                sym, id, src, cfi, secGrp);
    }
}
