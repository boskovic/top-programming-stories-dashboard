package org.example.topprogramingstoriesdashboard.backend.configuration;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Properties;

@Configuration
public class KafkaConfiguration {

    @Bean
    @ConfigurationProperties
    public Properties properties(){
        return new Properties();
    }

    @Bean
    @Scope("prototype")
    public KafkaConsumer<String, ItemMessage> createKafkaConsumer() {
        return new KafkaConsumer<>(properties());
    }
}
