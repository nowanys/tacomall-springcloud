package store.tacomall.mqkafka.receiver;

import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class LogReceiver {

    private static final String logTopic = "topic.log";

    @KafkaListener(topics = logTopic)
    public void onMessage(String message) {
        System.out.println(message);
    }
}
