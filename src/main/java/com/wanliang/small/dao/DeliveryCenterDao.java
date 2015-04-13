
package com.wanliang.small.dao;

import com.wanliang.small.entity.DeliveryCenter;
import com.wanliang.small.entity.DeliveryCenter;

/**
 * Dao - 发货点
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface DeliveryCenterDao extends BaseDao<DeliveryCenter, Long> {

	/**
	 * 查找默认发货点
	 * 
	 * @return 默认发货点，若不存在则返回null
	 */
	DeliveryCenter findDefault();

}