package com.hand.sales.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.sales.dto.OmOrderLines;

import java.util.List;

public interface OmOrderLinesMapper extends Mapper<OmOrderLines>{

    List<OmOrderLines> selectByLine(OmOrderLines omOrderLines);

}