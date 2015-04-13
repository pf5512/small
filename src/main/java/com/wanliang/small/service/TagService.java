
package com.wanliang.small.service;

import java.util.List;

import com.wanliang.small.Filter;
import com.wanliang.small.Filter;
import com.wanliang.small.Order;
import com.wanliang.small.entity.Tag;
import com.wanliang.small.entity.Tag.Type;

/**
 * Service - 标签
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface TagService extends BaseService<Tag, Long> {

	/**
	 * 查找标签
	 * 
	 * @param type
	 *            类型
	 * @return 标签
	 */
	List<Tag> findList(Type type);

	/**
	 * 查找标签(缓存)
	 * 
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @param cacheRegion
	 *            缓存区域
	 * @return 标签(缓存)
	 */
	List<Tag> findList(Integer count, List<Filter> filters, List<Order> orders, String cacheRegion);

}