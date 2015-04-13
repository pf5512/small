
package com.wanliang.small.service;

import java.util.List;

import com.wanliang.small.Filter;
import com.wanliang.small.entity.Brand;
import com.wanliang.small.Filter;
import com.wanliang.small.Order;
import com.wanliang.small.entity.Brand;

/**
 * Service - 品牌
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface BrandService extends BaseService<Brand, Long> {

	/**
	 * 查找品牌(缓存)
	 * 
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @param cacheRegion
	 *            缓存区域
	 * @return 品牌(缓存)
	 */
	List<Brand> findList(Integer count, List<Filter> filters, List<Order> orders, String cacheRegion);

}