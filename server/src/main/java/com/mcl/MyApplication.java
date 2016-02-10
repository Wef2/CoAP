package com.mcl;

import com.mcl.domain.Greeting;
import com.mcl.repository.GreetingRepository;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.SocketException;

@SpringBootApplication
public class MyApplication {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        try {
            MyServer server = new MyServer();
            server.start();
        } catch (SocketException e) {
            System.err.println("Failed to initialize server: " + e.getMessage());
        }
        SpringApplication.run(MyApplication.class, args);
    }

    @Autowired
    private GreetingRepository repository;

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            // save a couple of customers
            repository.save(new Greeting(1, "test"));
            repository.save(new Greeting(2, "test2"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Greeting greeting : repository.findAll()) {
                log.info(greeting.toString());
            }
            log.info("");

        };
    }
}
