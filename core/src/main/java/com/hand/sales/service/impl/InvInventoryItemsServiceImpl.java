package com.hand.sales.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import com.hand.sales.dto.OmOrderLines;
import com.hand.sales.mapper.InvInventoryItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.sales.dto.InvInventoryItems;
import com.hand.sales.service.IInvInventoryItemsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class InvInventoryItemsServiceImpl extends BaseServiceImpl<InvInventoryItems> implements IInvInventoryItemsService{

    @Autowired
    private InvInventoryItemsMapper invInventoryItemsMapper;
    @Override
    public List<InvInventoryItems> myBranchLinSelect(IRequest iRequest, InvInventoryItems list,int page,int pageNum) {
        PageHelper.startPage(page,pageNum);
        List<InvInventoryItems> invInventoryItems = invInventoryItemsMapper.selectItem(iRequest,list);
        return invInventoryItems;
    }

}