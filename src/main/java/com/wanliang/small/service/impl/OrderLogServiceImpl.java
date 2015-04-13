
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.dao.OrderLogDao;
import com.wanliang.small.entity.OrderLog;
import com.wanliang.small.service.OrderLogService;

import org.springframework.stereotype.Service;

/**
 * Service - 订单日志
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("orderLogServiceImpl")
public class OrderLogServiceImpl extends BaseServiceImpl<OrderLog, Long> implements OrderLogService {

	@Resource(name = "orderLogDaoImpl")
	public void setBaseDao(OrderLogDao orderLogDao) {
		super.setBaseDao(orderLogDao);
	}

}