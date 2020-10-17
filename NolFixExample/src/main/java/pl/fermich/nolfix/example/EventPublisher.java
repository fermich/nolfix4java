package pl.fermich.nolfix.example;

public interface EventPublisher {

    void send(String data);

    void sendAsync(String data);

}
