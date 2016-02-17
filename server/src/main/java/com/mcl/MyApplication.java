package com.mcl;

import com.mcl.domain.Item;
import com.mcl.domain.Node;
import com.mcl.repository.ItemRepository;
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
    public CommandLineRunner demo(NodeRepository nodeRepository, ItemRepository itemRepository) {
        return (args) -> {
            nodeRepository.save(new Node(1, "Edison Board", "Node 1"));
            nodeRepository.save(new Node(2, "Edison Board", "Node 2"));

            log.info("Nodes found with findAll():");
            log.info("-------------------------------");
            for (Node node : nodeRepository.findAll()) {
                log.info(node.toString());
            }
            log.info("");


            itemRepository.save(new Item("1-L-1", 1, "LED", "ON"));
            itemRepository.save(new Item("1-L-2", 1, "LED", "ON"));
            itemRepository.save(new Item("1-L-3", 1, "LED", "ON"));
            itemRepository.save(new Item("1-L-4", 1, "LED", "ON"));
            itemRepository.save(new Item("1-S-1", 1, "Temperature Sensor", "ON"));

            log.info("Nodes found with findByNodeId():");
            log.info("-------------------------------");
            for (Item item : itemRepository.findByNodeId(1)) {
                log.info(item.toString());
            }
            log.info("");

            log.info("Nodes found with findByType():");
            log.info("-------------------------------");
            for (Item item : itemRepository.findByItemType("LED")) {
                log.info(item.toString());
            }
            log.info("");

            log.info("Nodes found with findByNodeIdAndItemType():");
            log.info("-------------------------------");
            for (Item item : itemRepository.findItemsByNodeIdAndItemType(1, "Temperature Sensor")) {
                log.info(item.toString());
            }
            log.info("");
        };
    }

}