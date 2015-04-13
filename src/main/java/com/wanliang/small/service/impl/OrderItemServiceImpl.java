
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.dao.OrderItemDao;
import com.wanliang.small.entity.OrderItem;
import com.wanliang.small.service.OrderItemService;
import com.wanliang.small.dao.OrderItemDao;
import com.wanliang.small.entity.OrderItem;
import com.wanliang.small.service.OrderItemService;

import org.springframework.stereotype.Service;

/**
 * Service - 订单项
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("orderItemServiceImpl")
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItem, Long> implements OrderItemService {

	@Resource(name = "orderItemDaoImpl")
	public void setBaseDao(OrderItemDao orderItemDao) {
		super.setBaseDao(orderItemDao);
	}

}