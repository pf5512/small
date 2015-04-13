
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.dao.ParameterGroupDao;
import com.wanliang.small.entity.ParameterGroup;
import com.wanliang.small.service.ParameterGroupService;
import com.wanliang.small.dao.ParameterGroupDao;
import com.wanliang.small.entity.ParameterGroup;
import com.wanliang.small.service.ParameterGroupService;

import org.springframework.stereotype.Service;

/**
 * Service - 参数组
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("parameterGroupServiceImpl")
public class ParameterGroupServiceImpl extends BaseServiceImpl<ParameterGroup, Long> implements ParameterGroupService {

	@Resource(name = "parameterGroupDaoImpl")
	public void setBaseDao(ParameterGroupDao parameterGroupDao) {
		super.setBaseDao(parameterGroupDao);
	}

}