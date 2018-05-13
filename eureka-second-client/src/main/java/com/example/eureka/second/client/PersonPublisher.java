package com.example.eureka.second.client;


import java.util.Random;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

@EnableBinding(PersonSource.class)
public class PersonPublisher {
	
	Random r = new Random();

	@Bean
	@InboundChannelAdapter(channel = "customerPersonChannel", poller = @Poller(fixedDelay = "2000"))
	public MessageSource<Person> sendPerson() {
//		return "{name:\"Jacob\", address:\"21st Avenue\"}";
		return () -> MessageBuilder.withPayload(new Person("Jason", "Kantstr.")).setHeader("age", r.nextInt(8) * 10).build();
	}

	class Person {
		public String name;
		public String address;

		public Person(String name, String address) {
			super();
			this.name = name;
			this.address = address;
		}

	}
}
