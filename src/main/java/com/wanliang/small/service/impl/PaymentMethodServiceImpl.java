
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.service.PaymentMethodService;
import com.wanliang.small.dao.PaymentMethodDao;
import com.wanliang.small.entity.PaymentMethod;
import com.wanliang.small.service.PaymentMethodService;

import org.springframework.stereotype.Service;

/**
 * Service - 支付方式
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("paymentMethodServiceImpl")
public class PaymentMethodServiceImpl extends BaseServiceImpl<PaymentMethod, Long> implements PaymentMethodService {

	@Resource(name = "paymentMethodDaoImpl")
	public void setBaseDao(PaymentMethodDao paymentMethodDao) {
		super.setBaseDao(paymentMethodDao);
	}

}