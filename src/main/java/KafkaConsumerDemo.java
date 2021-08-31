import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();

        //kafka bootstrap server
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());

        properties.setProperty("group.id","test");
        properties.setProperty("enable.auto.commit","true");
        properties.setProperty("auto.commit.interval.ms","1000");
        properties.setProperty("auto.offset.reset","earliest");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String,String>(properties);
        consumer.subscribe(Arrays.asList("second_topic"));

        while (true)
        {
            ConsumerRecords<String, String> consumerRecords=consumer.poll(100);
            for ( ConsumerRecord<String, String> consumerRecord: consumerRecords) {
                System.out.println( "key:"+ consumerRecord.key());
                System.out.println( "value:"+ consumerRecord.value());
                System.out.println( "topic:"+ consumerRecord.topic());
                System.out.println( "partition:"+ consumerRecord.partition());
            }
        }


    }
}
