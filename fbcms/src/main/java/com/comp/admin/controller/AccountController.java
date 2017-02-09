/*
 * COPYRIGHT (C) 2015-2016,LUOSHUAI. ALL RIGHTS RESERVED.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files
 * (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, 
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions: 
 *
 *   a).The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software!
 *   b).Any individual or entity would be granted by LUOSHUAI before using this Software!
 *  
 * Please contact through email luoshuai@live.com if you need additional informations OR have any questions.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * Author: Luoshuai 
 * Revision: 1.0
 * 
 *  
 */
package com.comp.admin.controller;

import com.comp.admin.biz.AccountRoleBo;
import com.comp.admin.biz.AccountRoleRefMenuBo;
import com.comp.admin.biz.AccountUserBo;
import com.comp.admin.entities.AccountRole;
import com.comp.admin.entities.AccountUser;
import com.comp.admin.enums.StatusEnum;
import com.comp.admin.vo.AccountUserVo;
import com.fbcms.util.DataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/preferences/e")
public class AccountController extends BaseController{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private AccountUserBo accountUserBo;

	@Autowired
	private AccountRoleBo accountRoleBo;

	@Autowired
	private AccountRoleRefMenuBo accountRoleRefMenuBo;


	@RequestMapping("/account")
	public ModelAndView preference_account(HttpServletRequest request) {
		List<AccountRole> roleList = accountRoleBo.findAll();
		return new ModelAndView("/admin/user_list").addObject("roleList",roleList);
	}

	@RequestMapping("/account/ajax/users")
	@ResponseBody
	public DataResult ajax_users(AccountUserVo queryVo) {
		DataResult res = new DataResult();
		res.put("listData",accountUserBo.pageQuery(queryVo));
		res.put("listCount",accountUserBo.count(queryVo));
		return res;
	}

	@RequestMapping("/account/ajax/users/detail")
	@ResponseBody
	public DataResult ajax_detail(HttpServletRequest request, String userId) {
		DataResult res = new DataResult();
		AccountUser accountUser = accountUserBo.getByUserId(userId);
		List<String> roleCodes = accountRoleRefMenuBo.findRoleCodesByUserId(userId);
		if (roleCodes != null && roleCodes.size() != 0) {
			accountUser.setRoleCodes(roleCodes);
		}
		res.setData(accountUser);
		return res;
	}

	@RequestMapping("/account/ajax/save")
	@ResponseBody
	public DataResult ajax_save(AccountUserVo inVo) {
		DataResult res = new DataResult();
		accountUserBo.save(inVo);
		return res;
	}

	@RequestMapping("/account/ajax/status")
	@ResponseBody
	public DataResult ajax_status(String userId, Integer status) {
		DataResult res = new DataResult();
		accountUserBo.updateStatus(userId,status);
		return res;
	}

	@RequestMapping("/account/ajax/delete")
	@ResponseBody
	public DataResult ajax_delete(String userId) {
		DataResult res = new DataResult();
		accountUserBo.updateStatus(userId, StatusEnum.STATUS_DELETE.getCode());
		return res;
	}

	@RequestMapping("/account/ajax/resetAct")
	@ResponseBody
	public DataResult ajax_add(String userId, String loginName) {
		DataResult res = new DataResult();
		accountUserBo.saveAccount(userId,loginName);
		return res;
	}

	@RequestMapping(value="/account/ajax/resetPwd" ,method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public DataResult ajax_resetPwd(String userId, String password) {
		DataResult res = new DataResult();
		accountUserBo.resetPwd(userId,password);
		return res;
	}

}
