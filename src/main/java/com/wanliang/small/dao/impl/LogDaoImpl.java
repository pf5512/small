
package com.wanliang.small.dao.impl;

import javax.persistence.FlushModeType;

import com.wanliang.small.dao.LogDao;
import com.wanliang.small.entity.Log;
import com.wanliang.small.dao.LogDao;
import com.wanliang.small.entity.Log;

import org.springframework.stereotype.Repository;

/**
 * Dao - 日志
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Repository("logDaoImpl")
public class LogDaoImpl extends BaseDaoImpl<Log, Long> implements LogDao {

	public void removeAll() {
		String jpql = "delete from Log log";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
	}

}