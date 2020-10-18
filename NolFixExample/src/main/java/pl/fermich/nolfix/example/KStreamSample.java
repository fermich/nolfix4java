package pl.fermich.nolfix.example;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KStreamSample {

    public static void main(final String[] args) throws Exception {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        //turn off cache buffer:
        //props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);

        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 2000);
        props.put("linger.ms", "1");
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> textLines = builder.stream("market-data-test2");

        KTable<String, String> wordCounts = textLines
                .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
                .groupBy((key, word) -> word)
                //.windowedBy(TimeWindows.of(Duration.ofSeconds(1)))
                .count().mapValues(v -> v.toString());
                //.count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
        wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(), Serdes.String()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }
}
