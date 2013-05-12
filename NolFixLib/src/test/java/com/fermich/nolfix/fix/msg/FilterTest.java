package com.fermich.nolfix.fix.msg;

import com.fermich.nolfix.fix.msg.common.Instrument;
import com.fermich.nolfix.fix.msg.filter.MarketDataIncrementalRefresh;
import com.fermich.nolfix.fix.msg.filter.MarketDataRequest;
import com.fermich.nolfix.fix.msg.filter.MarketDataRequestReject;
import com.fermich.nolfix.fix.msg.filter.MarketDataSnapshotFullRefresh;
import com.fermich.nolfix.fix.msg.utils.FileUtils;
import com.fermich.nolfix.fix.msg.utils.MsgUtils;
import com.fermich.nolfix.fix.packet.FixmlMarshaller;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class FilterTest {

    private FileUtils fu;
    private FixmlMarshaller marshaller;

    @Before
    public void setUp() {
        fu = new FileUtils();
        marshaller = new FixmlMarshaller();
        marshaller.configure();
    }

    @Test
    public void testMarketDataIncrementalRefresh() throws IOException {
        Fixml msg = marshaller.deserialize(fu.readFile("MktDataInc.xml"));
        MarketDataIncrementalRefresh mktDataInc = msg.getMktDataIncrRfrs().get(0);

        Assert.assertEquals(mktDataInc.getReqId(), "20");

        MarketDataIncrementalRefresh.MdIncGrp mdIncGrpComarch = mktDataInc.getIncs().get(0);
        Assert.assertEquals(mdIncGrpComarch.getTyp().toString(), "C");
        Assert.assertEquals(mdIncGrpComarch.getSz(), 10.0f);
        Instrument instrumentComarch = mdIncGrpComarch.getInstrmts().get(0);
        Assert.assertEquals(instrumentComarch.getSym(), "COMARCH");
        Assert.assertEquals(instrumentComarch.getId(), "PLCOMAR00012");
        Assert.assertEquals(instrumentComarch.getSrc().toString(), "4");

        MarketDataIncrementalRefresh.MdIncGrp mdIncGrpTpsa = mktDataInc.getIncs().get(1);
        Assert.assertEquals(mdIncGrpTpsa.getTyp().toString(), "C");
        Assert.assertEquals(mdIncGrpTpsa.getSz(), 10.0f);
        Instrument instrumentTpsa = mdIncGrpTpsa.getInstrmts().get(0);
        Assert.assertEquals(instrumentTpsa.getSym(), "TPSA");
        Assert.assertEquals(instrumentTpsa.getId(), "PLTLKPL00017");
        Assert.assertEquals(instrumentTpsa.getSrc().toString(), "4");
    }

    @Test
    public void testMarketDataRequestReject() throws IOException {
        Fixml msg = marshaller.deserialize(fu.readFile("MktDataReqRej.xml"));
        MarketDataRequestReject mktDataReqRej = msg.getMktDataReqRejs().get(0);

        Assert.assertEquals(mktDataReqRej.getReqId(), "1");
        Assert.assertEquals(mktDataReqRej.getReqRejResn(), '0');
    }

    @Test
    public void testMarketDataSnapshotFullRefresh() throws IOException {
        Fixml msg = marshaller.deserialize(fu.readFile("MktDataFull.xml"));

        MarketDataSnapshotFullRefresh marketDataSnapshotFullRefresh = msg.getMktDataSnpshFullRfrs().get(0);
        Assert.assertEquals(marketDataSnapshotFullRefresh.getReqId(), "22");
    }

    @Test
    public void testMarketDataRequest() throws IOException {
        MarketDataRequest marketDataRequest = new MarketDataRequest()
                .setReqId("1")
                .setSubReqTyp('1')
                .setMktDepth(1)
                .setReq(Lists.newArrayList(
                        new MarketDataRequest.MdReqGrp().setTyp('C'),
                        new MarketDataRequest.MdReqGrp().setTyp('4'),
                        new MarketDataRequest.MdReqGrp().setTyp('5')))
                .setInstReq(Lists.newArrayList(
                        new Instrument().setSym("COMARCH").setId("PLCOMAR00012").setSrc(4),
                        new Instrument().setSym("TPSA").setId("PLTLKPL00017").setSrc(4)));

        String serializedRequest = marshaller.serialize(marketDataRequest.pack());
        Assert.assertTrue(MsgUtils.compareIgnoringWhiteSpaces(serializedRequest, fu.readFile("MktDataReq.xml")));
    }

}
