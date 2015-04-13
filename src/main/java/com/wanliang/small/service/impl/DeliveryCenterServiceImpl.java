
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.dao.DeliveryCenterDao;
import com.wanliang.small.entity.DeliveryCenter;
import com.wanliang.small.service.DeliveryCenterService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 广告
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("deliveryCenterServiceImpl")
public class DeliveryCenterServiceImpl extends BaseServiceImpl<DeliveryCenter, Long> implements DeliveryCenterService {

	@Resource(name = "deliveryCenterDaoImpl")
	private DeliveryCenterDao deliveryCenterDao;

	@Resource(name = "deliveryCenterDaoImpl")
	public void setBaseDao(DeliveryCenterDao DeliveryCenterDao) {
		super.setBaseDao(DeliveryCenterDao);
	}

	@Transactional(readOnly = true)
	public DeliveryCenter findDefault() {
		return deliveryCenterDao.findDefault();
	}

}