package org.example.topprogramingstoriesdashboard.scraper.configuration;

import org.example.topprogramingstoriesdashboard.scraper.ItemDtoToItemMessageMapper;
import org.example.topprogramingstoriesdashboard.scraper.ItemIdFetcher;
import org.example.topprogramingstoriesdashboard.scraper.ItemsFetcher;
import org.example.topprogramingstoriesdashboard.scraper.Scraper;
import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessage;
import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessageSender;
import org.example.topprogramingstoriesdashboard.scraper.web.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ScraperConfiguration {

    @Value("${application.hackernews.baseurl}")
    private String baseUrl;

    @Bean
    public NewStoriesGateway newStoriesGateway() {
        return new NewStoriesClient(baseUrl);
    }

    @Bean
    public TopStoriesGateway topStoriesGateway() {
        return new TopStoriesClient(baseUrl);
    }

    @Bean
    public ItemGateway itemGateway() {
        return new ItemClient(baseUrl);
    }

    @Bean
    public ItemIdFetcher itemIdFetcher() {
        return new ItemIdFetcher(newStoriesGateway(), topStoriesGateway());
    }

    @Bean
    public ItemsFetcher itemsFetcher() {
        return new ItemsFetcher(itemGateway());
    }

    @Bean
    public ItemDtoToItemMessageMapper itemDtoToItemMessageMapper() {
        return new ItemDtoToItemMessageMapper();
    }

    @Bean
    public ItemMessageSender itemMessageSender(KafkaTemplate<String, ItemMessage> kafkaTemplateForItemMessage){
        return new ItemMessageSender(kafkaTemplateForItemMessage);
    }

    @Bean
    public Scraper scraper(@Autowired KafkaTemplate<String, ItemMessage> kafkaTemplateForItemMessage) {
        return new Scraper(
                itemIdFetcher(),
                itemsFetcher(),
                itemMessageSender(kafkaTemplateForItemMessage),
                itemDtoToItemMessageMapper()
        );
    }
}