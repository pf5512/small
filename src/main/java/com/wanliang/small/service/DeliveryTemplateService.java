
package com.wanliang.small.service;

import com.wanliang.small.entity.DeliveryTemplate;
import com.wanliang.small.entity.DeliveryTemplate;

/**
 * Service - 快递单模板
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface DeliveryTemplateService extends BaseService<DeliveryTemplate, Long> {

	/**
	 * 查找默认快递单模板
	 * 
	 * @return 默认快递单模板，若不存在则返回null
	 */
	DeliveryTemplate findDefault();

}