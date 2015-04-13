
package com.wanliang.small.controller.shop.member;

import javax.annotation.Resource;

import com.wanliang.small.Pageable;
import com.wanliang.small.controller.shop.BaseController;
import com.wanliang.small.entity.Member;
import com.wanliang.small.service.MemberService;
import com.wanliang.small.Pageable;
import com.wanliang.small.controller.shop.BaseController;
import com.wanliang.small.entity.Member;
import com.wanliang.small.service.MemberService;
import com.wanliang.small.service.ReviewService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller - 会员中心 - 评论
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Controller("shopMemberReviewController")
@RequestMapping("/member/review")
public class ReviewController extends BaseController {

	/** 每页记录数 */
	private static final int PAGE_SIZE = 10;

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "reviewServiceImpl")
	private ReviewService reviewService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Integer pageNumber, ModelMap model) {
		Member member = memberService.getCurrent();
		Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
		model.addAttribute("page", reviewService.findPage(member, null, null, null, pageable));
		return "shop/member/review/list";
	}

}