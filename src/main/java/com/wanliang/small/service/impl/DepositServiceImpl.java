
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.Page;
import com.wanliang.small.Pageable;
import com.wanliang.small.dao.DepositDao;
import com.wanliang.small.entity.Deposit;
import com.wanliang.small.entity.Member;
import com.wanliang.small.service.DepositService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 预存款
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("depositServiceImpl")
public class DepositServiceImpl extends BaseServiceImpl<Deposit, Long> implements DepositService {

	@Resource(name = "depositDaoImpl")
	private DepositDao depositDao;

	@Resource(name = "depositDaoImpl")
	public void setBaseDao(DepositDao depositDao) {
		super.setBaseDao(depositDao);
	}

	@Transactional(readOnly = true)
	public Page<Deposit> findPage(Member member, Pageable pageable) {
		return depositDao.findPage(member, pageable);
	}

}