package com.example.demo.Dog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DogConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            DogRepository repository
    ) {
        return args -> {

            Dog buddy = new Dog(
                    "Buddy",
                    "Golden Retriever",
                    LocalDate.of(2020, 3, 15),
                    "601-555-1234"
            );

            Dog bella = new Dog(
                    "Bella",
                    "French Bulldog",
                    LocalDate.of(2021, 7, 10),
                    "601-555-5678"
            );

            repository.saveAll(
                    List.of(buddy, bella)
            );
        };
    }
}