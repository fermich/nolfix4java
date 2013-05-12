package com.fermich.nolfix.fix.client;

import com.fermich.nolfix.fix.msg.Fixml;

public interface NolSender {

    void send(Fixml fixMsg);

    void send(String rawMsg);

    void connect();

    void disconnect();

}
