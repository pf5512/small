
package com.wanliang.small.service;

import com.wanliang.small.entity.Seo;
import com.wanliang.small.entity.Seo;
import com.wanliang.small.entity.Seo.Type;

/**
 * Service - SEO设置
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface SeoService extends BaseService<Seo, Long> {

	/**
	 * 查找SEO设置
	 * 
	 * @param type
	 *            类型
	 * @return SEO设置
	 */
	Seo find(Seo.Type type);

	/**
	 * 查找SEO设置(缓存)
	 * 
	 * @param type
	 *            类型
	 * @param cacheRegion
	 *            缓存区域
	 * @return SEO设置(缓存)
	 */
	Seo find(Seo.Type type, String cacheRegion);

}