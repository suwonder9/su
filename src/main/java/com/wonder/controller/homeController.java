package com.wonder.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.wonder.mapper.TestMapper;
import com.wonder.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.expression.Lists;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
    public void travelList() throws IOException {
        logger.info("login now ,{}", LocalDateTime.now());
        reportService.exportBigDataExcel("D:/report/11.xls");
    }
}
