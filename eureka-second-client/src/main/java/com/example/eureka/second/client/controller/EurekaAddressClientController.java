package com.example.eureka.second.client.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EurekaAddressClientController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    private final String EUREKA_NAME_CLIENT = "EUREKA-NAME-CLIENT";

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping("/addresses")
    public List<String> getAddresses() {

        ResponseEntity<List<String>> nameResponse =
                restTemplate.exchange("http://" + EUREKA_NAME_CLIENT + "/names",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
                        });

        List<String> addresses = new ArrayList<>();

        addresses.add("21st Jump Street");
        addresses.add("Beverly Hills");

        List<String> combined = nameResponse.getBody()
                .stream()
                .map(name -> name + " lives in " + addresses.get(nameResponse	.getBody().indexOf(name)))
                .collect(Collectors.toList());

        return combined;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
