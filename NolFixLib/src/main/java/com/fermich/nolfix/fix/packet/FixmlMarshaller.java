package com.fermich.nolfix.fix.packet;

import com.fermich.nolfix.fix.msg.Fixml;
import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;

import java.io.StringWriter;

public class FixmlMarshaller {

    private XStream stream;

    public void configure() {
        stream = new XStream();
        stream.processAnnotations(Fixml.class);
    }

    public String serialize(FixmlMessage fixMsg) {
        StringWriter sw = new StringWriter();
        stream.marshal(fixMsg, new CompactWriter(sw));
        return sw.toString();
    }

    public String serializeWithIndents(FixmlMessage fixMsg) {
        return stream.toXML(fixMsg);
    }

    public Fixml deserialize(String fixMsgStr) {
        Fixml fixMsg = (Fixml) stream.fromXML(fixMsgStr);
        return fixMsg;
    }
}
