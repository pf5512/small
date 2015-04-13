
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.entity.ShippingMethod;
import com.wanliang.small.service.ShippingMethodService;
import com.wanliang.small.dao.ShippingMethodDao;
import com.wanliang.small.entity.ShippingMethod;
import com.wanliang.small.service.ShippingMethodService;

import org.springframework.stereotype.Service;

/**
 * Service - 配送方式
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("shippingMethodServiceImpl")
public class ShippingMethodServiceImpl extends BaseServiceImpl<ShippingMethod, Long> implements ShippingMethodService {

	@Resource(name = "shippingMethodDaoImpl")
	public void setBaseDao(ShippingMethodDao shippingMethodDao) {
		super.setBaseDao(shippingMethodDao);
	}

}