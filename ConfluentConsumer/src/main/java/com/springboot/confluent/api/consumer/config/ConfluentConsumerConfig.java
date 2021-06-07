package com.springboot.confluent.api.consumer.config;

import java.util.HashMap;
import java.util.Map;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.springboot.confluent.api.consumer.Customer;





@Configuration
@EnableKafka

public class ConfluentConsumerConfig {

	@Bean
	public ConsumerFactory<String, Customer> customerConsumerFactory() {		
		
	    Map<String, Object> props = new HashMap<>();
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
	    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest"); 	    
	    props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
	    props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
	    props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
	    return new DefaultKafkaConsumerFactory<String, Customer>(props);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Customer> customerConcurrentKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Customer> containerFactory = new ConcurrentKafkaListenerContainerFactory<String, Customer>();
		containerFactory.setConsumerFactory(customerConsumerFactory());
		containerFactory.setAutoStartup(false);		
		containerFactory.setBatchListener(true);
		containerFactory.setConcurrency(1);		
		containerFactory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);
		containerFactory.getContainerProperties().setAckOnError(true);
		containerFactory.getContainerProperties().setLogContainerConfig(false);
        
		return containerFactory;		
	}
	
	
	
	
	
}
