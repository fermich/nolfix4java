package pl.fermich.nolfix.example.gui;

import pl.fermich.nolfix.example.NolMain;

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
        JFrame frame = new JFrame("NolPublisher");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JTextField kafka = new JTextField("192.168.0.103:9092");
        JTextField topic = new JTextField("market-data-" + today());
        panel.add(kafka);
        panel.add(topic);

        JButton startButton = new JButton("StartPublisher");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NolMain.publish(kafka.getText(), topic.getText());
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
                NolMain.addToFilter(syms);
            }
        });
        panel.add(add);
        JButton clean = new JButton("CleanFilter");
        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NolMain.cleanUpFilter();
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
}