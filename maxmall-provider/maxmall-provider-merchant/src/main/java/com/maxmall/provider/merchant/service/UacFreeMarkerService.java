
package com.maxmall.provider.merchant.service;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;


/**
 * The interface Uac free marker service.
 *
 * @author maxmall.net@gmail.com
 */
public interface UacFreeMarkerService {

	/**
	 * Gets template.
	 *
	 * @param map              the map
	 * @param templateLocation the template location
	 *
	 * @return the template
	 *
	 * @throws IOException       the io exception
	 * @throws TemplateException the template exception
	 */
	String getTemplate(Map<String, Object> map, String templateLocation) throws IOException, TemplateException;
}
