package com.example.microservice4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    private final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @GetMapping
    public String hello() {
        logger.info("inside Microservice - 4");
        return "Hello from microservice - 4";
    }

}
