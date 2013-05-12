package com.fermich.nolfix.fix.client;

import com.fermich.nolfix.broker.ApiSettings;
import com.fermich.nolfix.fix.msg.Fixml;
import com.fermich.nolfix.fix.packet.FixmlMarshaller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class NolClient implements NolSender, NolReceiver {
    private String host;
    private int port;
    private Socket nolSocket;
    private InputStream inStream;
    private OutputStream outStream;
    private FixmlMarshaller fixmlMarshaller;

    public NolClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.fixmlMarshaller = new FixmlMarshaller();
        this.fixmlMarshaller.configure();
    }

    @Override
    public void connect() {
        try {
            nolSocket = new Socket(host, port);
            outStream = nolSocket.getOutputStream();
            inStream = nolSocket.getInputStream();
        } catch (Exception ex) {
            throw new NolConnectionException("Error while connecting to Nol!", ex);
        }
    }

    @Override
    public void send(Fixml fixMsg) {
        send(fixmlMarshaller.serialize(fixMsg));
    }

    @Override
    public void send(String rawMsg) {
        try {
            byte[] fixArray = rawMsg.getBytes();
            byte[] fixArrayWith0 = Arrays.copyOf(fixArray, fixArray.length + 1);

            outStream.write(fixArrayWith0.length);
            outStream.flush();

            outStream.write(fixArrayWith0);
            outStream.flush();
        } catch (Exception e) {
            throw new NolConnectionException("Error while sending message to Nol!", e);
        }
    }

    @Override
    public Fixml receive() {
        try {
            int msgSize = readMessageSize();
            if (msgSize > 0) {
                byte[] respArray = readBundledMessage(inStream, msgSize);

                String rawResponse = new String(Arrays.copyOf(respArray, respArray.length - 1));
                Fixml nolResponse = fixmlMarshaller.deserialize(rawResponse);
                nolResponse.setRawMessage(rawResponse);

                return nolResponse;
            }
        } catch (Exception e) {
            throw new NolConnectionException("Error while receiving message from Nol!", e);
        }
        return new Fixml();
    }

    @Override
    public void disconnect() {
        try {
            outStream.close();
            inStream.close();
            nolSocket.close();
        } catch (Exception ex) {
            throw new NolConnectionException("Error while closing connection!", ex);
        }
    }

    private int readMessageSize() throws IOException {
        byte[] lengthArray = new byte[ApiSettings.HEADER_SIZE];
        inStream.read(lengthArray);

        int size = 0;
        int bytes = 1;
        for (int i = 0; i < ApiSettings.MESSAGE_SIZE; i++) {
            size += (lengthArray[i] & 0xff) * bytes;
            bytes *= (0xff + 1);
        }
        return size;
    }

    private byte[] readBundledMessage(InputStream input, int expected) throws IOException {
        int numOfRead;
        int totalNumOfRead = 0;
        int nextLength = expected;

        byte[] respArray = new byte[expected];

        while (nextLength > 0) {
            byte[] bundle = new byte[nextLength];
            numOfRead = input.read(bundle);
            System.arraycopy(bundle, 0, respArray, totalNumOfRead, numOfRead);
            totalNumOfRead += numOfRead;
            nextLength -= numOfRead;
        }

        return respArray;
    }

}
