import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerDemo {


    public static void main(String[] args) {
        Properties properties = new Properties();

        //kafka bootstrap server
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty("linger.ms", "1");
        //producer acks
        properties.setProperty("acks", "1");
        properties.setProperty("retries", "3");


        Producer<String, String> producer = new KafkaProducer(properties);
        for (int i = 0; i < 10; i++) {

            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("second_topic", String.valueOf(i),
                    "another message test" + i);
            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        System.out.println("topic:" + recordMetadata.topic());
                        System.out.println("partition:" + recordMetadata.partition());
                        System.out.println("offset:" + recordMetadata.offset());
                        System.out.println("timestamp:" + recordMetadata.timestamp());
                    } else
                        e.printStackTrace();
                }
            });
        }


        producer.close();

        //topic name
    }
}
