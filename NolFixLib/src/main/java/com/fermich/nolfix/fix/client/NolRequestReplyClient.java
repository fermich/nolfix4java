package com.fermich.nolfix.fix.client;

import com.fermich.nolfix.fix.msg.Fixml;
import com.fermich.nolfix.fix.msg.exception.BusinessMessageRejectException;
import com.fermich.nolfix.fix.msg.exception.MarketDataRequestRejectException;

public class NolRequestReplyClient {
    private final String host;
    private final int port;

    public NolRequestReplyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Fixml send(Fixml fixMsg) {
        NolClient nolClient = new NolClient(host, port);
        nolClient.connect();

        nolClient.send(fixMsg);
        Fixml nolResponse = nolClient.receive();

        nolClient.disconnect();

        validateResponse(nolResponse);
        return nolResponse;
    }

    private void validateResponse(Fixml fixmlResponse) {
        if (fixmlResponse.getBussMsgRejects() != null && !fixmlResponse.getBussMsgRejects().isEmpty()) {
            throw new BusinessMessageRejectException(fixmlResponse.getBussMsgRejects().get(0));
        }
        if (fixmlResponse.getMktDataReqRejs() != null && !fixmlResponse.getMktDataReqRejs().isEmpty()) {
            throw new MarketDataRequestRejectException(fixmlResponse.getMktDataReqRejs().get(0));
        }
    }
}
