package com.springboot.confluent.api.consumer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.confluent.api.consumer.dao.Customer;

@RestController
@Component

public class ConfluentConsumerApplication {
	
	private Logger logger = LoggerFactory.getLogger(ConfluentConsumerApplication.class);
	
	List<Customer> listCustomerFromTopic = new ArrayList<>();
	
	Customer customerFromTopic ;
	String customerFirstName;
	
	
	@KafkaListener(groupId = "test-group1", topics = "test-topic", containerFactory = "customerConcurrentKafkaListenerContainerFactory")
	public void getArrayJsonMsgFromTopic(Customer customer) {	
		logger.info("****** Customer  Name is "+customer.getFirstName());
		listCustomerFromTopic.add(customer);
	}
	
	@KafkaListener(groupId = "test-group2", topics = "test-topic", containerFactory = "customerConcurrentKafkaListenerContainerFactory")
	public void getJsonMsgFromTopic(Customer customer) {	
		logger.info("****** Customer  Name is "+customer.getFirstName());
		customerFromTopic = customer;
	}
	
	
	@KafkaListener(groupId = "test-group3", topics = "test-topic", containerFactory = "customerConcurrentKafkaListenerContainerFactory")
	public void getStringMsgFromTopic(Customer customer) {
		    logger.info("****** Customer  Name is "+customer.getFirstName());
		    customerFirstName = (String) customer.getFirstName();			
	}
	
	@GetMapping("/consumeArrayJsonMessage")
	public List<Customer> consumeArrayJsonMessage() {
		return listCustomerFromTopic;
	}
	
	@GetMapping("/consumeJsonMessage")
	public Customer consumeJsonMessage() {
		return customerFromTopic;
	}
	
	@GetMapping("/consumeStringMessage")
	public String consumeStringMessage() {
		return customerFirstName;
	}
}
