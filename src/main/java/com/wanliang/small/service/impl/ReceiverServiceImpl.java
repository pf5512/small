
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.entity.Receiver;
import com.wanliang.small.service.ReceiverService;
import com.wanliang.small.Page;
import com.wanliang.small.Pageable;
import com.wanliang.small.dao.ReceiverDao;
import com.wanliang.small.entity.Member;
import com.wanliang.small.entity.Receiver;
import com.wanliang.small.service.ReceiverService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 收货地址
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("receiverServiceImpl")
public class ReceiverServiceImpl extends BaseServiceImpl<Receiver, Long> implements ReceiverService {

	@Resource(name = "receiverDaoImpl")
	private ReceiverDao receiverDao;

	@Resource(name = "receiverDaoImpl")
	public void setBaseDao(ReceiverDao receiverDao) {
		super.setBaseDao(receiverDao);
	}

	@Transactional(readOnly = true)
	public Receiver findDefault(Member member) {
		return receiverDao.findDefault(member);
	}

	@Transactional(readOnly = true)
	public Page<Receiver> findPage(Member member, Pageable pageable) {
		return receiverDao.findPage(member, pageable);
	}

}