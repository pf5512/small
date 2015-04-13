
package com.wanliang.small.dao.impl;

import com.wanliang.small.dao.PaymentMethodDao;
import com.wanliang.small.entity.PaymentMethod;

import org.springframework.stereotype.Repository;

/**
 * Dao - 支付方式
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Repository("paymentMethodDaoImpl")
public class PaymentMethodDaoImpl extends BaseDaoImpl<PaymentMethod, Long> implements PaymentMethodDao {

}