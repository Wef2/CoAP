package com.mcl.controller;

import com.mcl.domain.Greeting;
import com.mcl.repository.GreetingRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Kim on 2016-02-10.
 */

@RestController
public class InfoController {

    private GreetingRepository greetingRepository;
    private int count = 0;

    @RequestMapping("/greeting")
    public Iterable<Greeting> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        count++;
        greetingRepository.save(new Greeting(count, "test"));
        System.out.println(greetingRepository.findAll());
        return greetingRepository.findAll();
    }
}
