
package com.wanliang.small.service;

import com.wanliang.small.entity.Log;
import com.wanliang.small.entity.Log;

/**
 * Service - 日志
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface LogService extends BaseService<Log, Long> {

	/**
	 * 清空日志
	 */
	void clear();

}