package org.example.topprogramingstoriesdashboard.scraper.messaging;

import org.springframework.kafka.core.KafkaTemplate;

public class ItemMessageSender {

    public static final String KAFKA_TOPIC = "hn-stories";

    private final KafkaTemplate<String, ItemMessage> kafkaTemplate;

    public ItemMessageSender(KafkaTemplate<String, ItemMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(ItemMessage itemMessage) {
        kafkaTemplate.send(KAFKA_TOPIC, itemMessage);
    }
}
