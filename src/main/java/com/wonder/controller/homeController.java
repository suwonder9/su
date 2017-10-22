package com.wonder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/10/21 0021.
 */
@Controller
public class homeController {

    @RequestMapping("/")
    public String home(){
        return "/home";
    }
}
