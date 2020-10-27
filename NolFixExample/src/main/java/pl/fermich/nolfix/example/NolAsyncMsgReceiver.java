package pl.fermich.nolfix.example;

import com.fermich.nolfix.broker.ApiSettings;
import com.fermich.nolfix.fix.client.NolClient;
import com.fermich.nolfix.fix.client.NolReceiver;
import com.fermich.nolfix.fix.msg.Fixml;
import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.fermich.nolfix.fix.msg.misc.Heartbeat;
import com.google.gson.Gson;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class NolAsyncMsgReceiver {

    private boolean disabled = false;
    private Gson gson = new Gson();

    public void startReceiving(final EventPublisher publisher) {
        System.out.println("Starting asynchronous receiver...");
        FutureTask<Void> taskReceiver = new FutureTask<Void>(new Callable<Void>() {
            @Override
            public Void call() throws Exception {

                NolReceiver nolReceiver = new NolClient(ApiSettings.HOST, ApiSettings.ASYNC_PORT_NUM);
                nolReceiver.connect();
                while (!disabled) {
                    Fixml response = nolReceiver.receive();
                    FixmlMessage msg = response.unpack();
                    MarketDataEvent marketDataEvent = new MarketDataEvent(System.currentTimeMillis(), msg);
                    if (! (msg instanceof Heartbeat)) {
                        publisher.send(gson.toJson(marketDataEvent));
                    }
                }
                nolReceiver.disconnect();

                return null;
            }
        });

        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(taskReceiver);
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

}

class MarketDataEvent {
    private final long timestamp;
    private final FixmlMessage message;

    public MarketDataEvent(long timestamp, FixmlMessage message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public FixmlMessage getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketDataEvent that = (MarketDataEvent) o;
        return timestamp == that.timestamp &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, message);
    }
}