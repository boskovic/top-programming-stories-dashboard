package org.example.topprogramingstoriesdashboard.backend.configuration;

import org.example.topprogramingstoriesdashboard.backend.service.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    private final ServicesConfiguration servicesConfiguration;

    public KafkaConfiguration(@Autowired ServicesConfiguration servicesConfiguration) {
        this.servicesConfiguration = servicesConfiguration;
    }

    @Bean
    public KafkaConsumerService kafkaConsumerService(){
        return new KafkaConsumerService(servicesConfiguration.topicsRepository());
    }
}
