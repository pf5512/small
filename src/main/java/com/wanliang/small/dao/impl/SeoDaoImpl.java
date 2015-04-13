
package com.wanliang.small.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import com.wanliang.small.dao.SeoDao;
import com.wanliang.small.entity.Seo;
import com.wanliang.small.dao.SeoDao;
import com.wanliang.small.entity.Seo;
import com.wanliang.small.entity.Seo.Type;

import org.springframework.stereotype.Repository;

/**
 * Dao - SEO设置
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Repository("seoDaoImpl")
public class SeoDaoImpl extends BaseDaoImpl<Seo, Long> implements SeoDao {

	public Seo find(Seo.Type type) {
		if (type == null) {
			return null;
		}
		try {
			String jpql = "select seo from Seo seo where seo.type = :type";
			return entityManager.createQuery(jpql, Seo.class).setFlushMode(FlushModeType.COMMIT).setParameter("type", type).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}