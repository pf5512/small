
package com.wanliang.small.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import com.wanliang.small.dao.AreaDao;
import com.wanliang.small.entity.Area;
import com.wanliang.small.dao.AreaDao;
import com.wanliang.small.entity.Area;

import org.springframework.stereotype.Repository;

/**
 * Dao - 地区
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Repository("areaDaoImpl")
public class AreaDaoImpl extends BaseDaoImpl<Area, Long> implements AreaDao {

	public List<Area> findRoots(Integer count) {
		String jpql = "select area from Area area where area.parent is null order by area.order asc";
		TypedQuery<Area> query = entityManager.createQuery(jpql, Area.class).setFlushMode(FlushModeType.COMMIT);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

}