package com.hand.sales.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import com.hand.sales.mapper.ArCustomersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.sales.dto.ArCustomers;
import com.hand.sales.service.IArCustomersService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ArCustomersServiceImpl extends BaseServiceImpl<ArCustomers> implements IArCustomersService{

    @Autowired
    private ArCustomersMapper customersMapper;
    @Override
    public List<ArCustomers> selectByCustomer(IRequest iRequest, ArCustomers customers, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<ArCustomers> customersList = customersMapper.myByCustomer(iRequest,customers);
        return customersList;
    }
}