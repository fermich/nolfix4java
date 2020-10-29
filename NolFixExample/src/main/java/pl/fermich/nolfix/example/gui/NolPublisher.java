package pl.fermich.nolfix.example.gui;

import com.fermich.nolfix.fix.msg.common.Instrument;
import pl.fermich.nolfix.example.KafkaPublisher;
import pl.fermich.nolfix.example.NolAsyncMsgReceiver;
import pl.fermich.nolfix.example.NolSyncMsgRequester;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class NolPublisher {

    public static void main(String[] args) {
        NolSyncMsgRequester syncMsgRequester = new NolSyncMsgRequester();

        JFrame frame = new JFrame("NolPublisher");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, syncMsgRequester);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel, NolSyncMsgRequester syncMsgRequester) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                syncMsgRequester.login();
                loginButton.setEnabled(false);
            }
        });
        panel.add(loginButton);

        JTextField kafka = new JTextField("192.168.0.103:9092");
        JTextField topic = new JTextField("market-data-" + today());
        panel.add(kafka);
        panel.add(topic);
        JButton startButton = new JButton("StartPublisher");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startButton.setEnabled(false);
                NolAsyncMsgReceiver asyncMsgReceiver = new NolAsyncMsgReceiver();
                asyncMsgReceiver.startReceiving(new KafkaPublisher(kafka.getText(), topic.getText()));
            }
        });
        panel.add(startButton);

        JLabel passwordLabel = new JLabel("SYMs");
        panel.add(passwordLabel);

        JTextArea jTextArea = new JTextArea(20, 60);
        jTextArea.setText("PGE KGHM PGNIG DINOPL PKNORLEN CDPROJEKT LOTOS ALLEGRO MBANK");
        jTextArea.setLineWrap(true);
        panel.add(jTextArea);

        JButton add = new JButton("AddToFilter");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                List<String> syms = Arrays.stream(jTextArea.getText().split(" ")).map(String::trim).filter(not(String::isEmpty)).collect(Collectors.toList());
                addToFilter(syncMsgRequester, syms);
            }
        });
        panel.add(add);
        JButton clean = new JButton("CleanFilter");
        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                syncMsgRequester.cleanUpFilter();
            }
        });
        panel.add(clean);
    }

    private static String today() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public static void addToFilter(NolSyncMsgRequester syncMsgRequester, List<String> syms) {
        List<Instrument> instruments = syms.stream()
                .map(i -> new Instrument().setSym(i))
                .collect(Collectors.toList());
        syncMsgRequester.addInstrumentToFilter(instruments);
    }
}