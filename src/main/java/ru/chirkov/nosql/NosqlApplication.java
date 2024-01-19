package ru.chirkov.nosql;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="NoSQL sandbox"))
public class NosqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(NosqlApplication.class, args);
    }

}