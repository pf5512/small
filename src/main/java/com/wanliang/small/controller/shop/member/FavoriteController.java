
package com.wanliang.small.controller.shop.member;

import javax.annotation.Resource;

import com.wanliang.small.Message;
import com.wanliang.small.Pageable;
import com.wanliang.small.controller.shop.BaseController;
import com.wanliang.small.entity.Member;
import com.wanliang.small.entity.Product;
import com.wanliang.small.service.MemberService;
import com.wanliang.small.service.ProductService;
import com.wanliang.small.Message;
import com.wanliang.small.Pageable;
import com.wanliang.small.controller.shop.BaseController;
import com.wanliang.small.entity.Member;
import com.wanliang.small.entity.Product;
import com.wanliang.small.service.MemberService;
import com.wanliang.small.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller - 会员中心 - 商品收藏
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Controller("shopMemberFavoriteController")
@RequestMapping("/member/favorite")
public class FavoriteController extends BaseController {

	/** 每页记录数 */
	private static final int PAGE_SIZE = 10;

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "productServiceImpl")
	private ProductService productService;

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
    Message add(Long id) {
		Product product = productService.find(id);
		if (product == null) {
			return ERROR_MESSAGE;
		}
		Member member = memberService.getCurrent();
		if (member.getFavoriteProducts().contains(product)) {
			return Message.warn("shop.member.favorite.exist");
		}
		if (Member.MAX_FAVORITE_COUNT != null && member.getFavoriteProducts().size() >= Member.MAX_FAVORITE_COUNT) {
			return Message.warn("shop.member.favorite.addCountNotAllowed", Member.MAX_FAVORITE_COUNT);
		}
		member.getFavoriteProducts().add(product);
		memberService.update(member);
		return Message.success("shop.member.favorite.success");
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Integer pageNumber, ModelMap model) {
		Member member = memberService.getCurrent();
		Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
		model.addAttribute("page", productService.findPage(member, pageable));
		return "shop/member/favorite/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long id) {
		Product product = productService.find(id);
		if (product == null) {
			return ERROR_MESSAGE;
		}
		Member member = memberService.getCurrent();
		if (!member.getFavoriteProducts().contains(product)) {
			return ERROR_MESSAGE;
		}
		member.getFavoriteProducts().remove(product);
		memberService.update(member);
		return SUCCESS_MESSAGE;
	}

}