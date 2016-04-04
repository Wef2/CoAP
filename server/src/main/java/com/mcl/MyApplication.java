package com.mcl;

import com.mcl.coap.MyServer;
import com.mcl.domain.Item;
import com.mcl.controller.MessageController;
import com.mcl.repository.ItemRepository;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyApplication {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(MessageController messageController) {

        return (args) -> {
            MyServer myServer = new MyServer();
            myServer.start();
            myServer.getConnResource().setMessageController(messageController);
        };
    }

}