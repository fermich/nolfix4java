package pl.fermich.nolfix.example.gui;

import com.fermich.nolfix.fix.msg.common.Instrument;
import pl.fermich.nolfix.example.KafkaPublisher;
import pl.fermich.nolfix.example.NolAsyncMsgReceiver;
import pl.fermich.nolfix.example.NolSyncMsgRequester;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NolPanel extends JPanel {
    public NolPanel(NolSyncMsgRequester syncMsgRequester) {
        this.setLayout(new BorderLayout());

        this.add(northPanel(syncMsgRequester), BorderLayout.NORTH);

        JTextArea jTextArea = new JTextArea(20, 380);
        jTextArea.setText("PGE KGHM PGNIG DINOPL PKNORLEN CDPROJEKT LOTOS ALLEGRO MBANK ");
        jTextArea.setLineWrap(true);
        this.add(new JScrollPane(jTextArea), BorderLayout.CENTER);

        this.add(southPanel(jTextArea, syncMsgRequester), BorderLayout.SOUTH);
        this.add(eastPanel(jTextArea), BorderLayout.EAST);
    }

    private JPanel northPanel(NolSyncMsgRequester syncMsgRequester) {
        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(actionEvent -> syncMsgRequester.login());
        north.add(loginButton);

        JTextField kafka = new JTextField("localhost:9092", 15);
        JTextField topic = new JTextField("market-data-" + today(), 20);
        JButton startButton = new JButton("StartPublisher");
        startButton.addActionListener(actionEvent -> {
            NolAsyncMsgReceiver asyncMsgReceiver = new NolAsyncMsgReceiver();
            asyncMsgReceiver.startReceiving(new KafkaPublisher(kafka.getText(), topic.getText()));
        });
        north.add(startButton);
        north.add(new JLabel("Kafka: "));
        north.add(kafka);
        north.add(new JLabel("Topic: "));
        north.add(topic);
        return north;
    }

    private JPanel southPanel(JTextArea jTextArea, NolSyncMsgRequester syncMsgRequester) {
        JPanel south = new JPanel(new FlowLayout());
        JButton add = new JButton("AddToFilter");
        add.addActionListener(actionEvent -> {
            java.util.List<String> syms = Arrays.stream(jTextArea.getText().split(" "))
                    .map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
            addToFilter(syncMsgRequester, syms);
        });
        south.add(add);

        JButton clean = new JButton("CleanFilter");
        clean.addActionListener(actionEvent -> syncMsgRequester.cleanUpFilter());
        south.add(clean);
        return south;
    }

    private JPanel eastPanel(JTextArea jTextArea) {
        JPanel east = new JPanel();
        east.setLayout(new BoxLayout(east, BoxLayout.PAGE_AXIS));

        DefaultListModel<String> objectDefaultListModel = new DefaultListModel<>();
        JList<String> secList = new JList<>(objectDefaultListModel);
        JButton loadSecurities = new JButton("LoadSecurities");
        loadSecurities.addActionListener(e -> objectDefaultListModel.addElement("ALLEGRO"));
        east.add(loadSecurities);

        east.add(new JScrollPane(secList));
        JButton addToMonitor = new JButton("<<");
        addToMonitor.addActionListener(e -> {
                    for (String sym: secList.getSelectedValuesList()) {
                        jTextArea.append(sym + " ");
                    }
                }
        );
        east.add(addToMonitor);
        return east;
    }

    private String today() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    private void addToFilter(NolSyncMsgRequester syncMsgRequester, java.util.List<String> syms) {
        List<Instrument> instruments = syms.stream()
                .map(i -> new Instrument().setSym(i))
                .collect(Collectors.toList());
        syncMsgRequester.addInstrumentToFilter(instruments);
    }
}
