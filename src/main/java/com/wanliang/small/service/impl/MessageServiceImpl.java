
package com.wanliang.small.service.impl;

import javax.annotation.Resource;

import com.wanliang.small.Page;
import com.wanliang.small.Pageable;
import com.wanliang.small.dao.MessageDao;
import com.wanliang.small.entity.Member;
import com.wanliang.small.entity.Message;
import com.wanliang.small.Page;
import com.wanliang.small.Pageable;
import com.wanliang.small.dao.MessageDao;
import com.wanliang.small.entity.Member;
import com.wanliang.small.entity.Message;
import com.wanliang.small.service.MessageService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 消息
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("messageServiceImpl")
public class MessageServiceImpl extends BaseServiceImpl<Message, Long> implements MessageService {

	@Resource(name = "messageDaoImpl")
	private MessageDao messageDao;

	@Resource(name = "messageDaoImpl")
	public void setBaseDao(MessageDao messageDao) {
		super.setBaseDao(messageDao);
	}

	@Transactional(readOnly = true)
	public Page<Message> findPage(Member member, Pageable pageable) {
		return messageDao.findPage(member, pageable);
	}

	@Transactional(readOnly = true)
	public Page<Message> findDraftPage(Member sender, Pageable pageable) {
		return messageDao.findDraftPage(sender, pageable);
	}

	@Transactional(readOnly = true)
	public Long count(Member member, Boolean read) {
		return messageDao.count(member, read);
	}

	public void delete(Long id, Member member) {
		messageDao.remove(id, member);
	}

}