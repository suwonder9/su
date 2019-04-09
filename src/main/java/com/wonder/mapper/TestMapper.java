package com.wonder.mapper;

import com.wonder.entity.DepositAccountLog;
import com.wonder.entity.IndividualTicketVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<IndividualTicketVo> individualTicketQuery();
    Integer individualTicketCount();
    Integer depositAccountLogCount();

    List<DepositAccountLog> depositAccountLogQuery();
}
