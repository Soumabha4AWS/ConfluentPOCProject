package com.springboot.confluent.api.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Component
public class ConfluentProducerApplication  {
	
public String topic = "test-topic";
	
	@Autowired
	private KafkaTemplate<String , Customer> kafkaTemplate;
	
	@GetMapping("/publishJson")
	public Customer publishMessage() {
		
		
		Customer customer = Customer.newBuilder()
				.setFirstName("Soum")
				.setLastName("Sengupta")
				.setAge(41)
				.setHeight(5.11f)
				.setWeight(180)
				.build();
		
		// Customer customer = new Customer("Soum","Sengup",41,5.11f,(float) 180,true);
		
		kafkaTemplate.send(topic, customer);
		
		return customer;
	}
		

}
