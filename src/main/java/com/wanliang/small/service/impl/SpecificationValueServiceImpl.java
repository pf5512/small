
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.dao.SpecificationValueDao;
import com.wanliang.small.entity.SpecificationValue;
import com.wanliang.small.dao.SpecificationValueDao;
import com.wanliang.small.entity.SpecificationValue;
import com.wanliang.small.service.SpecificationValueService;

import org.springframework.stereotype.Service;

/**
 * Service - 规格值
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("specificationValueServiceImpl")
public class SpecificationValueServiceImpl extends BaseServiceImpl<SpecificationValue, Long> implements SpecificationValueService {

	@Resource(name = "specificationValueDaoImpl")
	public void setBaseDao(SpecificationValueDao specificationValueDao) {
		super.setBaseDao(specificationValueDao);
	}

}