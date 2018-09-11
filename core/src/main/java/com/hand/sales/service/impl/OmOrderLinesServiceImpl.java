package com.hand.sales.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.code.rule.exception.CodeRuleException;
import com.hand.hap.code.rule.service.ISysCodeRuleProcessService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import com.hand.sales.dto.OmOrderHeaders;
import com.hand.sales.mapper.OmOrderLinesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.sales.dto.OmOrderLines;
import com.hand.sales.service.IOmOrderLinesService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OmOrderLinesServiceImpl extends BaseServiceImpl<OmOrderLines> implements IOmOrderLinesService{
    @Autowired
    private OmOrderLinesMapper omOrderLinesMapper;
    @Autowired
    private IOmOrderLinesService iOmOrderLinesService;
    @Autowired
    private ISysCodeRuleProcessService iSysCodeRuleProcessService;
    @Override
    public List<OmOrderLines> selectByLine(IRequest iRequest, OmOrderLines omOrderLines, int page, int pageNum) {
        PageHelper.startPage(page,pageNum);
        List<OmOrderLines> list = omOrderLinesMapper.selectByLine(omOrderLines);
        return list;
    }

    @Override
    public List<OmOrderLines> selectLineByheaderId(OmOrderLines omOrderLines) {
        List<OmOrderLines> list = omOrderLinesMapper.selectByLine(omOrderLines);
        return list;
    }

    @Override
    public List<OmOrderLines> selectLineAll(OmOrderLines omOrderLines) {
        List<OmOrderLines> list = omOrderLinesMapper.select(omOrderLines);
        return list;
    }

    @Override
    public List<OmOrderLines> myBranchLineSubmit(IRequest iRequest, List<OmOrderLines> list) throws CodeRuleException {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i <list.size() ; i++) {
                OmOrderLines omOrderLines = list.get(i);
                Long lineId = omOrderLines.getLineId();
                if (lineId == null) {
                    Long linId = Long.parseLong(iSysCodeRuleProcessService.getRuleCode("HAP _OM_ORDER_LINES"));
                    omOrderLines.setLineId(linId);
                    iOmOrderLinesService.insertSelective(iRequest,omOrderLines);
                }else {
                    iOmOrderLinesService.updateByPrimaryKeySelective(iRequest,omOrderLines);
                }
            }
        }
        return list;
    }

}