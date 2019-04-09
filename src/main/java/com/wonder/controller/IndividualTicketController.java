package com.wonder.controller;

import com.wonder.entity.IndividualTicketVo;
import com.wonder.mapper.TestMapper;
import com.wonder.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


@RestController
@Slf4j
public class IndividualTicketController {


    @Autowired
    private TestMapper testMapper;

    @Autowired
    private ReportService reportService;

    private static String INDIVIDUAL_COLUMN = "orderId,orderDate,productName,printTicketName,proxyName,parkName,quantity,tranAmount,settlePrice,contactMobile,referenceNum,startDate,endDate,showStartTime,itemStatus,rebateType,pickupTime,pickupType,region,otaOrderId,accountType,userName,orderSource";

    private static String INDIVIDUAL_TITLE = "订单号,订票时间,门票名称,门票打印名称,协议名称,公园,总张数,总金额(含税),结算单价,订票手机号,凭证码,使用开始日期,使用结束日期,马戏场次,交易状态,返利状态,取票时间,取票方式,所属度假区,代理商自填订单号,主/子账号,账号ID,购票方式";

    private static String REPORT_NAME = "代理商散客门票订单统计报表";

    @GetMapping("/individualTicket")
    @ResponseBody
    public ResponseEntity individualTicket(HttpServletResponse response){
        List<IndividualTicketVo> individualTicketVos = testMapper.individualTicketQuery();

        reportService.export(REPORT_NAME,INDIVIDUAL_COLUMN,INDIVIDUAL_TITLE,individualTicketVos,response);
        return ResponseEntity.ok().build();
    }
}
