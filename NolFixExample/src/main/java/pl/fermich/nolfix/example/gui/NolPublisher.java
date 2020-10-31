package pl.fermich.nolfix.example.gui;

import pl.fermich.nolfix.example.logs.GuiMessageLogger;
import pl.fermich.nolfix.example.logs.MessageLogger;
import pl.fermich.nolfix.example.NolSyncMsgRequester;

import javax.swing.*;

public class NolPublisher {

    public static void main(String[] args) {
        MessageLogger logger = new GuiMessageLogger();
        NolSyncMsgRequester syncMsgRequester = new NolSyncMsgRequester(logger);

        JFrame frame = new JFrame("NolPublisher");
        frame.setSize(750, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new NolPanel(syncMsgRequester));

        frame.setVisible(true);
    }
}