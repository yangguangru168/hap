package com.hand.sales.mapper;

import com.hand.hap.core.IRequest;
import com.hand.hap.mybatis.common.Mapper;
import com.hand.sales.dto.ArCustomers;
import com.hand.sales.dto.OrgCompanys;

import java.util.List;

public interface ArCustomersMapper extends Mapper<ArCustomers>{

    List<ArCustomers> myByCustomer(IRequest iRequest,ArCustomers customers);

}