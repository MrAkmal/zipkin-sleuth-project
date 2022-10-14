package com.example.microservice1;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("logs")
public class Logs {

    @Id
    private String id;

    private String spanId;

    private String correlationId;

    public Logs(String spanId,String correlationId){
        this.spanId = spanId;
        this.correlationId = correlationId;
    }

}
