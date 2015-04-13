
package com.wanliang.small.dao.impl;

import com.wanliang.small.entity.OrderItem;
import com.wanliang.small.dao.OrderItemDao;
import com.wanliang.small.entity.OrderItem;

import org.springframework.stereotype.Repository;

/**
 * Dao - 订单项
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Repository("orderItemDaoImpl")
public class OrderItemDaoImpl extends BaseDaoImpl<OrderItem, Long> implements OrderItemDao {

}