package com.wonder.entity;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DepositAccountLog {

    private String accountId;
    private String accountName;
    private String region;
    private Double transactionAmount;
    private String transactionDate;
    private String logId;
    private String referId;
    private String category;
    private String subCategory;
    private String operatorName;
    private String operateDate;
    private String comments;

}
