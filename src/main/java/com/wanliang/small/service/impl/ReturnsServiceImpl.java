
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.entity.Returns;
import com.wanliang.small.dao.ReturnsDao;
import com.wanliang.small.entity.Returns;
import com.wanliang.small.service.ReturnsService;

import org.springframework.stereotype.Service;

/**
 * Service - 退货单
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("returnsServiceImpl")
public class ReturnsServiceImpl extends BaseServiceImpl<Returns, Long> implements ReturnsService {

	@Resource(name = "returnsDaoImpl")
	public void setBaseDao(ReturnsDao returnsDao) {
		super.setBaseDao(returnsDao);
	}

}