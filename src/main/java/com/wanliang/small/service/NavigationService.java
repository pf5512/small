
package com.wanliang.small.service;

import java.util.List;

import com.wanliang.small.Filter;
import com.wanliang.small.Filter;
import com.wanliang.small.Order;
import com.wanliang.small.entity.Navigation;
import com.wanliang.small.entity.Navigation.Position;

/**
 * Service - 导航
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface NavigationService extends BaseService<Navigation, Long> {

	/**
	 * 查找导航
	 * 
	 * @param position
	 *            位置
	 * @return 导航
	 */
	List<Navigation> findList(Position position);

	/**
	 * 查找导航(缓存)
	 * 
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @param cacheRegion
	 *            缓存区域
	 * @return 导航(缓存)
	 */
	List<Navigation> findList(Integer count, List<Filter> filters, List<Order> orders, String cacheRegion);

}