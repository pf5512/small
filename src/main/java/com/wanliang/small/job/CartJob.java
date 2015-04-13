
package com.wanliang.small.job;

import javax.annotation.Resource;

import com.wanliang.small.service.CartService;
import com.wanliang.small.service.CartService;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Job - 购物车
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Component("cartJob")
@Lazy(false)
public class CartJob {

	@Resource(name = "cartServiceImpl")
	private CartService cartService;

	/**
	 * 清除过期购物车
	 */
	@Scheduled(cron = "${job.cart_evict_expired.cron}")
	public void evictExpired() {
		cartService.evictExpired();
	}

}