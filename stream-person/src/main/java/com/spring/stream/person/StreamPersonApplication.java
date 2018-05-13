package com.spring.stream.person;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(Sink.class)
//@EnableBinding(Processor.class)
@SpringBootApplication
public class StreamPersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamPersonApplication.class, args);
	}
	
	@StreamListener(target=Sink.INPUT, condition="headers['age'] > 50")
//	@ServiceActivator(inputChannel=Sink.INPUT)
//	@StreamListener(Processor.INPUT)
//	@SendTo(Processor.OUTPUT)
	public void logOldPerson(String msg) {
		System.out.println("old person, age: " + msg);
	}
	
	@StreamListener(target=Sink.INPUT, condition="headers['age'] <= 50")
//	@ServiceActivator(inputChannel=Sink.INPUT)
//	@StreamListener(Processor.INPUT)
//	@SendTo(Processor.OUTPUT)
	public void logYoungPerson(String msg) {
		System.out.println("young person, age: " + msg);
	}
}
