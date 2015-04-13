
package com.wanliang.small.dao;

import com.wanliang.small.entity.Log;
import com.wanliang.small.entity.Log;

/**
 * Dao - 日志
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface LogDao extends BaseDao<Log, Long> {

	/**
	 * 删除所有日志
	 */
	void removeAll();

}