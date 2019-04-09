package com.wonder.entity;

import lombok.Data;

@Data
public class IndividualTicketVo {
    private String orderId;
    private String orderDate;
    private String productName;
    private String printTicketName;
    private String proxyName;
    private String parkName;
    private Double quantity;
    private Double tranAmount;
    private Double settlePrice;
    private String contactMobile;
    private String referenceNum;
    private String startDate;
    private String endDate;
    private String showStartTime;
    private String itemStatus;
    private String rebateType;
    private String pickupTime;
    private String pickupType;
    private String region;
    private String otaOrderId;
    private String accountId;
    private String accountType;
    private String userName;
    private String orderSource;
    private String accountName;



}
