package com.hand.sales.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.sales.dto.ArCustomers;
import com.hand.sales.dto.OrgCompanys;

import java.util.List;

public interface IArCustomersService extends IBaseService<ArCustomers>, ProxySelf<IArCustomersService>{

    List<ArCustomers> selectByCustomer(IRequest iRequest, ArCustomers customers, int page, int pageSize);

}