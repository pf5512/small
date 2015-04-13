
package com.wanliang.small.controller.admin;

import javax.annotation.Resource;

import com.wanliang.small.entity.Seo;
import com.wanliang.small.Pageable;
import com.wanliang.small.entity.Seo;
import com.wanliang.small.service.SeoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - SEO设置
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Controller("adminSeoController")
@RequestMapping("/admin/seo")
public class SeoController extends BaseController {

	@Resource(name = "seoServiceImpl")
	private SeoService seoService;

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("seo", seoService.find(id));
		return "/admin/seo/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Seo seo, RedirectAttributes redirectAttributes) {
		if (!isValid(seo)) {
			return ERROR_VIEW;
		}
		seoService.update(seo, "type");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", seoService.findPage(pageable));
		return "/admin/seo/list";
	}

}