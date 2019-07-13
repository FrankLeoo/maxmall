package com.maxmall.provider.merchant.service.impl;

import com.google.common.base.Preconditions;
import com.maxmall.provider.merchant.service.UacFreeMarkerService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * The class Uac free marker service.
 *
 * @author maxmall.net@gmail.com
 */
@Service
public class UacFreeMarkerServiceImpl implements UacFreeMarkerService {

	@Resource
	private Configuration configuration;

	@Override
	public String getTemplate(Map<String, Object> map, String templateLocation) throws IOException, TemplateException {
		Preconditions.checkArgument(StringUtils.isNotEmpty(templateLocation), "模板不能为空");

		Template t = configuration.getTemplate(templateLocation, "UTF-8");
		return FreeMarkerTemplateUtils.processTemplateIntoString(t, map);

	}
}
