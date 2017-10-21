package com.wonder.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/10/21 0021.
 */
@RestController
public class homeController {

    @RequestMapping("/")
    public String home(){
        return "welcome home";
    }
}
