
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.dao.CartItemDao;
import com.wanliang.small.entity.CartItem;
import com.wanliang.small.service.CartItemService;
import com.wanliang.small.dao.CartItemDao;
import com.wanliang.small.entity.CartItem;
import com.wanliang.small.service.CartItemService;

import org.springframework.stereotype.Service;

/**
 * Service - 购物车项
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("cartItemServiceImpl")
public class CartItemServiceImpl extends BaseServiceImpl<CartItem, Long> implements CartItemService {

	@Resource(name = "cartItemDaoImpl")
	public void setBaseDao(CartItemDao cartItemDao) {
		super.setBaseDao(cartItemDao);
	}

}