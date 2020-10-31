package pl.fermich.nolfix.example.logs;

import javax.swing.*;

public class GuiMessageLogger extends MessageLogger {

    @Override
    public void logError(String msg) {
        JOptionPane.showMessageDialog(new JFrame(), msg);
    }

    @Override
    public void logInfo(String msg) {
        JOptionPane.showMessageDialog(new JFrame(), msg);
    }
}
