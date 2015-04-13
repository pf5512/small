
package com.wanliang.small.service;

import com.wanliang.small.entity.AdPosition;
import com.wanliang.small.entity.AdPosition;

/**
 * Service - 广告位
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface AdPositionService extends BaseService<AdPosition, Long> {

	/**
	 * 查找广告位(缓存)
	 * 
	 * @param id
	 *            ID
	 * @param cacheRegion
	 *            缓存区域
	 * @return 广告位(缓存)
	 */
	AdPosition find(Long id, String cacheRegion);

}