package com.example.eureka.address;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PersonSource {

	@Output("customerPersonChannel")
	MessageChannel customerPerson();
	
	@Output("employeePersonChannel")
	MessageChannel employeePerson();
}
