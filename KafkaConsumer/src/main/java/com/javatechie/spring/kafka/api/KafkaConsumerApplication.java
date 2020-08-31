package com.javatechie.spring.kafka.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaConsumerApplication {

	List<String> messages = new ArrayList<>();
	User userFromTopic = null;
	
	@GetMapping("/consumeStringMessage")
	public List<String> consumeMessage() {
		return messages; 
	}
	
	@GetMapping("/consumeJsonMessage")
	public User consumeJsonMessage() {
		return userFromTopic;
	}
	
	@KafkaListener(groupId = "test-group1",topics = "test-topic",containerFactory = "concurrentKafkaListenerContainerFactory")
	public List<String> getMsgFromTopic(String data) {
		messages.add(data);
		return messages;		
	}
	
	
	
	@KafkaListener(groupId = "test-group2", topics = "test-topic", containerFactory = "userConcurrentKafkaListenerContainerFactory")
	public User getJsonMsgFromTopic(User user) {
		userFromTopic = user;
		return userFromTopic;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}