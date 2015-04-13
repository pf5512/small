
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.service.DeliveryCorpService;
import com.wanliang.small.dao.DeliveryCorpDao;
import com.wanliang.small.entity.DeliveryCorp;
import com.wanliang.small.service.DeliveryCorpService;

import org.springframework.stereotype.Service;

/**
 * Service - 物流公司
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("deliveryCorpServiceImpl")
public class DeliveryCorpServiceImpl extends BaseServiceImpl<DeliveryCorp, Long> implements DeliveryCorpService {

	@Resource(name = "deliveryCorpDaoImpl")
	public void setBaseDao(DeliveryCorpDao deliveryCorpDao) {
		super.setBaseDao(deliveryCorpDao);
	}

}