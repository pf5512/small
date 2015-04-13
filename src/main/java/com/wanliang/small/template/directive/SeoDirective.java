
package com.wanliang.small.template.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import com.wanliang.small.entity.Seo;
import com.wanliang.small.service.SeoService;
import com.wanliang.small.util.FreemarkerUtils;
import com.wanliang.small.entity.Seo;
import com.wanliang.small.entity.Seo.Type;
import com.wanliang.small.service.SeoService;
import com.wanliang.small.util.FreemarkerUtils;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - SEO设置
 * 
 * @author wan_liang@126.com Team
 * @version 3.0
 */
@Component("seoDirective")
public class SeoDirective extends BaseDirective {

	/** "类型"参数名称 */
	private static final String TYPE_PARAMETER_NAME = "type";

	/** 变量名称 */
	private static final String VARIABLE_NAME = "seo";

	@Resource(name = "seoServiceImpl")
	private SeoService seoService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Seo.Type type = FreemarkerUtils.getParameter(TYPE_PARAMETER_NAME, Seo.Type.class, params);

		Seo seo;
		boolean useCache = useCache(env, params);
		String cacheRegion = getCacheRegion(env, params);
		if (useCache) {
			seo = seoService.find(type, cacheRegion);
		} else {
			seo = seoService.find(type);
		}
		setLocalVariable(VARIABLE_NAME, seo, env, body);
	}

}