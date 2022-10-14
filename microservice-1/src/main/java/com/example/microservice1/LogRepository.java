package com.example.microservice1;


import org.springframework.data.mongodb.repository.MongoRepository;


public interface LogRepository extends MongoRepository<Logs,String> {





}
