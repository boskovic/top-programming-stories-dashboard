package org.example.topprogramingstoriesdashboard.backend.configuration;

import org.example.topprogramingstoriesdashboard.backend.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    @Bean
    public GetTopStoriesUseCase getTopStoriesUseCase() {
        return new TopStories();
    }

    @Bean
    public GetNewStoriesUseCase getNewStoriesUseCase() {
        return new NewStories();
    }

    @Bean
    public ItemMessageRepository topicsRepository() {
        return new ItemMessageRepository();
    }
}
