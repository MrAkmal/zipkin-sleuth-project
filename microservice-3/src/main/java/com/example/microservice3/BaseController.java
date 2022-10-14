package com.example.microservice3;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class BaseController {

    private final Logger logger = LoggerFactory.getLogger(BaseController.class);

    private final UserRepository repository;

    private final RestTemplate restTemplate;

    private final String microservice4URI = "http://localhost:3030";


    public BaseController(UserRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }


    @SneakyThrows
    @CircuitBreaker(name = "backendB")
    @RateLimiter(name = "backendB")
    @Bulkhead(name = "backendB")
    @Retry(name = "backendB")
    @GetMapping
    public List<User> getAll() {
        Thread.sleep(2000);
        logger.info("inside Microservice - 3");

        ResponseEntity<String> exchange = restTemplate.exchange(microservice4URI, HttpMethod.GET, null, String.class);
        System.out.println("exchange.getBody() = " + exchange.getBody());

        return repository.findAll();
    }






}
