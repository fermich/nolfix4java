package pl.fermich.nolfix.example;

public class Main {

    public static void main(String[] args) {
        NolSyncMsgRequester syncMsgRequester = new NolSyncMsgRequester();
        syncMsgRequester.login();
//        syncMsgRequester.printInstruments();
//        syncMsgRequester.cleanUpFilter();
//        syncMsgRequester.addInstrumentToFilter();
//        syncMsgRequester.disableAsynchronousMessagesAndPrintSessionStatus();
//        syncMsgRequester.newOrder();
//        syncMsgRequester.cancelOrder();
    }

    private void startAsynchronousReceiver() {
        NolAsyncMsgReceiver asyncMsgReceiver = new NolAsyncMsgReceiver();
        asyncMsgReceiver.startReceiving();
    }


}
