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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.comp.admin.biz.AccountModuleBo;
import com.comp.admin.biz.AccountRoleBo;
import com.comp.admin.biz.AccountRoleRefMenuBo;
import com.comp.admin.biz.AccountUserRefRoleBo;
import com.comp.admin.entities.AccountModule;
import com.comp.admin.entities.AccountRole;
import com.comp.admin.entities.AccountRoleRefMenu;
import com.comp.admin.vo.AccountModuleVo;
import com.comp.admin.vo.AccountRoleRefMenuVo;
import com.comp.admin.vo.AccountRoleVo;
import com.comp.menu.MenuTree;
import com.comp.menu.MenuTreeUtils;
import com.fbcms.util.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;


@Controller
@RequestMapping("/preferences/e")
public class RoleController extends BaseController{

	@Autowired
	private AccountModuleBo accountModuleBo;

	@Autowired
	private AccountRoleBo accountRoleBo;

	@Autowired
	private AccountUserRefRoleBo accountUserRefRoleBo;

	@Autowired
	private AccountRoleRefMenuBo accountRoleRefMenuBo;


	@RequestMapping("/role")
	public ModelAndView roleList() {
		return new ModelAndView("/admin/role_list");
	}

	@RequestMapping("/role/ajax/list")
	@ResponseBody
	public DataResult ajaxRoleList(AccountRoleVo queryVo) {
		DataResult res = new DataResult();
		res.put("listData", accountRoleBo.getRoleList(queryVo.getUserType()));
		res.put("listCount", accountRoleBo.count(queryVo));
		return res;
	}

	@RequestMapping("/role/ajax/save")
	@ResponseBody
	public DataResult ajaxRoleSave(AccountRoleVo inVo) {
		Integer id = inVo.getId();
		if (id == null) {
			inVo.setUserType("e");
			accountRoleBo.insert(inVo);
		} else {
			accountRoleBo.update(inVo);
		}
		return new DataResult();
	}

	@RequestMapping("/role/ajax/menuTree")
	@ResponseBody
	public DataResult ajaxMenuTree() {
		DataResult result = new DataResult();
        AccountModuleVo queryVo = new AccountModuleVo();
		queryVo.setUserType("e");
		List<AccountModule> list = accountModuleBo.pageQuery(queryVo);
		List<Map<String, Object>> resList = new ArrayList<>();
		for (AccountModule accountModule : list) {
			String menuTree = accountModule.getMenuTree();
			if (menuTree != null) {
				Map<String, Object> map = new HashMap<>();
				MenuTree tree = MenuTreeUtils.buildTree(menuTree);
				map.put("code", accountModule.getCode());
				map.put("name", accountModule.getName());
				map.put("tree", tree.getTreeRoot());
				resList.add(map);
			}
		}
		result.setData(resList);
		return result;
	}

	@RequestMapping("/role/ajax/getRoleMenus")
	@ResponseBody
	public DataResult getRoleMenus(AccountRoleVo queryVo) {
		DataResult result = new DataResult();
        AccountRoleRefMenuVo qVo = new AccountRoleRefMenuVo();
        qVo.setUserType("e");
        qVo.setRoleCode(queryVo.getRoleCode());
        List<AccountRoleRefMenu> list = accountRoleRefMenuBo.pageQuery(qVo);
		for (AccountRoleRefMenu map : list) {
			String moduleCode = map.getModuleCode();
			if (moduleCode != null) {
				List<AccountRoleRefMenu> menus = (List<AccountRoleRefMenu>) result.get(moduleCode);
				if (menus == null) {
					menus = new ArrayList<>();
					result.put(moduleCode, menus);
				}
				menus.add(map);
			}
		}
		return result;
	}

	@RequestMapping("/role/ajax/saveMenus")
	@ResponseBody
	public DataResult saveMenus(String roleCode, String menus) {
		DataResult result = new DataResult();
		List<AccountRoleRefMenu> list = JSON.parseArray(menus, AccountRoleRefMenu.class);
        accountRoleRefMenuBo.updateRoleRefMenus(roleCode, "e", list);
		return result;
	}

	@RequestMapping("/role/ajax/delRole")
	@ResponseBody
	public DataResult delRole(AccountRoleVo queryVo) {
        DataResult result = new DataResult();
        accountRoleBo.deleteById(queryVo.getId());
		return result;
	}
}
