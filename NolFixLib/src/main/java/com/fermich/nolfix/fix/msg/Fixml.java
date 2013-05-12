package com.fermich.nolfix.fix.msg;

import com.fermich.nolfix.fix.msg.access.UserRequest;
import com.fermich.nolfix.fix.msg.access.UserResponse;
import com.fermich.nolfix.fix.msg.common.BusinessMessageReject;
import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.fermich.nolfix.fix.msg.filter.MarketDataIncrementalRefresh;
import com.fermich.nolfix.fix.msg.filter.MarketDataRequest;
import com.fermich.nolfix.fix.msg.filter.MarketDataRequestReject;
import com.fermich.nolfix.fix.msg.filter.MarketDataSnapshotFullRefresh;
import com.fermich.nolfix.fix.msg.misc.ApplMsgRpt;
import com.fermich.nolfix.fix.msg.misc.Heartbeat;
import com.fermich.nolfix.fix.msg.misc.News;
import com.fermich.nolfix.fix.msg.misc.Statement;
import com.fermich.nolfix.fix.msg.order.*;
import com.fermich.nolfix.fix.msg.securities.SecurityList;
import com.fermich.nolfix.fix.msg.securities.SecurityListRequest;
import com.fermich.nolfix.fix.msg.session.TradingSessionStatus;
import com.fermich.nolfix.fix.msg.session.TradingSessionStatusRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * FIXML's root element
 * <FIXML v="5.0" r="20080317" s="20080314">
 * </FIXML>
 */
@XStreamAlias("FIXML")
public class Fixml extends FixmlMessage {

    @XStreamAlias("v")
    @XStreamAsAttribute
    private String v;  // wersja FIXMLa

    @XStreamAlias("r")
    @XStreamAsAttribute
    private String r;  // dzien wydania zastosowanej wersji FIXMLa

    @XStreamAlias("s")
    @XStreamAsAttribute
    private String s;  // dzien wydania zastosowanego FIXML schema

    // ===== Requests =====

    @XStreamImplicit(itemFieldName = "UserReq")
    private List<UserRequest> userRequests;

    @XStreamImplicit(itemFieldName = "MktDataReq")
    private List<MarketDataRequest> mktDataReqs;

    @XStreamImplicit(itemFieldName = "TrdgSesStatReq")
    private List<TradingSessionStatusRequest> tradSessStatReqs;

    @XStreamImplicit(itemFieldName = "Order")
    private List<NewOrderSingle> newOrders;

    @XStreamImplicit(itemFieldName = "OrdCxlRplcReq")
    private List<OrderCancelReplaceRequest> orderRplcReqs;

    @XStreamImplicit(itemFieldName = "OrdCxlReq")
    private List<OrderCancelRequest> orderCncReqs;

    @XStreamImplicit(itemFieldName = "OrdStatReq")
    private List<OrderStatusRequest> orderStatReqs;

    @XStreamImplicit(itemFieldName = "SecListReq")
    private List<SecurityListRequest> secListReqs;

    @XStreamImplicit(itemFieldName = "SecList")
    private List<SecurityList> secList;

    // ====== Responses =====
    @XStreamImplicit(itemFieldName = "UserRsp")
    private List<UserResponse> userResponses;

    @XStreamImplicit(itemFieldName = "BizMsgRej")
    private List<BusinessMessageReject> bussMsgRejects;

    @XStreamImplicit(itemFieldName = "MktDataInc")
    private List<MarketDataIncrementalRefresh> mktDataIncrRfrs;

    @XStreamImplicit(itemFieldName = "MktDataReqRej")
    private List<MarketDataRequestReject> mktDataReqRejs;

    @XStreamImplicit(itemFieldName = "MktDataFull")
    private List<MarketDataSnapshotFullRefresh> mktDataSnpshFullRfrs;

    @XStreamImplicit(itemFieldName = "ApplMsgRpt")
    private List<ApplMsgRpt> appMsgReps;

    @XStreamImplicit(itemFieldName = "News")
    private List<News> newsList;

    @XStreamImplicit(itemFieldName = "Statement")
    private List<Statement> stmts;

    @XStreamImplicit(itemFieldName = "ExecRpt")
    private List<ExecutionReport> execReps;

    @XStreamImplicit(itemFieldName = "TrdgSesStat")
    private List<TradingSessionStatus> tradSessStats;

    @XStreamImplicit(itemFieldName = "Heartbeat")
    private List<Heartbeat> heartbeats;

    @Override
    public String getMsgName() {
        return "FIXML";
    }

    public FixmlMessage unpack() {
        FixmlMessage fixMsg =
                (userRequests != null && userRequests.size() > 0) ? userRequests.get(0) :
                        (mktDataReqs != null && mktDataReqs.size() > 0) ? mktDataReqs.get(0) :
                                (tradSessStatReqs != null && tradSessStatReqs.size() > 0) ? tradSessStatReqs.get(0) :
                                        (newOrders != null && newOrders.size() > 0) ? newOrders.get(0) :
                                                (orderRplcReqs != null && orderRplcReqs.size() > 0) ? orderRplcReqs.get(0) :
                                                        (orderCncReqs != null && orderCncReqs.size() > 0) ? orderCncReqs.get(0) :
                                                                (orderStatReqs != null && orderStatReqs.size() > 0) ? orderStatReqs.get(0) :
                                                                        (secListReqs != null && secListReqs.size() > 0) ? secListReqs.get(0) :
                                                                                (userResponses != null && userResponses.size() > 0) ? userResponses.get(0) :
                                                                                        (bussMsgRejects != null && bussMsgRejects.size() > 0) ? bussMsgRejects.get(0) :
                                                                                                (mktDataIncrRfrs != null && mktDataIncrRfrs.size() > 0) ? mktDataIncrRfrs.get(0) :
                                                                                                        (mktDataReqRejs != null && mktDataReqRejs.size() > 0) ? mktDataReqRejs.get(0) :
                                                                                                                (mktDataSnpshFullRfrs != null && mktDataSnpshFullRfrs.size() > 0) ? mktDataSnpshFullRfrs.get(0) :
                                                                                                                        (appMsgReps != null && appMsgReps.size() > 0) ? appMsgReps.get(0) :
                                                                                                                                (newsList != null && newsList.size() > 0) ? newsList.get(0) :
                                                                                                                                        (stmts != null && stmts.size() > 0) ? stmts.get(0) :
                                                                                                                                                (execReps != null && execReps.size() > 0) ? execReps.get(0) :
                                                                                                                                                                (tradSessStats != null && tradSessStats.size() > 0) ? tradSessStats.get(0) :
                                                                                                                                                                        (heartbeats != null && heartbeats.size() > 0) ? heartbeats.get(0) :
                                                                                                                                                                                (secList != null && secList.size() > 0) ? secList.get(0) : null;

        if (fixMsg == null) {
            throw new FixmlElementNotFound("FIXML Message has no content!");
        }

        return fixMsg;
    }

    public Fixml addUserRequest(UserRequest userReq) {
        if (userRequests == null) {
            userRequests = new ArrayList<UserRequest>();
        }
        userRequests.add(userReq);
        return this;
    }

    public Fixml addUserResponse(UserResponse userResp) {
        if (userResponses == null) {
            userResponses = new ArrayList<UserResponse>();
        }
        userResponses.add(userResp);
        return this;
    }

    public Fixml addBussinessMsgReject(BusinessMessageReject bussMsgRej) {
        if (bussMsgRejects == null) {
            bussMsgRejects = new ArrayList<BusinessMessageReject>();
        }
        bussMsgRejects.add(bussMsgRej);
        return this;
    }

    public Fixml addMktDataIncrRefresh(MarketDataIncrementalRefresh mktDataIncrRfr) {
        if (mktDataIncrRfrs == null) {
            mktDataIncrRfrs = new ArrayList<MarketDataIncrementalRefresh>();
        }
        mktDataIncrRfrs.add(mktDataIncrRfr);
        return this;
    }

    public Fixml addMktDataRequest(MarketDataRequest mktDataReq) {
        if (mktDataReqs == null) {
            mktDataReqs = new ArrayList<MarketDataRequest>();
        }
        mktDataReqs.add(mktDataReq);
        return this;
    }

    public Fixml addMktDataReqRej(MarketDataRequestReject mktDataReqRej) {
        if (mktDataReqRejs == null) {
            mktDataReqRejs = new ArrayList<MarketDataRequestReject>();
        }
        mktDataReqRejs.add(mktDataReqRej);
        return this;
    }

    public Fixml addMktDataSnpshFullRfr(MarketDataSnapshotFullRefresh mktDataSnpshFullRfr) {
        if (mktDataSnpshFullRfrs == null) {
            mktDataSnpshFullRfrs = new ArrayList<MarketDataSnapshotFullRefresh>();
        }
        mktDataSnpshFullRfrs.add(mktDataSnpshFullRfr);
        return this;
    }

    public Fixml addAppMsgRep(ApplMsgRpt appMsgRep) {
        if (appMsgReps == null) {
            appMsgReps = new ArrayList<ApplMsgRpt>();
        }
        appMsgReps.add(appMsgRep);
        return this;
    }

    public Fixml addNews(News news) {
        if (newsList == null) {
            newsList = new ArrayList<News>();
        }
        newsList.add(news);
        return this;
    }

    public Fixml addStatement(Statement stmt) {
        if (stmts == null) {
            stmts = new ArrayList<Statement>();
        }
        stmts.add(stmt);
        return this;
    }

    public Fixml addExecRep(ExecutionReport execRep) {
        if (execReps == null) {
            execReps = new ArrayList<ExecutionReport>();
        }
        execReps.add(execRep);
        return this;
    }

    public Fixml addNewOrder(NewOrderSingle newOrder) {
        if (newOrders == null) {
            newOrders = new ArrayList<NewOrderSingle>();
        }
        newOrders.add(newOrder);
        return this;
    }

    public Fixml addOrderRplcReq(OrderCancelReplaceRequest orderRplcReq) {
        if (orderRplcReqs == null) {
            orderRplcReqs = new ArrayList<OrderCancelReplaceRequest>();
        }
        orderRplcReqs.add(orderRplcReq);
        return this;
    }

    public Fixml addOrderCncReqs(OrderCancelRequest orderCncReq) {
        if (orderCncReqs == null) {
            orderCncReqs = new ArrayList<OrderCancelRequest>();
        }
        orderCncReqs.add(orderCncReq);
        return this;
    }

    public Fixml addOrderStatReq(OrderStatusRequest orderStatReq) {
        if (orderStatReqs == null) {
            orderStatReqs = new ArrayList<OrderStatusRequest>();
        }
        orderStatReqs.add(orderStatReq);
        return this;
    }

    public Fixml addSecListReq(SecurityListRequest secListReq) {
        if (secListReqs == null) {
            secListReqs = new ArrayList<SecurityListRequest>();
        }
        secListReqs.add(secListReq);
        return this;
    }

    public Fixml addTradSessStats(TradingSessionStatus tradSessStat) {
        if (tradSessStats == null) {
            tradSessStats = new ArrayList<TradingSessionStatus>();
        }
        tradSessStats.add(tradSessStat);
        return this;
    }

    public Fixml addTradSessStatReq(TradingSessionStatusRequest tradSessStatReq) {
        if (tradSessStatReqs == null) {
            tradSessStatReqs = new ArrayList<TradingSessionStatusRequest>();
        }
        tradSessStatReqs.add(tradSessStatReq);
        return this;
    }

    public List<ApplMsgRpt> getAppMsgReps() {
        return appMsgReps;
    }

    public Fixml setAppMsgReps(List<ApplMsgRpt> appMsgReps) {
        this.appMsgReps = appMsgReps;
        return this;
    }

    public List<BusinessMessageReject> getBussMsgRejects() {
        return bussMsgRejects;
    }

    public Fixml setBussMsgRejects(List<BusinessMessageReject> bussMsgRejects) {
        this.bussMsgRejects = bussMsgRejects;
        return this;
    }

    public List<ExecutionReport> getExecReps() {
        return execReps;
    }

    public Fixml setExecReps(List<ExecutionReport> execReps) {
        this.execReps = execReps;
        return this;
    }

    public List<Heartbeat> getHeartbeats() {
        return heartbeats;
    }

    public Fixml setHeartbeats(List<Heartbeat> heartbeats) {
        this.heartbeats = heartbeats;
        return this;
    }

    public List<MarketDataIncrementalRefresh> getMktDataIncrRfrs() {
        return mktDataIncrRfrs;
    }

    public Fixml setMktDataIncrRfrs(List<MarketDataIncrementalRefresh> mktDataIncrRfrs) {
        this.mktDataIncrRfrs = mktDataIncrRfrs;
        return this;
    }

    public List<MarketDataRequestReject> getMktDataReqRejs() {
        return mktDataReqRejs;
    }

    public Fixml setMktDataReqRejs(List<MarketDataRequestReject> mktDataReqRejs) {
        this.mktDataReqRejs = mktDataReqRejs;
        return this;
    }

    public List<MarketDataRequest> getMktDataReqs() {
        return mktDataReqs;
    }

    public Fixml setMktDataReqs(List<MarketDataRequest> mktDataReqs) {
        this.mktDataReqs = mktDataReqs;
        return this;
    }

    public List<MarketDataSnapshotFullRefresh> getMktDataSnpshFullRfrs() {
        return mktDataSnpshFullRfrs;
    }

    public Fixml setMktDataSnpshFullRfrs(List<MarketDataSnapshotFullRefresh> mktDataSnpshFullRfrs) {
        this.mktDataSnpshFullRfrs = mktDataSnpshFullRfrs;
        return this;
    }

    public List<NewOrderSingle> getNewOrders() {
        return newOrders;
    }

    public Fixml setNewOrders(List<NewOrderSingle> newOrders) {
        this.newOrders = newOrders;
        return this;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public Fixml setNewsList(List<News> newsList) {
        this.newsList = newsList;
        return this;
    }

    public List<OrderCancelRequest> getOrderCncReqs() {
        return orderCncReqs;
    }

    public Fixml setOrderCncReqs(List<OrderCancelRequest> orderCncReqs) {
        this.orderCncReqs = orderCncReqs;
        return this;
    }

    public List<OrderCancelReplaceRequest> getOrderRplcReqs() {
        return orderRplcReqs;
    }

    public Fixml setOrderRplcReqs(List<OrderCancelReplaceRequest> orderRplcReqs) {
        this.orderRplcReqs = orderRplcReqs;
        return this;
    }

    public List<OrderStatusRequest> getOrderStatReqs() {
        return orderStatReqs;
    }

    public Fixml setOrderStatReqs(List<OrderStatusRequest> orderStatReqs) {
        this.orderStatReqs = orderStatReqs;
        return this;
    }

    public List<SecurityList> getSecList() {
        return secList;
    }

    public Fixml setSecList(List<SecurityList> secList) {
        this.secList = secList;
        return this;
    }

    public List<SecurityListRequest> getSecListReqs() {
        return secListReqs;
    }

    public Fixml setSecListReqs(List<SecurityListRequest> secListReqs) {
        this.secListReqs = secListReqs;
        return this;
    }

    public List<Statement> getStmts() {
        return stmts;
    }

    public Fixml setStmts(List<Statement> stmts) {
        this.stmts = stmts;
        return this;
    }

    public List<TradingSessionStatusRequest> getTradSessStatReqs() {
        return tradSessStatReqs;
    }

    public Fixml setTradSessStatReqs(List<TradingSessionStatusRequest> tradSessStatReqs) {
        this.tradSessStatReqs = tradSessStatReqs;
        return this;
    }

    public List<TradingSessionStatus> getTradSessStats() {
        return tradSessStats;
    }

    public Fixml setTradSessStats(List<TradingSessionStatus> tradSessStats) {
        this.tradSessStats = tradSessStats;
        return this;
    }

    public List<UserRequest> getUserRequests() {
        return userRequests;
    }

    public Fixml setUserRequests(List<UserRequest> userRequests) {
        this.userRequests = userRequests;
        return this;
    }

    public List<UserResponse> getUserResponses() {
        return userResponses;
    }

    public Fixml setUserResponses(List<UserResponse> userResponses) {
        this.userResponses = userResponses;
        return this;
    }

    public String getV() {
        return v;
    }

    public Fixml setV(String v) {
        this.v = v;
        return this;
    }

    public String getR() {
        return r;
    }

    public Fixml setR(String r) {
        this.r = r;
        return this;
    }

    public String getS() {
        return s;
    }

    public Fixml setS(String s) {
        this.s = s;
        return this;
    }

    @Override
    public String toString() {
        return "Fixml{" +
                "v='" + v + '\'' +
                ", r='" + r + '\'' +
                ", s='" + s + '\'' +
                ", userRequests=" + userRequests +
                ", mktDataReqs=" + mktDataReqs +
                ", tradSessStatReqs=" + tradSessStatReqs +
                ", newOrders=" + newOrders +
                ", orderRplcReqs=" + orderRplcReqs +
                ", orderCncReqs=" + orderCncReqs +
                ", orderStatReqs=" + orderStatReqs +
                ", secListReqs=" + secListReqs +
                ", secList=" + secList +
                ", userResponses=" + userResponses +
                ", bussMsgRejects=" + bussMsgRejects +
                ", mktDataIncrRfrs=" + mktDataIncrRfrs +
                ", mktDataReqRejs=" + mktDataReqRejs +
                ", mktDataSnpshFullRfrs=" + mktDataSnpshFullRfrs +
                ", appMsgReps=" + appMsgReps +
                ", newsList=" + newsList +
                ", stmts=" + stmts +
                ", execReps=" + execReps +
                ", tradSessStats=" + tradSessStats +
                ", heartbeats=" + heartbeats +
                '}';
    }
}
