package pl.fermich.nolfix.example;

import com.fermich.nolfix.fix.msg.common.Instrument;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            System.out.println("need command");
            return;
        }
        NolSyncMsgRequester syncMsgRequester = new NolSyncMsgRequester();
        if ("publish".equals(args[1])) {
            if (args.length < 4) {
                System.out.println("publish kafka:9092 topic-name");
                return;
            }
            syncMsgRequester.login();

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    NolAsyncMsgReceiver asyncMsgReceiver = new NolAsyncMsgReceiver();
                    asyncMsgReceiver.startReceiving(new KafkaPublisher(args[2], args[3]));
                }
            });
            thread.start();
            thread.join();
        } else
        if ("clean".equals(args[1])) {
            syncMsgRequester.cleanUpFilter();
            return;
        } else
        if ("add".equals(args[1])) {
            if (args.length < 3) {
                System.out.println("add KGHM PGE");
                return;
            }
            List<Instrument> instruments = Arrays.stream(args).skip(2)
                    .map(i -> new Instrument().setSym(i))
                    .collect(Collectors.toList());
            syncMsgRequester.addInstrumentToFilter(instruments);
        }

//        syncMsgRequester.printInstruments();
//        syncMsgRequester.cleanUpFilter();
//        syncMsgRequester.disableAsynchronousMessagesAndPrintSessionStatus();
//        syncMsgRequester.newOrder();
//        syncMsgRequester.cancelOrder();
    }

    private static void printMenu() {
        System.out.println("1. Login and Start Receiver");
        System.out.println("2. Add Filter");
        System.out.println("3. Filter Cleanup");
        System.out.println("4. Exit");
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
