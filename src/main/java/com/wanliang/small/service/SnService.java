
package com.wanliang.small.service;

import com.wanliang.small.entity.Sn.Type;

/**
 * Service - 序列号
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface SnService {

	/**
	 * 生成序列号
	 * 
	 * @param type
	 *            类型
	 * @return 序列号
	 */
	String generate(Type type);

}