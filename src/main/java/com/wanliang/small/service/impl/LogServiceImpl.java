
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.dao.LogDao;
import com.wanliang.small.entity.Log;
import com.wanliang.small.service.LogService;
import com.wanliang.small.dao.LogDao;
import com.wanliang.small.entity.Log;
import com.wanliang.small.service.LogService;

import org.springframework.stereotype.Service;

/**
 * Service - 日志
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("logServiceImpl")
public class LogServiceImpl extends BaseServiceImpl<Log, Long> implements LogService {

	@Resource(name = "logDaoImpl")
	private LogDao logDao;

	@Resource(name = "logDaoImpl")
	public void setBaseDao(LogDao logDao) {
		super.setBaseDao(logDao);
	}

	public void clear() {
		logDao.removeAll();
	}

}