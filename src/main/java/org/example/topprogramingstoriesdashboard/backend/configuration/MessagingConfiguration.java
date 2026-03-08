package org.example.topprogramingstoriesdashboard.backend.configuration;

import org.example.topprogramingstoriesdashboard.backend.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {

    private final ServicesConfiguration servicesConfiguration;

    public MessagingConfiguration(@Autowired ServicesConfiguration servicesConfiguration) {
        this.servicesConfiguration = servicesConfiguration;
    }

    @Bean
    public ConsumerService kafkaConsumerService(){
        return new ConsumerService(servicesConfiguration.topicsRepository());
    }
}
