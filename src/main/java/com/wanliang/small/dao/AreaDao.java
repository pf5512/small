
package com.wanliang.small.dao;

import java.util.List;

import com.wanliang.small.entity.Area;
import com.wanliang.small.entity.Area;

/**
 * Dao - 地区
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface AreaDao extends BaseDao<Area, Long> {

	/**
	 * 查找顶级地区
	 * 
	 * @param count
	 *            数量
	 * @return 顶级地区
	 */
	List<Area> findRoots(Integer count);

}