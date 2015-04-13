
package com.wanliang.small.service;

import com.wanliang.small.Page;
import com.wanliang.small.Pageable;
import com.wanliang.small.entity.Coupon;

/**
 * Service - 优惠券
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface CouponService extends BaseService<Coupon, Long> {

	/**
	 * 查找优惠券分页
	 * 
	 * @param isEnabled
	 *            是否启用
	 * @param isExchange
	 *            是否允许积分兑换
	 * @param hasExpired
	 *            是否已过期
	 * @param pageable
	 *            分页信息
	 * @return 优惠券分页
	 */
	Page<Coupon> findPage(Boolean isEnabled, Boolean isExchange, Boolean hasExpired, Pageable pageable);

}