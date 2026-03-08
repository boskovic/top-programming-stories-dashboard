package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.scraper.Ranking;
import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessage;
import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessageSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ConsumerServiceTest {

    private static final KafkaContainer kafkaContainer
            = new KafkaContainer(DockerImageName.parse("apache/kafka"));

    static {
        kafkaContainer.start();
    }

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    @Autowired
    private KafkaTemplate<String, ItemMessage> kafkaTemplate;

    @Autowired
    private ItemMessageRepository itemMessageRepository;

    @Test
    public void sentMessageIsInRepository() throws Exception {
        var id = 1L;
        var ranking = Ranking.NEW;
        var itemMessage = ItemMessage.builder()
                .id(id)
                .rankings(Set.of(ranking))
                .build();
        var kafkaTopic = ItemMessageSender.KAFKA_TOPIC;

        kafkaTemplate.send(kafkaTopic, itemMessage);

        Thread.sleep(5000);

        var result = itemMessageRepository.find(id);

        assertTrue(result.isPresent());
    }
}