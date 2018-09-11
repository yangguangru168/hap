package com.hand.sales.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.code.rule.exception.CodeRuleException;
import com.hand.hap.code.rule.service.ISysCodeRuleProcessService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import com.hand.sales.dto.OmOrderLines;
import com.hand.sales.mapper.OmOrderHeadersMapper;
import com.hand.sales.service.IOmOrderLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.sales.dto.OmOrderHeaders;
import com.hand.sales.service.IOmOrderHeadersService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OmOrderHeadersServiceImpl extends BaseServiceImpl<OmOrderHeaders> implements IOmOrderHeadersService {

    @Autowired
    private IOmOrderHeadersService iOmOrderHeadersService;

    @Autowired
    private IOmOrderLinesService iOmOrderLinesService;
    @Autowired
    private OmOrderHeadersMapper omOrderHeadersMapper;
    @Autowired
    private ISysCodeRuleProcessService iSysCodeRuleProcessService;
    @Override
    public List<OmOrderHeaders> queryAll(IRequest iRequest, OmOrderHeaders list, int page, int pageNum) {
        PageHelper.startPage(page, pageNum);
        List<OmOrderHeaders> omOrderHeaders = omOrderHeadersMapper.queryAll(list);
        return omOrderHeaders;
    }

    @Override
    public List<OmOrderHeaders> queryLineAll(IRequest iRequest,OmOrderHeaders omOrderHeaders) {
        List<OmOrderHeaders> orderHeaders = omOrderHeadersMapper.queryAll(omOrderHeaders);
        return orderHeaders;
    }

    @Override
    public List<OmOrderHeaders> myBachSubmit(IRequest iRequest, List<OmOrderHeaders> list) throws CodeRuleException {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                OmOrderHeaders omOrderHeaders = list.get(i);
                Long headerId = omOrderHeaders.getHeaderId();
                Long companyId = omOrderHeaders.getCompanyId();
                if (headerId == null || headerId == 0) {
                    String orderNumber = iSysCodeRuleProcessService.getRuleCode("ORDER_NUMBER");
                    omOrderHeaders.setOrderNumber(orderNumber);
                    Long heaId =Long.parseLong (iSysCodeRuleProcessService.getRuleCode("HAP_OM _ORDER_HEADERS"));
                    omOrderHeaders.setHeaderId(heaId);
                    iOmOrderHeadersService.insertSelective(iRequest, omOrderHeaders);
                    headerId = omOrderHeaders.getHeaderId();
                    companyId = omOrderHeaders.getCompanyId();
                    List<OmOrderLines> omOrderLines = omOrderHeaders.getLinesList();
                    if (omOrderLines != null) {
                        for (int j = 0; j < omOrderLines.size(); j++) {
                             omOrderLines.get(j).setHeaderId(headerId);
                             omOrderLines.get(j).setCompanyId(companyId);
                        }
                    }
                    iOmOrderLinesService.myBranchLineSubmit(iRequest, omOrderLines);
                } else {
                    iOmOrderHeadersService.updateByPrimaryKeySelective(iRequest, omOrderHeaders);
                    List<OmOrderLines> omOrderLinesList = omOrderHeaders.getLinesList();
                    if (omOrderLinesList != null) {
                        for (int j = 0; j < omOrderLinesList.size(); j++) {
                            omOrderLinesList.get(j).setHeaderId(headerId);
                            omOrderLinesList.get(j).setCompanyId(companyId);
                        }

                    }
                    iOmOrderLinesService.myBranchLineSubmit(iRequest, omOrderLinesList);
                }
            }
        }
        return list;
    }

    @Override
    public List<OmOrderHeaders> selectAllHead(IRequest iRequest, List<OmOrderHeaders> list) {
        List<OmOrderHeaders> headersList = new ArrayList<OmOrderHeaders>();
        for (OmOrderHeaders om: list) {
            headersList.addAll(omOrderHeadersMapper.queryAll(om));
        }
        return  headersList;
    }
}