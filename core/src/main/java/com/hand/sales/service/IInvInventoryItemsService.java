package com.hand.sales.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.sales.dto.InvInventoryItems;
import com.hand.sales.dto.OmOrderLines;

import java.util.List;

public interface IInvInventoryItemsService extends IBaseService<InvInventoryItems>, ProxySelf<IInvInventoryItemsService>{

    /**
     * 联级带出单位
     * @param iRequest
     * @param list
     * @return
     */
    List<InvInventoryItems> myBranchLinSelect(IRequest iRequest,InvInventoryItems list,int page, int pageSize);
}