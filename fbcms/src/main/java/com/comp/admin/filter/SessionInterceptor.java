package com.comp.admin.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fbcms.util.ConstantConfigUtil;

import java.net.URLEncoder;


public class SessionInterceptor implements HandlerInterceptor {

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object arg2) throws Exception {
		HttpSession sess = request.getSession();
		request.setAttribute("staticHost", ConstantConfigUtil.staticHost);
		StringBuffer source = new StringBuffer(request.getRequestURI());
		if(sess.getAttribute("menus") == null && !extUrl(source.toString())){
			String queryString = request.getQueryString();
			if (StringUtils.isNotBlank(queryString)) {
				source.append("?").append(queryString);
			}
			response.sendRedirect("/login?s="+ URLEncoder.encode(source.toString(), "UTF-8"));
		}
		return true;
	}

	private boolean extUrl(String url){
		if(url.startsWith("/login") || url.startsWith("/dashboard") || url.startsWith("/lib/")){
			return true;
		}
		return false;
	}


}
