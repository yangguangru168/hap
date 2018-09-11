package com.hand.sales.mapper;

import com.hand.hap.core.IRequest;
import com.hand.hap.mybatis.common.Mapper;
import com.hand.sales.dto.OmOrderHeaders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OmOrderHeadersMapper extends Mapper<OmOrderHeaders>{

    List<OmOrderHeaders> queryAll(OmOrderHeaders dto);
}