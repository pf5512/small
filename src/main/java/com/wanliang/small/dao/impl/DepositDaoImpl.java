
package com.wanliang.small.dao.impl;

import java.util.Collections;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.wanliang.small.Page;
import com.wanliang.small.Pageable;
import com.wanliang.small.dao.DepositDao;
import com.wanliang.small.entity.Member;
import com.wanliang.small.Page;
import com.wanliang.small.Pageable;
import com.wanliang.small.dao.DepositDao;
import com.wanliang.small.entity.Deposit;
import com.wanliang.small.entity.Member;

import org.springframework.stereotype.Repository;

/**
 * Dao - 预存款
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Repository("depositDaoImpl")
public class DepositDaoImpl extends BaseDaoImpl<Deposit, Long> implements DepositDao {

	public Page<Deposit> findPage(Member member, Pageable pageable) {
		if (member == null) {
			return new Page<Deposit>(Collections.<Deposit> emptyList(), 0, pageable);
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Deposit> criteriaQuery = criteriaBuilder.createQuery(Deposit.class);
		Root<Deposit> root = criteriaQuery.from(Deposit.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("member"), member));
		return super.findPage(criteriaQuery, pageable);
	}

}