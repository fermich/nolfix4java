package pl.fermich.nolfix.example;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaPublisher implements EventPublisher {

    private Producer<Long, String> kafkaProducer;
    private String topic;

    public KafkaPublisher(String bootstrapServers, String topic) {
        this.kafkaProducer = createProducer(bootstrapServers);
        this.topic = topic;
    }

    public void send(String marketData) {
        ProducerRecord producerRecord = new ProducerRecord(topic, marketData);
        try {
            this.kafkaProducer.send(producerRecord).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAsync(final String marketData) {
        ProducerRecord producerRecord = new ProducerRecord(topic, marketData);
        this.kafkaProducer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (recordMetadata == null) {
                    e.printStackTrace();
                } else {
                    System.out.println("Event published: " + marketData);
                }
            }
        });
    }

    private Producer<Long, String> createProducer(String bootstrapServers) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "MarketDataProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        return new KafkaProducer<>(props);
    }
}
