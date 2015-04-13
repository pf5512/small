
package com.wanliang.small.dao.impl;

import com.wanliang.small.entity.Ad;
import com.wanliang.small.dao.AdDao;
import com.wanliang.small.entity.Ad;

import org.springframework.stereotype.Repository;

/**
 * Dao - 广告
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Repository("adDaoImpl")
public class AdDaoImpl extends BaseDaoImpl<Ad, Long> implements AdDao {

}