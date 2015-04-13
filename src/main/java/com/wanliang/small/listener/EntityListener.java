
package com.wanliang.small.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.wanliang.small.entity.BaseEntity;

/**
 * Listener - 创建日期、修改日期处理
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
public class EntityListener {

	/**
	 * 保存前处理
	 * 
	 * @param entity
	 *            基类
	 */
	@PrePersist
	public void prePersist(BaseEntity entity) {
		entity.setCreateDate(new Date());
		entity.setModifyDate(new Date());
	}

	/**
	 * 更新前处理
	 * 
	 * @param entity
	 *            基类
	 */
	@PreUpdate
	public void preUpdate(BaseEntity entity) {
		entity.setModifyDate(new Date());
	}

}