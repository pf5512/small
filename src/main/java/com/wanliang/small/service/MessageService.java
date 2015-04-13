
package com.wanliang.small.service;

import com.wanliang.small.Page;
import com.wanliang.small.Pageable;
import com.wanliang.small.entity.Member;
import com.wanliang.small.entity.Message;
import com.wanliang.small.Page;
import com.wanliang.small.Pageable;
import com.wanliang.small.entity.Member;
import com.wanliang.small.entity.Message;

/**
 * Service - 消息
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface MessageService extends BaseService<Message, Long> {

	/**
	 * 查找消息分页
	 * 
	 * @param member
	 *            会员,null表示管理员
	 * @param pageable
	 *            分页信息
	 * @return 消息分页
	 */
	Page<Message> findPage(Member member, Pageable pageable);

	/**
	 * 查找草稿分页
	 * 
	 * @param sender
	 *            发件人,null表示管理员
	 * @param pageable
	 *            分页信息
	 * @return 草稿分页
	 */
	Page<Message> findDraftPage(Member sender, Pageable pageable);

	/**
	 * 查找消息数量
	 * 
	 * @param member
	 *            会员，null表示管理员
	 * @param read
	 *            是否已读
	 * @return 消息数量，不包含草稿
	 */
	Long count(Member member, Boolean read);

	/**
	 * 删除消息
	 * 
	 * @param id
	 *            ID
	 * @param member
	 *            执行人,null表示管理员
	 */
	void delete(Long id, Member member);

}