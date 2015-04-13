
package com.wanliang.small.service;

import java.util.List;

import com.wanliang.small.entity.Area;
import com.wanliang.small.entity.Area;

/**
 * Service - 地区
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface AreaService extends BaseService<Area, Long> {

	/**
	 * 查找顶级地区
	 * 
	 * @return 顶级地区
	 */
	List<Area> findRoots();

	/**
	 * 查找顶级地区
	 * 
	 * @param count
	 *            数量
	 * @return 顶级地区
	 */
	List<Area> findRoots(Integer count);

}