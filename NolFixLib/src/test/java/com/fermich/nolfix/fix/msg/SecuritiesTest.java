package com.fermich.nolfix.fix.msg;

import com.fermich.nolfix.fix.msg.common.Instrument;
import com.fermich.nolfix.fix.msg.securities.SecurityList;
import com.fermich.nolfix.fix.msg.securities.SecurityListRequest;
import com.fermich.nolfix.fix.msg.utils.FileUtils;
import com.fermich.nolfix.fix.msg.utils.MsgUtils;
import com.fermich.nolfix.fix.packet.FixmlMarshaller;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class SecuritiesTest {

    private FileUtils fu;
    private FixmlMarshaller marshaller;

    @Before
    public void setUp() {
        fu = new FileUtils();
        marshaller = new FixmlMarshaller();
        marshaller.configure();
    }

    @Test
    public void testSecurityList() throws IOException {
        Fixml fixMsg = marshaller.deserialize(fu.readFile("SecurityList.xml"));
        SecurityList securityList = fixMsg.getSecList().get(0);
        Instrument instrumentComarch = securityList.getInstrmts().get(0);
        Assert.assertEquals(instrumentComarch.getSym(), "COMARCH");
        Assert.assertEquals(instrumentComarch.getSrc().toString(), "4");
        Assert.assertEquals(instrumentComarch.getId(), "PLCOMAR00012");
        Assert.assertEquals(instrumentComarch.getSecGrp(), "C7");
        Assert.assertEquals(instrumentComarch.getCfi(), "ESXXXX");

        Instrument instrumentTpsa = securityList.getInstrmts().get(1);
        Assert.assertEquals(instrumentTpsa.getSym(), "TPSA");
        Assert.assertEquals(instrumentTpsa.getSrc().toString(), "4");
        Assert.assertEquals(instrumentTpsa.getId(), "TPSA00012");
        Assert.assertEquals(instrumentTpsa.getSecGrp(), "C7");
        Assert.assertEquals(instrumentTpsa.getCfi(), "ESXXXX");
    }

    @Test
    public void testSecurityListRequest() throws IOException {
        SecurityListRequest securityListRequest = new SecurityListRequest()
                .setReqId("0")
                .setListReqTyp(0)
                .setInstrmts(Lists.newArrayList(
                        new Instrument().setSym("COMARCH")
                ));

        String serializedRequest = marshaller.serialize(securityListRequest.pack());
        Assert.assertTrue(MsgUtils.compareIgnoringWhiteSpaces(serializedRequest, fu.readFile("SecurityListRequest.xml")));
    }

}
