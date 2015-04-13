
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.dao.SpecificationDao;
import com.wanliang.small.service.SpecificationService;
import com.wanliang.small.dao.SpecificationDao;
import com.wanliang.small.entity.Specification;
import com.wanliang.small.service.SpecificationService;

import org.springframework.stereotype.Service;

/**
 * Service - 规格
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("specificationServiceImpl")
public class SpecificationServiceImpl extends BaseServiceImpl<Specification, Long> implements SpecificationService {

	@Resource(name = "specificationDaoImpl")
	public void setBaseDao(SpecificationDao specificationDao) {
		super.setBaseDao(specificationDao);
	}

}