package com.mcl;

import com.mcl.domain.Node;
import com.mcl.repository.NodeRepository;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Bean
    public CommandLineRunner demo(NodeRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Node(1, "Node 1"));
            repository.save(new Node(2, "Node 2"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Node node : repository.findAll()) {
                log.info(node.toString());
            }
            log.info("");
        };
    }


}
