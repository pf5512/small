
package com.wanliang.small.template.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.wanliang.small.Filter;
import com.wanliang.small.entity.Member;
import com.wanliang.small.entity.Product;
import com.wanliang.small.entity.Review;
import com.wanliang.small.service.MemberService;
import com.wanliang.small.service.ProductService;
import com.wanliang.small.Filter;
import com.wanliang.small.Order;
import com.wanliang.small.entity.Member;
import com.wanliang.small.entity.Product;
import com.wanliang.small.entity.Review;
import com.wanliang.small.entity.Review.Type;
import com.wanliang.small.service.MemberService;
import com.wanliang.small.service.ProductService;
import com.wanliang.small.service.ReviewService;
import com.wanliang.small.util.FreemarkerUtils;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 评论
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Component("reviewListDirective")
public class ReviewListDirective extends BaseDirective {

	/** "会员ID"参数名称 */
	private static final String MEMBER_ID_PARAMETER_NAME = "memberId";

	/** "商品ID"参数名称 */
	private static final String PRODUCT_ID_PARAMETER_NAME = "productId";

	/** "类型"参数名称 */
	private static final String TYPE_PARAMETER_NAME = "type";

	/** 变量名称 */
	private static final String VARIABLE_NAME = "reviews";

	@Resource(name = "reviewServiceImpl")
	private ReviewService reviewService;
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "productServiceImpl")
	private ProductService productService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Long memberId = FreemarkerUtils.getParameter(MEMBER_ID_PARAMETER_NAME, Long.class, params);
		Long productId = FreemarkerUtils.getParameter(PRODUCT_ID_PARAMETER_NAME, Long.class, params);
		Review.Type type = FreemarkerUtils.getParameter(TYPE_PARAMETER_NAME, Review.Type.class, params);

		Member member = memberService.find(memberId);
		Product product = productService.find(productId);

		List<Review> reviews;
		if ((memberId != null && member == null) || (productId != null && product == null)) {
			reviews = new ArrayList<Review>();
		} else {
			boolean useCache = useCache(env, params);
			String cacheRegion = getCacheRegion(env, params);
			Integer count = getCount(params);
			List<Filter> filters = getFilters(params, Review.class);
			List<Order> orders = getOrders(params);
			if (useCache) {
				reviews = reviewService.findList(member, product, type, true, count, filters, orders, cacheRegion);
			} else {
				reviews = reviewService.findList(member, product, type, true, count, filters, orders);
			}
		}
		setLocalVariable(VARIABLE_NAME, reviews, env, body);
	}

}