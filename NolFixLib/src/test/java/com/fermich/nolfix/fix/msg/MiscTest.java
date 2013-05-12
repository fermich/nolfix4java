package com.fermich.nolfix.fix.msg;

import com.fermich.nolfix.fix.msg.misc.ApplMsgRpt;
import com.fermich.nolfix.fix.msg.misc.News;
import com.fermich.nolfix.fix.msg.misc.Statement;
import com.fermich.nolfix.fix.msg.utils.FileUtils;
import com.fermich.nolfix.fix.packet.FixmlMarshaller;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class MiscTest {

    private FileUtils fu;
    private FixmlMarshaller marshaller;

    @Before
    public void setUp() {
        fu = new FileUtils();
        marshaller = new FixmlMarshaller();
        marshaller.configure();
    }

    @Test
    public void testApplicationMessageReport() throws IOException {
        Fixml msg = marshaller.deserialize(fu.readFile("ApplMsgRpt.xml"));
        ApplMsgRpt applMsgRpt = msg.getAppMsgReps().get(0);

        Assert.assertEquals(applMsgRpt.getApplRepId(), "1");
        Assert.assertEquals(applMsgRpt.getTxt(), "22");
    }

    @Test
    public void testNews() throws IOException {
        Fixml msg = marshaller.deserialize(fu.readFile("News.xml"));
        News news = msg.getNewsList().get(0);

        Assert.assertEquals(news.getOrigTm(), "20080910-10:12:23");
        Assert.assertEquals(news.getHeadline(), "Wiadomosci");
        Assert.assertEquals(news.getMessage(), "Wiadomosc");
    }

    @Test
    public void testStatement() throws IOException {
        Fixml msg = marshaller.deserialize(fu.readFile("NonFixStatement.xml"));

        Statement statement1 = msg.getStmts().get(0);
        Assert.assertEquals(statement1.getAcct(), "00-22-012345");
        Assert.assertEquals(statement1.getAcctType(), 'P');
        Assert.assertEquals(statement1.getIke(), 'N');

        Assert.assertEquals(statement1.getFunds().size(), 9);
        for (Statement.Fund fund : statement1.getFunds()) {
            Assert.assertEquals(fund.getValue(), "0.00");
        }

        Statement statement2 = msg.getStmts().get(1);
        Assert.assertEquals(statement2.getAcct(), "00-55-012345");
        Assert.assertEquals(statement2.getAcctType(), 'M');
        Assert.assertEquals(statement2.getIke(), 'N');

        Statement.Position position = statement2.getPositions().get(0);
        Assert.assertEquals(position.getAcc110(), "2");
        Assert.assertEquals(position.getAcc120(), "0");
        Assert.assertEquals(position.getInstrmts().get(0).getSym(), "BIOTON");
        Assert.assertEquals(position.getInstrmts().get(0).getSrc().toString(), "4");
        Assert.assertEquals(position.getInstrmts().get(0).getId(), "PLBIOTN00029");

        Assert.assertEquals(statement2.getFunds().size(), 11);
    }
}
