
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.dao.SnDao;
import com.wanliang.small.service.SnService;
import com.wanliang.small.dao.SnDao;
import com.wanliang.small.entity.Sn.Type;
import com.wanliang.small.service.SnService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 序列号
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("snServiceImpl")
public class SnServiceImpl implements SnService {

	@Resource(name = "snDaoImpl")
	private SnDao snDao;

	@Transactional
	public String generate(Type type) {
		return snDao.generate(type);
	}

}