package com.hand.sales.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import com.hand.sales.mapper.OrgCompanysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.sales.dto.OrgCompanys;
import com.hand.sales.service.IOrgCompanysService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrgCompanysServiceImpl extends BaseServiceImpl<OrgCompanys> implements IOrgCompanysService{
    @Autowired
    private OrgCompanysMapper companysMapper;
    @Override
    public List<OrgCompanys> selectByCompany(IRequest iRequest,OrgCompanys orgCompanys, int page,int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<OrgCompanys> companys = companysMapper.myByCompany(iRequest,orgCompanys);
        return companys;
    }
}