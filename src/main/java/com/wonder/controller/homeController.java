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


    private static String DEPOSITACCOUNTLOG_COLUMN = "accountId,accountName,region,transactionDate,logId,category,subCategory,transactionAmount,operatorName,operateDate,comments";

    private static String DEPOSITACCOUNTLOG_TITLE = "账号id,代理商名称,区域,交易日期,日志id,交易类型,交易方式,交易金额,操作人,操作日期,备注";


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
}
