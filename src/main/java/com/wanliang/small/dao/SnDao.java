
package com.wanliang.small.dao;

import com.wanliang.small.entity.Sn.Type;

/**
 * Dao - 序列号
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface SnDao {

	/**
	 * 生成序列号
	 * 
	 * @param type
	 *            类型
	 * @return 序列号
	 */
	String generate(Type type);

}