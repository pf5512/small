
package com.wanliang.small.service;

import java.util.List;

import com.wanliang.small.LogConfig;
import com.wanliang.small.LogConfig;

/**
 * Service - 日志配置
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface LogConfigService {

	/**
	 * 获取所有日志配置
	 * 
	 * @return 所有日志配置
	 */
	List<LogConfig> getAll();

}