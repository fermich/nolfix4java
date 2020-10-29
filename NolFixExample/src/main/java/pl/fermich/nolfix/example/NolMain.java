package pl.fermich.nolfix.example;

import com.fermich.nolfix.fix.msg.common.Instrument;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NolMain {

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            System.out.println("need command");
            return;
        }
        if ("publish".equals(args[1])) {
            if (args.length < 4) {
                System.out.println("publish kafka:9092 topic-name");
                return;
            }
            publish(args[2], args[2]);
        } else
        if ("clean".equals(args[1])) {
            cleanUpFilter();
        } else
        if ("add".equals(args[1])) {
            if (args.length < 3) {
                System.out.println("add KGHM PGE");
                return;
            }
            addToFilter(Arrays.stream(args).skip(2).collect(Collectors.toList()));
        } else {
            System.out.println("command not found: " + args[0]);
        }

//        syncMsgRequester.printInstruments();
//        syncMsgRequester.cleanUpFilter();
//        syncMsgRequester.disableAsynchronousMessagesAndPrintSessionStatus();
//        syncMsgRequester.newOrder();
//        syncMsgRequester.cancelOrder();
    }

    public static void addToFilter(List<String> syms) {
        NolSyncMsgRequester syncMsgRequester = new NolSyncMsgRequester();
        List<Instrument> instruments = syms.stream().skip(2)
                .map(i -> new Instrument().setSym(i))
                .collect(Collectors.toList());
        syncMsgRequester.addInstrumentToFilter(instruments);
    }

    public static void publish(String kafka, String topic) {
        NolSyncMsgRequester syncMsgRequester = new NolSyncMsgRequester();
        syncMsgRequester.login();

        NolAsyncMsgReceiver asyncMsgReceiver = new NolAsyncMsgReceiver();
        asyncMsgReceiver.startReceiving(new KafkaPublisher(kafka, topic));
    }

    public static void cleanUpFilter() {
        NolSyncMsgRequester syncMsgRequester = new NolSyncMsgRequester();
        syncMsgRequester.cleanUpFilter();
    }
}
