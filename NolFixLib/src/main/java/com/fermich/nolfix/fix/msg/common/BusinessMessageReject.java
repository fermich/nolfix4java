package com.fermich.nolfix.fix.msg.common;

import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Negative response
 * <BizMsgRej RefMsgTyp="BE" BizRejRsn="5"/>
 */
@XStreamAlias("BizMsgRej")
public class BusinessMessageReject extends FixmlMessage {

    @XStreamAlias("RefMsgTyp")
    @XStreamAsAttribute
    private String refMsgTyp;        // values from RefMsgType

    @XStreamAlias("BizRejRsn")
    @XStreamAsAttribute
    private Integer bizRejRsn;       // values from BusinessRejectReason

    @XStreamAlias("Txt")
    @XStreamAsAttribute
    private String txt;

    @Override
    public String getMsgName() {
        return "BizMsgRej";
    }

    public enum RefMsgType {
        LOGIN_LOGOUT("BE"), //Logowanie / Wylogowanie
        ORDER_NEW("D"), //Nowe zlecenie
        ORDER_CANCEL("F"), //Anulata zlecenia
        ORDER_CHANGE("G"), //Modyfikacja zlecenia
        ORDER_STATUS("H"), //Status zlecenia
        ONLINE_QUOTES("V"), //Notowania online
        SESSION_STATUS("g"); //Status oraz faza sesji

        private RefMsgType(String refMsgTyp) {
            this.refMsgTyp = refMsgTyp;
        }

        public static RefMsgType getRefMsgType(String value) throws FixmlElementNotFound {
            for (RefMsgType item : values()) {
                if (item.getRefMsgTyp().equals(value)) {
                    return item;
                }
            }
            throw new FixmlElementNotFound("Ref Message Type not found for value: " + value);
        }

        public String getRefMsgTyp() {
            return refMsgTyp;
        }

        private String refMsgTyp;
    }

    /**
     * Rejection reason
     */
    public enum BusinessRejectReason {
        OTHER(0), //Inny
        UNKNOWN_ID(1), //Nieznane ID
        UNKNOWN_INSTRUMENT(2), //Nieznany instrument
        UNKNOWN_MSG_TYPE(3), //Nieznany typ komunikatu
        ACCESS_DENIED(4), //Brak dostepu do aplikacji
        XML_ERROR(5), //Blad XML (brak atrybutu)
        NOT_AUTHORIZED(6), //Brak autoryzacji
        NO_COMMUNICATION(7); //Brak komunikacji
        //QUOTES_STEP_ERROR(18) Bledny krok notowan // NO MORE USED

        private BusinessRejectReason(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static BusinessRejectReason getBusinessRejectReason(int value) throws FixmlElementNotFound {
            for (BusinessRejectReason item : values()) {
                if (item.getValue() == value) {
                    return item;
                }
            }
            throw new FixmlElementNotFound("Business Reject reason not found for value: " + value);
        }

        private int value;
    }

    public Integer getBizRejRsn() {
        return bizRejRsn;
    }

    public BusinessMessageReject setBizRejRsn(Integer bizRejRsn) {
        this.bizRejRsn = bizRejRsn;
        return this;
    }

    public String getRefMsgTyp() {
        return refMsgTyp;
    }

    public BusinessMessageReject setRefMsgTyp(String refMsgTyp) {
        this.refMsgTyp = refMsgTyp;
        return this;
    }

    public String getTxt() {
        return txt;
    }

    public BusinessMessageReject setTxt(String txt) {
        this.txt = txt;
        return this;
    }

    @Override
    public String toString() {
        return String.format("BusinessMessageReject{refMsgTyp='%s', bizRejRsn=%d, txt='%s'}", refMsgTyp, bizRejRsn, txt);
    }
}
