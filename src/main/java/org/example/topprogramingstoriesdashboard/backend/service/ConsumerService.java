package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessage;
import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessageSender;
import org.springframework.kafka.annotation.KafkaListener;

public class ConsumerService {

    private final TopicsRepository topicsRepository;

    public ConsumerService(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }

    @KafkaListener(topics = ItemMessageSender.KAFKA_TOPIC, groupId = "top-stories-group")
    public void listen(ItemMessage itemMessage){
        topicsRepository.save(itemMessage);
    }
}
