
package com.wanliang.small.dao;

import java.util.List;

import com.wanliang.small.entity.Tag;
import com.wanliang.small.entity.Tag.Type;

/**
 * Dao - 标签
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface TagDao extends BaseDao<Tag, Long> {

	/**
	 * 查找标签
	 * 
	 * @param type
	 *            类型
	 * @return 标签
	 */
	List<Tag> findList(Type type);

}