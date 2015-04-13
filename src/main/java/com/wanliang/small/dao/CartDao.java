
package com.wanliang.small.dao;

import com.wanliang.small.entity.Cart;
import com.wanliang.small.entity.Cart;

/**
 * Dao - 购物车
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface CartDao extends BaseDao<Cart, Long> {

	/**
	 * 清除过期购物车
	 */
	void evictExpired();

}