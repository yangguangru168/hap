package com.hand.sales.mapper;

import com.hand.hap.core.IRequest;
import com.hand.hap.mybatis.common.Mapper;
import com.hand.sales.dto.OrgCompanys;

import java.util.List;

public interface OrgCompanysMapper extends Mapper<OrgCompanys>{

    List<OrgCompanys> myByCompany(IRequest iRequest,OrgCompanys orgCompanys);

}