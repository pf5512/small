
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.dao.ParameterDao;
import com.wanliang.small.service.ParameterService;
import com.wanliang.small.dao.ParameterDao;
import com.wanliang.small.entity.Parameter;
import com.wanliang.small.service.ParameterService;

import org.springframework.stereotype.Service;

/**
 * Service - 参数
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("parameterServiceImpl")
public class ParameterServiceImpl extends BaseServiceImpl<Parameter, Long> implements ParameterService {

	@Resource(name = "parameterDaoImpl")
	public void setBaseDao(ParameterDao parameterDao) {
		super.setBaseDao(parameterDao);
	}

}