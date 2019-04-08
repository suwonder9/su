package com.wonder.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class individualTicketVo {
    private String accountId;

    private BigDecimal orderId;

    private String accountName;

}
