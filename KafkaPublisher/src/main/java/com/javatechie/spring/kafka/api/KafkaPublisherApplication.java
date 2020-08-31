package com.javatechie.spring.kafka.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController


public class KafkaPublisherApplication {
	
	public String topic = "test-topic";
	
	@Autowired
	private KafkaTemplate<String , Object> kafkaTemplate;
	
	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name) {		
		kafkaTemplate.send(topic,"Hi "+name+", welcome here");
		return "Data is published";		
	}
 
	@GetMapping("/publishJson")
	public String publishMessage() {	
		User user = new User(1,"Shubham",new String[] {"250 Main Street","Hartford","Connecticuit"});
		kafkaTemplate.send(topic,user);
		return "Json Data is published";		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}

}
