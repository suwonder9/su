package com.wonder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.expression.Lists;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
public class homeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/home")
    public String home(){
        return "/home";
    }

    @GetMapping("/login")
    public String login(){
        logger.info("login now ,{}", LocalDateTime.now());
        return "login";
    }

    @GetMapping("/travelList")
    public Object travelList(){
        logger.info("login now ,{}", LocalDateTime.now());
        List<?> list = new ArrayList<>();


        return list;
    }
}
