
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.dao.AttributeDao;
import com.wanliang.small.entity.Attribute;
import com.wanliang.small.service.AttributeService;

import org.springframework.stereotype.Service;

/**
 * Service - 属性
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("attributeServiceImpl")
public class AttributeServiceImpl extends BaseServiceImpl<Attribute, Long> implements AttributeService {

	@Resource(name = "attributeDaoImpl")
	public void setBaseDao(AttributeDao attributeDao) {
		super.setBaseDao(attributeDao);
	}

}