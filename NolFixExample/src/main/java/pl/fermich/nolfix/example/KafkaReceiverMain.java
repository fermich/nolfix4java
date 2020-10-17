package pl.fermich.nolfix.example;

public class KafkaReceiverMain {

    public static void main(String[] args) {
        NolAsyncMsgReceiver asyncMsgReceiver = new NolAsyncMsgReceiver();
        asyncMsgReceiver.startReceiving(new KafkaPublisher("127.0.0.1:9092", "market-data"));
    }
}
