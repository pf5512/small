
package com.wanliang.small.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.wanliang.small.dao.TagDao;
import com.wanliang.small.entity.Tag;
import com.wanliang.small.entity.Tag.Type;

import org.springframework.stereotype.Repository;

/**
 * Dao - 标签
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Repository("tagDaoImpl")
public class TagDaoImpl extends BaseDaoImpl<Tag, Long> implements TagDao {

	public List<Tag> findList(Type type) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
		Root<Tag> root = criteriaQuery.from(Tag.class);
		criteriaQuery.select(root);
		if (type != null) {
			criteriaQuery.where(criteriaBuilder.equal(root.get("type"), type));
		}
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("order")));
		return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
	}

}