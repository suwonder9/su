package com.wonder.mapper;

import com.wonder.entity.DepositAccountLog;
import com.wonder.entity.individualTicketVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<individualTicketVo> individualTicketQuery();
    Integer individualTicketCount();
    Integer depositAccountLogCount();

    List<DepositAccountLog> depositAccountLogQuery();
}
