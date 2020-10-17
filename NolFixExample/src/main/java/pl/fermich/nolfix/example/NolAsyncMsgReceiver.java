package pl.fermich.nolfix.example;

import com.fermich.nolfix.broker.ApiSettings;
import com.fermich.nolfix.fix.client.NolClient;
import com.fermich.nolfix.fix.client.NolReceiver;
import com.fermich.nolfix.fix.msg.Fixml;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class NolAsyncMsgReceiver {

    private boolean disabled = false;

    public void startReceiving(final EventPublisher publisher) {
        System.out.println("Starting asynchronous receiver...");
        FutureTask<Void> taskReceiver = new FutureTask<Void>(new Callable<Void>() {
            @Override
            public Void call() throws Exception {

                NolReceiver nolReceiver = new NolClient(ApiSettings.HOST, ApiSettings.ASYNC_PORT_NUM);
                nolReceiver.connect();
                while (!disabled) {
                    Fixml response = nolReceiver.receive();
                    publisher.sendAsync(response.unpack().toString());
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
