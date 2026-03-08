package org.example.topprogramingstoriesdashboard.backend.configuration;

import org.example.topprogramingstoriesdashboard.backend.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    @Bean
    public GetTopStoriesUseCase getTopStoriesUseCase() {
        return new TopStories(itemMessageRepository(), itemMessageToStoryDtoMapper());
    }

    @Bean
    public GetNewStoriesUseCase getNewStoriesUseCase() {
        return new NewStories(itemMessageRepository(), itemMessageToStoryDtoMapper());
    }

    @Bean
    public ItemMessageRepository itemMessageRepository() {
        return new ItemMessageRepository();
    }

    @Bean
    public ItemMessageToStoryDtoMapper itemMessageToStoryDtoMapper() {
        return new ItemMessageToStoryDtoMapper();
    }
}
