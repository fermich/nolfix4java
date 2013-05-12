package com.fermich.nolfix.fix.client;

import com.fermich.nolfix.fix.msg.Fixml;

public interface NolReceiver {

    Fixml receive();

    void connect();

    void disconnect();

}
