
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.dao.RefundsDao;
import com.wanliang.small.entity.Refunds;
import com.wanliang.small.service.RefundsService;

import org.springframework.stereotype.Service;

/**
 * Service - 退款单
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("refundsServiceImpl")
public class RefundsServiceImpl extends BaseServiceImpl<Refunds, Long> implements RefundsService {

	@Resource(name = "refundsDaoImpl")
	public void setBaseDao(RefundsDao refundsDao) {
		super.setBaseDao(refundsDao);
	}

}