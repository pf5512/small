
package com.wanliang.small.service;

/**
 * Service - 缓存
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface CacheService {

	/**
	 * 获取缓存存储路径
	 * 
	 * @return 缓存存储路径
	 */
	String getDiskStorePath();

	/**
	 * 获取缓存数
	 * 
	 * @return 缓存数
	 */
	int getCacheSize();

	/**
	 * 清除缓存
	 */
	void clear();

}