package pl.fermich.nolfix.example;

import com.fermich.nolfix.fix.msg.common.Instrument;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 1) {
            System.out.println("need command");
            return;
        }
        NolSyncMsgRequester syncMsgRequester = new NolSyncMsgRequester();
        if ("publish".equals(args[0])) {
            if (args.length < 3) {
                System.out.println("publish kafka:9092 topic-name");
                return;
            }
            syncMsgRequester.login();

            System.out.println("Starting kafka topic publisher: " + args[1] + "/" + args[2]);
            NolAsyncMsgReceiver asyncMsgReceiver = new NolAsyncMsgReceiver();
            asyncMsgReceiver.startReceiving(new KafkaPublisher(args[1], args[2]));
        } else
        if ("clean".equals(args[0])) {
            syncMsgRequester.cleanUpFilter();
            return;
        } else
        if ("add".equals(args[0])) {
            if (args.length < 2) {
                System.out.println("add KGHM PGE");
                return;
            }
            List<Instrument> instruments = Arrays.stream(args).skip(1)
                    .map(i -> new Instrument().setSym(i))
                    .collect(Collectors.toList());
            for (Instrument i : instruments) {
                System.out.println("Adding: " + i);
            }
            syncMsgRequester.addInstrumentToFilter(instruments);
        }

//        syncMsgRequester.printInstruments();
//        syncMsgRequester.cleanUpFilter();
//        syncMsgRequester.disableAsynchronousMessagesAndPrintSessionStatus();
//        syncMsgRequester.newOrder();
//        syncMsgRequester.cancelOrder();
    }

    private void startAsynchronousReceiver() {
        NolAsyncMsgReceiver asyncMsgReceiver = new NolAsyncMsgReceiver();
        asyncMsgReceiver.startReceiving(new EventPublisher() {
            @Override
            public void send(String data) {
                System.out.println(data);
            }

            @Override
            public void sendAsync(String data) {
                System.out.println(data);
            }
        });
    }


}
