package com.fermich.nolfix.fix.msg.misc;

import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

/**
 * Outlook - FIX message from BOS
 * <News OrigTm="20080910-10:12:23" Headline ="Wiadomosci" >
 <![CDATA[ Wiadomosc]]>
 * </News>
 */
@XStreamAlias("News")
@XStreamConverter(value=ToAttributedValueConverter.class, strings={"message"})
public class News extends FixmlMessage {

    @XStreamAlias("OrigTm")
    @XStreamAsAttribute
    private String origTm;        //czas powstania wiadomosci

    @XStreamAlias("Headline")
    @XStreamAsAttribute
    private String headline;    //naglowek wiadomosci

    private String message;     //wiadomosc

    @Override
    public String getMsgName() {
        return "News";
    }

    @XStreamAlias("TxtLn")
    public static class TxtLn {

        @XStreamAlias("Txt")
        @XStreamAsAttribute
        String txt;

        public String getTxt() {
            return txt;
        }

        public TxtLn setTxt(String txt) {
            this.txt = txt;
            return this;
        }
    }

    public String getHeadline() {
        return headline;
    }

    public News setHeadline(String headline) {
        this.headline = headline;
        return this;
    }

    public String getOrigTm() {
        return origTm;
    }

    public News setOrigTm(String origTm) {
        this.origTm = origTm;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("News{origTm='%s', headline='%s', message='%s'}", origTm, headline, message);
    }
}
