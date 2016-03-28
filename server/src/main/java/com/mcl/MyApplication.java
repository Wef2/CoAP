package com.mcl;

import com.mcl.coap.MyServer;
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

import java.io.IOException;

@SpringBootApplication
public class MyApplication {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        try {
            new MyServer().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpringApplication.run(MyApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(NodeRepository nodeRepository, ItemRepository itemRepository) {
        return (args) -> {
            itemRepository.save(new Item("1-L-1", 1, "LED", "ON"));
            itemRepository.save(new Item("1-L-2", 1, "LED", "ON"));
            itemRepository.save(new Item("1-L-3", 1, "LED", "ON"));
            itemRepository.save(new Item("1-L-4", 1, "LED", "ON"));
            itemRepository.save(new Item("1-S-1", 1, "Temperature Sensor", "ON"));
            itemRepository.save(new Item("2-L-1", 2, "LED", "ON"));
            itemRepository.save(new Item("2-S-1", 2, "Temperature Sensor", "ON"));
        };
    }

}