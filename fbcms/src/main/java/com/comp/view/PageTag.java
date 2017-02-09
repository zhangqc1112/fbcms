package com.comp.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class PageTag implements TemplateDirectiveModel {

	private static final ThreadLocal<String> pageName = new ThreadLocal<>();
	private static final ThreadLocal<Boolean> pageEmbed = new ThreadLocal<>();
	private static final ThreadLocal<Map<String, String>> allParams = new ThreadLocal<>();

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Object name = params.get("name");
		if (name != null) {
			pageName.set(name.toString());
		}
		Object embed = params.get("embed");
		if (embed != null) {
			pageEmbed.set(Boolean.valueOf(embed.toString()));
		}
		Map<String, String> allParam = new HashMap<>();
		for (Object key : params.keySet()) {
			if (key != null) {
				Object value = params.get(key);
				allParam.put(key.toString(), value.toString());
			}
		}
		allParams.set(allParam);
	}

	public static String getPageName() {
		return pageName.get();
	}

	public static boolean isEmbed() {
		return pageEmbed.get() == Boolean.TRUE;
	}

	public static Map<String, String> getParams() {
		return allParams.get();
	}

	public static void clear() {
		pageName.remove();
		pageEmbed.remove();
		allParams.remove();
	}
}
