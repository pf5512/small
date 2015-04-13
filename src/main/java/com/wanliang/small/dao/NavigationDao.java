
package com.wanliang.small.dao;

import java.util.List;

import com.wanliang.small.entity.Navigation;
import com.wanliang.small.entity.Navigation.Position;

/**
 * Dao - 导航
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface NavigationDao extends BaseDao<Navigation, Long> {

	/**
	 * 查找导航
	 * 
	 * @param position
	 *            位置
	 * @return 导航
	 */
	List<Navigation> findList(Position position);

}