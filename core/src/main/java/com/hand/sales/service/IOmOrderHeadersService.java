package com.hand.sales.service;

import com.hand.hap.code.rule.exception.CodeRuleException;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.sales.dto.OmOrderHeaders;

import java.util.List;

public interface IOmOrderHeadersService extends IBaseService<OmOrderHeaders>, ProxySelf<IOmOrderHeadersService>{
    /**
     * 查询所有头数据
     * @param iRequest
     * @param list
     * @param page
     * @param pageSize
     * @return
     */
    List<OmOrderHeaders> queryAll(IRequest iRequest, OmOrderHeaders list, int page, int pageSize);

    List<OmOrderHeaders> queryLineAll(IRequest iRequest,OmOrderHeaders omOrderHeaders);

    List<OmOrderHeaders> myBachSubmit(IRequest iRequest,List<OmOrderHeaders> list) throws CodeRuleException;

    List<OmOrderHeaders> selectAllHead(IRequest iRequest,List<OmOrderHeaders> list);
}