package com.fermich.nolfix.fix.msg;

import com.fermich.nolfix.fix.msg.common.Instrument;
import com.fermich.nolfix.fix.msg.session.TradingSessionStatus;
import com.fermich.nolfix.fix.msg.session.TradingSessionStatusRequest;
import com.fermich.nolfix.fix.msg.utils.FileUtils;
import com.fermich.nolfix.fix.msg.utils.MsgUtils;
import com.fermich.nolfix.fix.packet.FixmlMarshaller;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class SessionTest {
    private FileUtils fu;
    private FixmlMarshaller marshaller;

    @Before
    public void setUp() {
        fu = new FileUtils();
        marshaller = new FixmlMarshaller();
        marshaller.configure();
    }

    @Test
    public void testTradingSessionStatus() throws IOException {
        Fixml msg = marshaller.deserialize(fu.readFile("TrdgSesStat.xml"));
        TradingSessionStatus tradingSessionStatus = msg.getTradSessStats().get(0);

        Assert.assertEquals(tradingSessionStatus.getReqId(), "0");
        Assert.assertEquals(tradingSessionStatus.getSesSub(), "S");

        Instrument instrmt = tradingSessionStatus.getInstrmts().get(0);
        Assert.assertEquals(instrmt.getSym(), "TPSA");
        Assert.assertEquals(instrmt.getId(), "PLTLKPL00017");
        Assert.assertEquals(instrmt.getSrc().toString(), "4");
    }

    @Test
    public void testTradingSessionStatusRequest() throws IOException {
        TradingSessionStatusRequest tradingSessionStatusRequest = new TradingSessionStatusRequest()
                .setReqId("1")
                .setSubReqTyp('1');

        String serializedRequest = marshaller.serialize(tradingSessionStatusRequest.pack());
        Assert.assertTrue(MsgUtils.compareIgnoringWhiteSpaces(serializedRequest, fu.readFile("TrdgSesStatReq.xml")));
    }

}
