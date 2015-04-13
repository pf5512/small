
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.entity.Coupon;
import com.wanliang.small.service.CouponService;
import com.wanliang.small.Page;
import com.wanliang.small.Pageable;
import com.wanliang.small.dao.CouponDao;
import com.wanliang.small.entity.Coupon;
import com.wanliang.small.service.CouponService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 优惠券
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("couponServiceImpl")
public class CouponServiceImpl extends BaseServiceImpl<Coupon, Long> implements CouponService {

	@Resource(name = "couponDaoImpl")
	private CouponDao couponDao;

	@Resource(name = "couponDaoImpl")
	public void setBaseDao(CouponDao couponDao) {
		super.setBaseDao(couponDao);
	}

	@Transactional(readOnly = true)
	public Page<Coupon> findPage(Boolean isEnabled, Boolean isExchange, Boolean hasExpired, Pageable pageable) {
		return couponDao.findPage(isEnabled, isExchange, hasExpired, pageable);
	}

}