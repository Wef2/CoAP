package com.mcl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kim on 2016-02-18.
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

}