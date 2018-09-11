package com.hand.sales.service;

import com.hand.hap.code.rule.exception.CodeRuleException;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.sales.dto.OmOrderHeaders;
import com.hand.sales.dto.OmOrderLines;

import java.util.List;

public interface IOmOrderLinesService extends IBaseService<OmOrderLines>, ProxySelf<IOmOrderLinesService>{
    /**
     * 查询所有行
     * @param iRequest
     * @param omOrderLines
     * @param page
     * @param pageNum
     * @return
     */
    List<OmOrderLines> selectByLine(IRequest iRequest, OmOrderLines omOrderLines ,int page ,int pageNum);

    /**
     *
     * @param omOrderLines
     * @return
     */
    List<OmOrderLines> selectLineByheaderId(OmOrderLines omOrderLines);

    /**
     *
     * @param omOrderLines
     * @return
     */
    List<OmOrderLines> selectLineAll(OmOrderLines omOrderLines);

    /**
     * 行保存
     * @param iRequest
     * @param list
     * @return
     */
    List<OmOrderLines> myBranchLineSubmit(IRequest iRequest,List<OmOrderLines> list) throws CodeRuleException;


}