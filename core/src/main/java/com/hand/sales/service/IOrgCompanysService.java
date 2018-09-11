package com.hand.sales.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.sales.dto.OrgCompanys;

import java.util.List;

public interface IOrgCompanysService extends IBaseService<OrgCompanys>, ProxySelf<IOrgCompanysService>{
    /**
     *
     * @param iRequest
     * @return
     */
    List<OrgCompanys> selectByCompany(IRequest iRequest,OrgCompanys orgCompanys,int page,int pageSize);

}