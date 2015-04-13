
package com.wanliang.small.dao;

import java.util.List;
import java.util.Set;

import com.wanliang.small.entity.Parameter;
import com.wanliang.small.entity.ParameterGroup;

/**
 * Dao - 参数
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface ParameterDao extends BaseDao<Parameter, Long> {

	/**
	 * 查找参数
	 * 
	 * @param parameterGroup
	 *            参数组
	 * @param excludes
	 *            排除参数
	 * @return 参数
	 */
	List<Parameter> findList(ParameterGroup parameterGroup, Set<Parameter> excludes);

}