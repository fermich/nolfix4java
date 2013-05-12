package com.fermich.nolfix.fix.msg;

import com.fermich.nolfix.fix.msg.common.Instrument;
import com.fermich.nolfix.fix.msg.order.*;
import com.fermich.nolfix.fix.msg.utils.FileUtils;
import com.fermich.nolfix.fix.msg.utils.MsgUtils;
import com.fermich.nolfix.fix.packet.FixmlMarshaller;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class OrderTest {

    private FileUtils fu;
    private FixmlMarshaller marshaller;

    @Before
    public void setUp() {
        fu = new FileUtils();
        marshaller = new FixmlMarshaller();
        marshaller.configure();
    }

    @Test
    public void testExecutionReport() throws IOException {
        Fixml msg = marshaller.deserialize(fu.readFile("ExecRpt.xml"));
        ExecutionReport executionReport = msg.getExecReps().get(0);

        Assert.assertEquals(executionReport.getId(), "5");
        Assert.assertEquals(executionReport.getOrdId(), "178803909");
        Assert.assertEquals(executionReport.getOrdId2(), "82755223");
        Assert.assertEquals(executionReport.getExecId(), "0");
        Assert.assertEquals(executionReport.getExecTyp().toString(), "0");
        Assert.assertEquals(executionReport.getStat().toString(), "0");
        Assert.assertEquals(executionReport.getAcct(), "00-55-012345");
        Assert.assertEquals(executionReport.getSide().toString(), "1");
        Assert.assertEquals(executionReport.getTmInForce().toString(), "0");
        Assert.assertEquals(executionReport.getTyp().toString(), "L");
        Assert.assertEquals(executionReport.getPx(), 5.20f);
        Assert.assertEquals(executionReport.getTxnTm(), "20120412-17:27:38");
        Assert.assertEquals(executionReport.getCcy(), "PLN");
        Assert.assertEquals(executionReport.getCumQty(), 0.0f);
        Assert.assertEquals(executionReport.getLeavesQty(), 1.0f);
        Assert.assertEquals(executionReport.getMinQty(), 0.0f);
        Assert.assertEquals(executionReport.getDefPayTyp().toString(), "N");

        Instrument instrument = executionReport.getInstrmts().get(0);
        Assert.assertEquals(instrument.getSym(), "COMARCH");
        Assert.assertEquals(instrument.getSrc().toString(), "4");
        Assert.assertEquals(instrument.getId(), "PLCOMAR00012");

        Assert.assertEquals(executionReport.getDsplyInstrs().get(0).getDisplayQty(), 0.0f);

        Assert.assertEquals(executionReport.getOrdQty().getQty(), 1.0f);
    }

    @Test
    public void testNewOrderSingle() throws IOException {
        NewOrderSingle newOrderSingle = new NewOrderSingle()
                .setId("1")
                .setTrdDt("20130417")
                .setAcct("00-55-012345")
                .setSide('1')
                .setTxnTm("20130417")
                .setOrdTyp('G')
                .setStopPx(199.0f)
                .setCcy("PLN")
                .setTmInForce('0')
                .setInstrmt(new Instrument().setId("PLCOMAR00012").setSrc(4))
                .setOrdQty(new OrderQtyData().setQty(10.0f));

        String serializedRequest = marshaller.serialize(newOrderSingle.pack());
        Assert.assertTrue(MsgUtils.compareIgnoringWhiteSpaces(serializedRequest, fu.readFile("Order.xml")));
    }

    @Test
    public void testOrderCancelRequest() throws IOException {
        OrderCancelRequest orderCancelRequest = new OrderCancelRequest()
                .setId("1")
                .setAcct("00-55-006638")
                .setOrdId("168193517")
                .setSide('1')
                .setTxnTm("20100628-08:01:23")
                .setInstrmt(new Instrument().setSym("COMARCH").setId("PLCOMAR00012").setSrc(4))
                .setOrdQty(new OrderQtyData().setQty(1.0f));

        String serializedRequest = marshaller.serialize(orderCancelRequest.pack());
        Assert.assertTrue(MsgUtils.compareIgnoringWhiteSpaces(serializedRequest, fu.readFile("OrdCxlReq.xml")));
    }

    @Test
    public void testOrderCancelReplaceRequest() throws IOException {
        OrderCancelReplaceRequest orderCancelReplaceRequest = new OrderCancelReplaceRequest()
                .setTrdDt("20120510")
                .setId("6")
                .setOrdId("123456789")
                .setAcct("00-55-012345")
                .setMinQty(10.0f)
                .setSide('1')
                .setTxnTm("20120510-12:30:00")
                .setOrdTyp('L')
                .setPx(123.3f)
                .setCcy("PLN")
                .setInstrmt(new Instrument().setSym("COMARCH").setId("PLCOMAR00012").setSrc(4))
                .setOrdQty(new OrderQtyData().setQty(200.0f));

        String serializedRequest = marshaller.serialize(orderCancelReplaceRequest.pack());
        Assert.assertTrue(MsgUtils.compareIgnoringWhiteSpaces(serializedRequest, fu.readFile("OrdCxlRplcReq.xml")));
    }

    @Test
    public void testOrderStatusRequest() throws IOException {
        OrderStatusRequest orderStatusRequest = new OrderStatusRequest()
                .setStatReqId("0")
                .setOrdId("123456789")
                .setAcct("00-55-012345")
                .setSide('1')
                .setInstrmt(new Instrument().setSym("COMARCH"));

        String serializedRequest = marshaller.serialize(orderStatusRequest.pack());
        Assert.assertTrue(MsgUtils.compareIgnoringWhiteSpaces(serializedRequest, fu.readFile("OrdStatReq.xml")));
    }

}
