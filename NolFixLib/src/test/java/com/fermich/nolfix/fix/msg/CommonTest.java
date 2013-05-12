package com.fermich.nolfix.fix.msg;

import com.fermich.nolfix.fix.msg.common.BusinessMessageReject;
import com.fermich.nolfix.fix.msg.utils.FileUtils;
import com.fermich.nolfix.fix.packet.FixmlMarshaller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class CommonTest {

    private FileUtils fu;
    private FixmlMarshaller marshaller;

    @Before
    public void setUp() {
        fu = new FileUtils();
        marshaller = new FixmlMarshaller();
        marshaller.configure();
    }

    @Test
    public void testBizMsgRej() throws IOException {
        Fixml msg = marshaller.deserialize(fu.readFile("BizMsgRej.xml"));
        BusinessMessageReject businessMessageReject = msg.getBussMsgRejects().get(0);

        Assert.assertEquals(businessMessageReject.getRefMsgTyp(), "BE");
        Assert.assertEquals(businessMessageReject.getBizRejRsn().toString(), "5");
        Assert.assertEquals(businessMessageReject.getTxt(), "Test");
    }

}
