
package com.wanliang.small.dao;

import java.util.List;

import com.wanliang.small.entity.FriendLink;
import com.wanliang.small.entity.FriendLink.Type;

/**
 * Dao - 友情链接
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface FriendLinkDao extends BaseDao<FriendLink, Long> {

	/**
	 * 查找友情链接
	 * 
	 * @param type
	 *            类型
	 * @return 友情链接
	 */
	List<FriendLink> findList(Type type);

}