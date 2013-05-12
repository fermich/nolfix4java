package com.fermich.nolfix.fix.client;

public class NolConnectionException extends RuntimeException {
    public NolConnectionException(Exception e) {
        super(e);
    }

    public NolConnectionException(String s, Exception ex) {
        super(s, ex);
    }
}
