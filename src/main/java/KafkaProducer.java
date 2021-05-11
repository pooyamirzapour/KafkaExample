import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();

        //kafka bootstrap server
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        properties.setProperty("linger.ms", "1");
        //producer acks
        properties.setProperty("acks", "1");
        properties.setProperty("retries", "3");


        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(properties);
        for (int i = 0; i < 10; i++) {

            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("second_topic", String.valueOf(i), "another message test" + i);
            producer.send(producerRecord);
        }


        producer.close();

        //topic name
    }
}
