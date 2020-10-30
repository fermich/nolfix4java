package pl.fermich.nolfix.example.gui;

import pl.fermich.nolfix.example.NolSyncMsgRequester;

import javax.swing.*;

public class NolPublisher {

    public static void main(String[] args) {
        NolSyncMsgRequester syncMsgRequester = new NolSyncMsgRequester();

        JFrame frame = new JFrame("NolPublisher");
        frame.setSize(750, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new NolPanel(syncMsgRequester));

        frame.setVisible(true);
    }
}