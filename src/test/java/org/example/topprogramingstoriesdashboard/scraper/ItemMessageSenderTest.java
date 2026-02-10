package org.example.topprogramingstoriesdashboard.scraper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@SpringBootTest
public class ItemMessageSenderTest {
    // Start Kafka container
    private static final KafkaContainer kafkaContainer
            = new KafkaContainer(DockerImageName.parse("apache/kafka"));
    private static final Logger logger = LogManager.getLogger(ItemMessageSenderTest.class);

    static {
        kafkaContainer.start();
    }

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    @Autowired
    private KafkaTemplate<String, ItemMessage> kafkaTemplate;

    @Test
    void testSend() throws InterruptedException {
        var ID = 10L;
        var rankings = Set.of(Ranking.NEW, Ranking.TOP);
        var itemMessage = ItemMessage.builder().id(ID).rankings(rankings).build();
        var sus = new ItemMessageSender(kafkaTemplate);

        sus.send(itemMessage);

        Thread.sleep(2000); // simple wait for demo purposes
        logger.info("Message sent to Kafka container!");
    }
}
