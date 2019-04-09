package com.wonder.controller;

import com.wonder.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;


@Controller
@Slf4j
public class homeController {


    @Autowired
    private ReportService reportService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/home")
    public String home(){
        logger.info("home ,{}", LocalDateTime.now());
        return "/home";
    }

    @GetMapping("/login")
    public String login(){
        logger.info("login now ,{}", LocalDateTime.now());
        return "login";
    }

    @GetMapping("/list")
    @ResponseBody
    public void travelList(HttpServletResponse response) throws IOException {
        logger.info("login now ,{}", LocalDateTime.now());
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode( String.valueOf(System.currentTimeMillis())+".xls", "UTF-8"));

        reportService.export(response);
    }
}
