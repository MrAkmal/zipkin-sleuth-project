package com.example.microservice1;


import brave.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class TestController {


    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final Tracer tracer;

    private final RestTemplate restTemplate;


    public TestController(Tracer tracer, RestTemplate restTemplate, LogRepository repository) {
        this.tracer = tracer;
        this.restTemplate = restTemplate;
        this.repository = repository;
    }


    private final LogRepository repository;
    private final String URI = "http://localhost:2020";

    @GetMapping("/info")
    public List<User> getUser() {

        logger.info("inside Microservice - 1");

        String traceId = tracer.currentSpan().context().traceIdString();
        String spanId = tracer.currentSpan().context().spanIdString();


        repository.save(new Logs(spanId, traceId));

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>("from microservice-1", httpHeaders);

        ResponseEntity<List<User>> exchange = null;
        for (int i = 0; i < 12; i++) {

            exchange = restTemplate.exchange(URI, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<User>>() {
            });
        }
        return exchange.getBody();
    }


}
