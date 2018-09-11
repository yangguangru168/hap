package com.hand.sales.mapper;

import com.hand.hap.core.IRequest;
import com.hand.hap.mybatis.common.Mapper;
import com.hand.sales.dto.InvInventoryItems;

import java.util.List;

public interface InvInventoryItemsMapper extends Mapper<InvInventoryItems>{

    List<InvInventoryItems> selectItem(IRequest iRequest, InvInventoryItems invInventoryItems );
}