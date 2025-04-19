package com.trackit.trackit_server.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/rabbitmq")
public class DirectExchangeController {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private AmqpTemplate amqpTemplate;

	public static final String MARKETING_QUEUE = "marketingQueue";
	public static final String FINANCE_QUEUE = "financeQueue";
	public static final String ADMIN_QUEUE = "adminQueue";

	@GetMapping(value = "/direct-exchange")
	public String producerDirectExchange(
			@Parameter(description = "Exchange type", schema = @Schema(type = "string", allowableValues = {
					"direct-exchange", "fanout-exchange" })) 
			@RequestParam("exchangeName") String exchange,

			@Parameter(description = "Routing Key", schema = @Schema(type = "string", allowableValues = { "finance",
					"admin", "marketing" })) 
			@RequestParam("routingKey") String routingKey,
			
			@Parameter(description = "messagedata", schema = @Schema(type = "string", allowableValues = {
					"finance data", "marketing data",
					"admin data" }))
			@RequestParam("messageData") String messageData) {

		log.info("Sending message to exchange: {}, routing key: {}, message data: {}", exchange, routingKey,
				messageData);

		amqpTemplate.convertAndSend(exchange, routingKey, messageData);

		log.info("Message sent to the RabbitMQ Successfully to exchange: {} with routingKey: {}", exchange, routingKey);
		return "Message sent to RabbitMQ Successfully";
	}

	
	@GetMapping(value = "/fanout-exchange")
	public String producerFanoutExchange(
			@Parameter(description = "Exchange type", schema = @Schema(type = "string", allowableValues = {
					"direct-exchange", "fanout-exchange" })) 
			@RequestParam("exchangeName") String exchange,

			@Parameter(description = "Routing Key", schema = @Schema(type = "string", allowableValues = { "finance",
					"admin", "marketing" })) 
			@RequestParam("routingKey") String routingKey,
			
			@Parameter(description = "messagedata", schema = @Schema(type = "string", allowableValues = {
					"finance data", "marketing data",
					"admin data" }))
			@RequestParam("messageData") String messageData) {

		log.info("Sending message to exchange: {}, routing key: {}, message data: {}", exchange, routingKey,
				messageData);

		amqpTemplate.convertAndSend(exchange, routingKey, messageData);

		log.info("Message sent to the RabbitMQ Successfully to exchange: {} with routingKey: {}", exchange, routingKey);
		return "Message sent to RabbitMQ Successfully";
	}
	
	
	@GetMapping("/consumeQueue/{queueName}")
	public ResponseEntity<String> consumeQueue(
			@Parameter(description = "Queue names" , schema = @Schema(type = "string", allowableValues = {"marketingQueue",
				"financeQueue",	"adminQueue"}) )
			@PathVariable String queueName) {
		log.info("Attempting to consume message from dynamic queue: {}", queueName);
		String message = consumeMessageFromQueue(queueName);
		return ResponseEntity.ok(message);
	}


	private String consumeMessageFromQueue(String queueName) {
		log.debug("Consuming message from queue: {}", queueName);

		String message = (String) rabbitTemplate.receiveAndConvert(queueName);

		if (message != null) {
			log.info("Received message from {}: {}", queueName, message);
		} else {
			log.info("No messages available in queue: {}", queueName);
		}

		return message != null ? message : "No message in queue: " + queueName;
	}

}
