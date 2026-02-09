package org.example.topprogramingstoriesdashboard.scraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class ScraperConfiguration {

    @Value("{application.hackernews.baseurl}")
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