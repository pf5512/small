
package com.wanliang.small.dao;

import com.wanliang.small.entity.Seo;
import com.wanliang.small.entity.Seo.Type;

/**
 * Dao - SEO设置
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface SeoDao extends BaseDao<Seo, Long> {

	/**
	 * 查找SEO设置
	 * 
	 * @param type
	 *            类型
	 * @return SEO设置
	 */
	Seo find(Type type);

}