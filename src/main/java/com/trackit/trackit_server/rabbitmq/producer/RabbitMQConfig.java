package com.trackit.trackit_server.rabbitmq.producer;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Queues
    @Bean
    Queue marketingQueue() {
        return new Queue("marketingQueue", false);
    }

    @Bean
    Queue financeQueue() {
        return new Queue("financeQueue", false);
    }

    @Bean
    Queue adminQueue() {
        return new Queue("adminQueue", false);
    }

    @Bean
    Queue allQueue() {
        return new Queue("allQueue", false);
    }

    // Topic Exchange Configuration
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Binding marketingTopicBinding(Queue marketingQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(marketingQueue).to(topicExchange).with("queue.marketing");
    }

    @Bean
    Binding financeTopicBinding(Queue financeQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(financeQueue).to(topicExchange).with("queue.finance");
    }

    @Bean
    Binding adminTopicBinding(Queue adminQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(adminQueue).to(topicExchange).with("queue.admin");
    }

    @Bean
    Binding allTopicBinding(Queue allQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(allQueue).to(topicExchange).with("queue.*");
    }

    // Direct Exchange Configuration
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    Binding marketingDirectBinding(Queue marketingQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(marketingQueue).to(directExchange).with("marketing");
    }

    @Bean
    Binding financeDirectBinding(Queue financeQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(financeQueue).to(directExchange).with("finance");
    }

    @Bean
    Binding adminDirectBinding(Queue adminQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(adminQueue).to(directExchange).with("admin");
    }

    // Fanout Exchange Configuration
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    Binding marketingFanoutBinding(Queue marketingQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(marketingQueue).to(fanoutExchange);
    }

    @Bean
    Binding financeFanoutBinding(Queue financeQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(financeQueue).to(fanoutExchange);
    }

    @Bean
    Binding adminFanoutBinding(Queue adminQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(adminQueue).to(fanoutExchange);
    }
}
