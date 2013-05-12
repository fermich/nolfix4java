package com.fermich.nolfix.fix.msg;

import com.fermich.nolfix.fix.msg.access.UserRequest;
import com.fermich.nolfix.fix.msg.access.UserResponse;
import com.fermich.nolfix.fix.msg.common.BusinessMessageReject;
import com.fermich.nolfix.fix.msg.filter.MarketDataIncrementalRefresh;
import com.fermich.nolfix.fix.msg.filter.MarketDataRequest;
import com.fermich.nolfix.fix.msg.filter.MarketDataRequestReject;
import com.fermich.nolfix.fix.msg.filter.MarketDataSnapshotFullRefresh;
import com.fermich.nolfix.fix.msg.misc.ApplMsgRpt;
import com.fermich.nolfix.fix.msg.misc.Heartbeat;
import com.fermich.nolfix.fix.msg.misc.News;
import com.fermich.nolfix.fix.msg.misc.Statement;
import com.fermich.nolfix.fix.msg.order.ExecutionReport;
import com.fermich.nolfix.fix.msg.order.NewOrderSingle;
import com.fermich.nolfix.fix.msg.order.OrderCancelReplaceRequest;
import com.fermich.nolfix.fix.msg.order.OrderCancelRequest;
import com.fermich.nolfix.fix.msg.session.TradingSessionStatus;
import com.fermich.nolfix.fix.msg.session.TradingSessionStatusRequest;

public class MessageDescriptionsPL {

    public static final String FIXML = "Root element FIXML";
    public static final String USER_REQUEST = "Komunikat logowania";
    public static final String USER_RESPONSE = "Odpowiedz na komunikat logowania";
    public static final String BUSINESS_MESSAGE_REJECT = "Odpowiedz negatywna";

    public static final String MARKET_DATA_INCR_REFRESH = "Odpowiedz zawierajaca zmiany w filtrze lub na GPW";
    public static final String MARKET_DATA_SNAPSHOT_REFRESH = "Odpowiedz na MarketDataRequest";
    public static final String MARKET_DATA_REQUEST = "Zapytanie - dodanie papierow do filtra lub wyczyszczenie filtra";
    public static final String MARKET_DATA_REQUEST_REJECT = "Odpowiedz z informacja o powodzie odrzucenia zapytania";

    public static final String MISC_STATEMENT = "Wyciag";
    public static final String MISC_APP_MSG_REPORT = "Informacja o opoznieniach";
    public static final String MISC_OUTLOOK = "Komunikat / News z DMBOS";

    public static final String ORDER_NEW_SINGLE = "Nowe zlecenie";
    public static final String ORDER_EXECUTION_REPORT = "Odpowiedz na operacje na zleceniu"; //dwa rodzaje GPW / DMBOS
    public static final String ORDER_CANCEL_REQUEST = "Anulowanie zlecenia";
    public static final String ORDER_CANCEL_REPLACE_REQUEST = "Zadanie modyfikacji zlecenia";
    public static final String ORDER_STATUS_REQUEST = "Zapytanie o status zlecenia";

    public static final String SESSION_REQUEST = "Zapytanie o status sesji";
    public static final String SESSION_RESPONSE = "Odpowiedz ze statusem sesji";

    public static final String SECURITY_LIST_REQ = "Zapytanie o liste papierow";
    public static final String SECURITY_LIST_RESP = "Odpowiedz, lista instrumentow";

    public static final String HEARTBEAT = "Heartbeat";

    public static String findDescriptionForFixMsg(FixmlMessage fix) {
        return (fix instanceof BusinessMessageReject) ? BUSINESS_MESSAGE_REJECT :
                (fix instanceof UserRequest) ? USER_REQUEST :
                        (fix instanceof UserResponse) ? USER_RESPONSE :
                                (fix instanceof MarketDataIncrementalRefresh) ? MARKET_DATA_INCR_REFRESH :
                                        (fix instanceof MarketDataRequest) ? MARKET_DATA_REQUEST :
                                                (fix instanceof MarketDataRequestReject) ? MARKET_DATA_REQUEST_REJECT :
                                                        (fix instanceof MarketDataSnapshotFullRefresh) ? MARKET_DATA_SNAPSHOT_REFRESH :
                                                                (fix instanceof ApplMsgRpt) ? MISC_APP_MSG_REPORT :
                                                                        (fix instanceof Heartbeat) ? HEARTBEAT :
                                                                                (fix instanceof News) ? MISC_OUTLOOK :
                                                                                        (fix instanceof Statement) ? MISC_STATEMENT :
                                                                                                (fix instanceof ExecutionReport) ? ORDER_EXECUTION_REPORT :
                                                                                                        (fix instanceof NewOrderSingle) ? ORDER_NEW_SINGLE :
                                                                                                                        (fix instanceof OrderCancelReplaceRequest) ? ORDER_CANCEL_REPLACE_REQUEST :
                                                                                                                                (fix instanceof OrderCancelRequest) ? ORDER_CANCEL_REQUEST :
                                                                                                                                        (fix instanceof TradingSessionStatus) ? SESSION_RESPONSE :
                                                                                                                                                (fix instanceof TradingSessionStatusRequest) ? SESSION_REQUEST :
                                                                                                                                                        (fix instanceof Fixml) ? FIXML : "";
    }

}
