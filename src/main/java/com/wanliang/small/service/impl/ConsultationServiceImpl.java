
package com.wanliang.small.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.wanliang.small.Filter;
import com.wanliang.small.Filter;
import com.wanliang.small.Order;
import com.wanliang.small.Page;
import com.wanliang.small.Pageable;
import com.wanliang.small.dao.ConsultationDao;
import com.wanliang.small.entity.Consultation;
import com.wanliang.small.entity.Member;
import com.wanliang.small.entity.Product;
import com.wanliang.small.service.ConsultationService;
import com.wanliang.small.service.StaticService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 咨询
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Service("consultationServiceImpl")
public class ConsultationServiceImpl extends BaseServiceImpl<Consultation, Long> implements ConsultationService {

	@Resource(name = "consultationDaoImpl")
	private ConsultationDao consultationDao;
	@Resource(name = "staticServiceImpl")
	private StaticService staticService;

	@Resource(name = "consultationDaoImpl")
	public void setBaseDao(ConsultationDao consultationDao) {
		super.setBaseDao(consultationDao);
	}

	@Transactional(readOnly = true)
	public List<Consultation> findList(Member member, Product product, Boolean isShow, Integer count, List<Filter> filters, List<Order> orders) {
		return consultationDao.findList(member, product, isShow, count, filters, orders);
	}

	@Transactional(readOnly = true)
	@Cacheable("consultation")
	public List<Consultation> findList(Member member, Product product, Boolean isShow, Integer count, List<Filter> filters, List<Order> orders, String cacheRegion) {
		return consultationDao.findList(member, product, isShow, count, filters, orders);
	}

	@Transactional(readOnly = true)
	public Page<Consultation> findPage(Member member, Product product, Boolean isShow, Pageable pageable) {
		return consultationDao.findPage(member, product, isShow, pageable);
	}

	@Transactional(readOnly = true)
	public Long count(Member member, Product product, Boolean isShow) {
		return consultationDao.count(member, product, isShow);
	}

	@CacheEvict(value = { "product", "productCategory", "review", "consultation" }, allEntries = true)
	public void reply(Consultation consultation, Consultation replyConsultation) {
		if (consultation == null || replyConsultation == null) {
			return;
		}
		consultation.setIsShow(true);
		consultationDao.merge(consultation);

		replyConsultation.setIsShow(true);
		replyConsultation.setProduct(consultation.getProduct());
		replyConsultation.setForConsultation(consultation);
		consultationDao.persist(replyConsultation);

		Product product = consultation.getProduct();
		if (product != null) {
			consultationDao.flush();
			staticService.build(product);
		}
	}

	@Override
	@Transactional
	@CacheEvict(value = { "product", "productCategory", "review", "consultation" }, allEntries = true)
	public void save(Consultation consultation) {
		super.save(consultation);
		Product product = consultation.getProduct();
		if (product != null) {
			consultationDao.flush();
			staticService.build(product);
		}
	}

	@Override
	@Transactional
	@CacheEvict(value = { "product", "productCategory", "review", "consultation" }, allEntries = true)
	public Consultation update(Consultation consultation) {
		Consultation pConsultation = super.update(consultation);
		Product product = pConsultation.getProduct();
		if (product != null) {
			consultationDao.flush();
			staticService.build(product);
		}
		return pConsultation;
	}

	@Override
	@Transactional
	@CacheEvict(value = { "product", "productCategory", "review", "consultation" }, allEntries = true)
	public Consultation update(Consultation consultation, String... ignoreProperties) {
		return super.update(consultation, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = { "product", "productCategory", "review", "consultation" }, allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = { "product", "productCategory", "review", "consultation" }, allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = { "product", "productCategory", "review", "consultation" }, allEntries = true)
	public void delete(Consultation consultation) {
		if (consultation != null) {
			super.delete(consultation);
			Product product = consultation.getProduct();
			if (product != null) {
				consultationDao.flush();
				staticService.build(product);
			}
		}
	}

}