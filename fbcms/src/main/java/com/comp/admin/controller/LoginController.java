package com.comp.admin.controller;

import java.awt.image.BufferedImage;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.fastjson.JSONObject;
import com.comp.admin.biz.AccountLoginInfoBo;
import com.comp.admin.biz.AccountModuleBo;
import com.comp.admin.biz.AccountUserBo;
import com.comp.admin.entities.AccountLoginInfo;
import com.comp.admin.entities.AccountModule;
import com.comp.admin.utils.BaseException;
import com.comp.admin.utils.ConstantUtil;
import com.comp.admin.utils.DefaultKaptcha;
import com.fbcms.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping(value = "/")
public class LoginController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private AccountModuleBo accountModuleBo;
	@Autowired
	private AccountLoginInfoBo accountLoginInfoBo;
	@Autowired
	private AccountUserBo accountUserBo;

	public LoginController() {
	}

	@RequestMapping("login")
	public ModelAndView portal(HttpServletRequest request, String s) {
		ModelAndView mv = new ModelAndView("login");
		logger.info("jump url->{}",s);

		Object obj = request.getSession().getAttribute(ConstantUtil.SESS_USER);
		if(obj != null){
			return new ModelAndView("index");
		}else{
			return mv.addObject("s",s);
		}
	}


	@RequestMapping("login/getVerifyCode")
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image
		//String capText = captchaProducer.createText();
		// store the text in the session
		//request.getSession().setAttribute(KAPTCHA_SESSION_KEY, capText);
		// create the image with the text
		DefaultKaptcha captchaProducer = new DefaultKaptcha();
		BufferedImage bi = captchaProducer.createImage(request,response);
		ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}


	@RequestMapping(value = "login/dologin", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public DataResult doLogin(@RequestParam(required = true) String loginName,
							  @RequestParam(required = true) String rc,
							  @RequestParam(required = true) String password,
							  String source,
							  HttpServletRequest request,
							  HttpServletResponse response) {
		logger.info("{} login at time {}", loginName, DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
		DataResult result = new DataResult();
		AccountLoginInfo info = accountLoginInfoBo.login(loginName,password);
		AccountModule module = accountModuleBo.getByCode(info.getSalt());
		JSONObject menuTree = JSONObject.parseObject(module.getMenuTree());
		request.getSession().setAttribute(ConstantUtil.SESS_MODULE,module);
		request.getSession().setAttribute(ConstantUtil.SESS_MENU,menuTree);
		request.getSession().setAttribute(ConstantUtil.SESS_USER,info);
		request.getSession().setAttribute(ConstantUtil.LOGIN_NAME,info.getLoginName());
		if(StringUtil.isEmptyOrNull(source)){
			if(isAdmin(info)){
				source = "/preferences/e/group";
			}else{
				source = "/index";
			}
		}else{
			if(!isAdmin(info)){
				if(source.startsWith("/preferences/e")){
					source = "/index";
				}
			}
		}
		result.put("url", source);
		return result;
	}


	@RequestMapping(value = "login/loginOut")
	public ModelAndView doLoginOut(HttpServletRequest request) {
		destroySesson(request);
		return new ModelAndView("login");
	}

	@RequestMapping(value = "changePwd", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public DataResult changePwd(@RequestParam(required = true) String pwd,
								@RequestParam(required = true) String npwd,HttpServletRequest request,HttpServletResponse response) {
		DataResult result = new DataResult();
		AccountLoginInfo info = (AccountLoginInfo)request.getSession().getAttribute(ConstantUtil.SESS_USER);
		if(!info.getPassword().equals(MD5Encrypt.md5(pwd))){
			throw new BaseException("旧密码错误");
		}
		accountUserBo.resetPwd(info.getUserId(),npwd);
		destroySesson(request);
		return result;
	}

	private void destroySesson(HttpServletRequest request){
		if(request.getSession().getAttribute(ConstantUtil.SESS_USER) != null){
			AccountLoginInfo info = (AccountLoginInfo)request.getSession().getAttribute(ConstantUtil.SESS_USER);
			logger.info("{} login out time {}", info.getLoginName(), DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
			request.getSession().removeAttribute(ConstantUtil.SESS_MODULE);
			request.getSession().removeAttribute(ConstantUtil.SESS_MENU);
			request.getSession().removeAttribute(ConstantUtil.SESS_USER);
			request.getSession().invalidate();
		}
	}

	private boolean isAdmin(AccountLoginInfo info){
		if(info.getSalt().equals("admin")){
			return true;
		}else{
			return false;
		}
	}

}
