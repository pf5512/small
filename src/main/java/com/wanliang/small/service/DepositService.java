
package com.wanliang.small.service;

import com.wanliang.small.Page;
import com.wanliang.small.Pageable;
import com.wanliang.small.entity.Deposit;
import com.wanliang.small.entity.Member;

/**
 * Service - 预存款
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public interface DepositService extends BaseService<Deposit, Long> {

	/**
	 * 查找预存款分页
	 * 
	 * @param member
	 *            会员
	 * @param pageable
	 *            分页信息
	 * @return 预存款分页
	 */
	Page<Deposit> findPage(Member member, Pageable pageable);

}