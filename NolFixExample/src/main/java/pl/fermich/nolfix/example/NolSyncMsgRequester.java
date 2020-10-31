package pl.fermich.nolfix.example;

import com.fermich.nolfix.broker.ApiSettings;
import com.fermich.nolfix.fix.client.NolRequestReplyClient;
import com.fermich.nolfix.fix.id.FileStoreIdGenerator;
import com.fermich.nolfix.fix.msg.Fixml;
import com.fermich.nolfix.fix.msg.RequestFactory;
import com.fermich.nolfix.fix.msg.access.UserRequest;
import com.fermich.nolfix.fix.msg.common.Instrument;
import com.fermich.nolfix.fix.msg.common.MarketDepth;
import com.fermich.nolfix.fix.msg.exception.BusinessMessageRejectException;
import com.fermich.nolfix.fix.msg.exception.MarketDataRequestRejectException;
import com.fermich.nolfix.fix.msg.filter.EntryType;
import com.fermich.nolfix.fix.msg.filter.MarketDataRequest;
import com.fermich.nolfix.fix.msg.order.*;
import com.fermich.nolfix.fix.msg.securities.MarketID;
import com.fermich.nolfix.fix.msg.securities.SecurityList;
import com.fermich.nolfix.fix.msg.securities.SecurityListRequest;
import com.fermich.nolfix.fix.msg.session.TradingSessionStatusRequest;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NolSyncMsgRequester {

    private RequestFactory msgFact = new RequestFactory(new FileStoreIdGenerator("requestId.txt"));
    private NolRequestReplyClient nolClient = new NolRequestReplyClient(ApiSettings.HOST, ApiSettings.SYNC_PORT_NUM);

    public void login() {
        System.out.println("Logging in...");
        UserRequest userRequest = msgFact.createUserRequest()
                .setUserReqTyp(UserRequest.UserRequestType.LOGIN.getUserReqTyp())
                .setUsername(ApiSettings.USER_NAME)
                .setPassword(ApiSettings.USER_PASSWD);

        try {
            Fixml fixResponse = nolClient.send(userRequest.pack());
            System.out.println("Response: " + fixResponse.unpack());
        } catch (BusinessMessageRejectException brex) {
            System.out.println("BusinessMessageReject: " + brex.getErrorMessage());
        }
    }

    public List<String> listInstruments() {
        System.out.println("Getting securities...");
        Pattern p = Pattern.compile("[A-Z,0-9]*[A-Z]+");

        SecurityListRequest securityListRequest = msgFact.createSecurityListRequest()
                .setListReqTyp(SecurityListRequest.SecurityListRequestType.ONE_MARKET_TYPE_LIST.getValue())
                .setMktId(MarketID.NM.getValue());

        try {
            Fixml fixResponse = nolClient.send(securityListRequest.pack());
            SecurityList securityList = (SecurityList)fixResponse.unpack();
            return securityList.getInstrmts().stream().map(Instrument::getSym)
                    .sorted()
                    .filter(sym -> p.matcher(sym).matches())
                    .collect(Collectors.toList());
        } catch (BusinessMessageRejectException brex) {
            System.out.println("BusinessMessageReject: " + brex.getErrorMessage());
        }
        return null;
    }

    public void disableAsynchronousMessagesAndPrintSessionStatus() {
        System.out.println("Getting session state...");
        TradingSessionStatusRequest tradingSessionStatusRequest = msgFact.createTradingSessionStatusRequest()
                .setSubReqTyp(TradingSessionStatusRequest.SubscriptionRequestType.MSG_ONLINE_OFF.getValue());
        try {
            Fixml fixResponse = nolClient.send(tradingSessionStatusRequest.pack());
            System.out.println("Session status: " + fixResponse.unpack());
        } catch (BusinessMessageRejectException brex) {
            System.out.println("BusinessMessageReject: " + brex.getErrorMessage());
        }
    }

    public void addInstrumentToFilter(List<Instrument> instruments) {
        System.out.println("Adding instrument to filter...");
        MarketDataRequest marketDataRequest = msgFact.createMarketDataRequest()
                .setSubReqTyp(MarketDataRequest.SubscriptionRequestType.ADD_TO_FILTER.getValue())
                //TODO Check ALL_OFFERS also
                .setMktDepth(MarketDepth.BEST_OFFER.getValue())
                .setReq(Lists.newArrayList(
                        new MarketDataRequest.MdReqGrp().setTyp(EntryType.BID.getValue()),
                        new MarketDataRequest.MdReqGrp().setTyp(EntryType.OFFER.getValue()),
                        new MarketDataRequest.MdReqGrp().setTyp(EntryType.LAST_TRADE.getValue()),
                        new MarketDataRequest.MdReqGrp().setTyp(EntryType.TRADE_VOLUME.getValue()),
                        new MarketDataRequest.MdReqGrp().setTyp(EntryType.OPEN_INTEREST.getValue()),
                        new MarketDataRequest.MdReqGrp().setTyp(EntryType.OPENNING_PRICE.getValue()),
                        new MarketDataRequest.MdReqGrp().setTyp(EntryType.CLOSING_PRICE.getValue()),
                        new MarketDataRequest.MdReqGrp().setTyp(EntryType.TRADING_SESSION_HIGH_PRICE.getValue()),
                        new MarketDataRequest.MdReqGrp().setTyp(EntryType.TRADING_SESSION_LOW_PRICE.getValue()),
                        new MarketDataRequest.MdReqGrp().setTyp(EntryType.REFERENCE_PRICE.getValue()),
                        new MarketDataRequest.MdReqGrp().setTyp(EntryType.INDEX_VOLUME.getValue())))
                .setInstReq(instruments);

        try {
            Fixml fixResponse = nolClient.send(marketDataRequest.pack());
            System.out.println("MarketDataSnapshotFullRefresh: " + fixResponse.unpack());
        } catch (BusinessMessageRejectException brex) {
            System.out.println("BusinessMessageReject: " + brex.getErrorMessage());
        } catch (MarketDataRequestRejectException mdrex) {
            System.out.println("MarketDataRequestReject: " + mdrex.getErrorMessage());
        }
    }

    public void cleanUpFilter() {
        System.out.println("Cleaning up filter...");

        MarketDataRequest marketDataRequest = msgFact.createMarketDataRequest()
                .setSubReqTyp(MarketDataRequest.SubscriptionRequestType.CLEAR_FILTER.getValue());

        try {
            Fixml fixResponse = nolClient.send(marketDataRequest.pack());
            System.out.println("MarketDataSnapshotFullRefresh: " + fixResponse.unpack());
        } catch (BusinessMessageRejectException brex) {
            System.out.println("BusinessMessageReject: " + brex.getErrorMessage());
        } catch (MarketDataRequestRejectException mdrex) {
            System.out.println("MarketDataRequestReject: " + mdrex.getErrorMessage());
        }
    }

    public void newOrder() {
        NewOrderSingle newOrderSingle = msgFact.createNewOrderSingle()
                .setTrdDt("20130502")
                .setAcct("00-55-123456")
                .setSide(SideType.BUY.getValue())
                .setTxnTm("20130502-14:04:00")
                .setOrdTyp(OrderType.PEG.getValue())
                .setCcy("PLN")
                .setTmInForce('0')
                .setInstrmt(new Instrument().setSym("COMARCH").setId("PLCOMAR00012").setSrc(4))
                .setOrdQty(new OrderQtyData().setQty(1.0f));

        try {
            Fixml fixResponse = nolClient.send(newOrderSingle.pack());
            System.out.println("ExecutionReport: " + fixResponse.unpack());
        } catch (BusinessMessageRejectException brex) {
            System.out.println("BusinessMessageReject: " + brex.getErrorMessage());
        }

    }

    public void cancelOrder() {
        OrderCancelRequest orderCancelRequest = msgFact.createOrderCancelRequest()
                .setAcct("00-55-123456")
                .setOrdId("335753983")
                .setSide(SideType.BUY.getValue())
                .setTxnTm("20130502-13:56:00")
                .setInstrmt(new Instrument().setSym("COMARCH").setId("PLCOMAR00012").setSrc(4))
                .setOrdQty(new OrderQtyData().setQty(1.0f));

        try {
            Fixml fixResponse = nolClient.send(orderCancelRequest.pack());
            System.out.println("ExecutionReport: " + fixResponse.unpack());
        } catch (BusinessMessageRejectException brex) {
            System.out.println("BusinessMessageReject: " + brex.getErrorMessage());
        }
    }

}
