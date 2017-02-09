package com.comp.admin.controller;

import java.util.List;

import com.comp.admin.biz.AccountModuleBo;
import com.comp.admin.biz.AccountRoleRefMenuBo;
import com.comp.admin.entities.AccountModule;
import com.comp.admin.entities.AccountRoleRefMenu;
import com.comp.admin.vo.AccountModuleVo;
import com.comp.admin.vo.AccountRoleRefMenuVo;
import com.comp.menu.MenuTree;
import com.comp.menu.MenuTreeUtils;
import com.fbcms.util.DataResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/preferences/e")
public class ModuleController extends BaseController{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountModuleBo accountModuleBo;

	@Autowired
	private AccountRoleRefMenuBo accountRoleRefMenuBo;

	@RequestMapping("/module")
	public ModelAndView moduleList() {
		return new ModelAndView("/admin/module_list");
	}

	@RequestMapping("/module/ajax/list")
	@ResponseBody
	public DataResult ajaxModuleList(AccountModuleVo queryVO) {
		DataResult res = new DataResult();
		queryVO.setUserType("e");
		res.put("listData",accountModuleBo.pageQuery(queryVO));
		res.put("listCount",accountModuleBo.count(queryVO));
		return res;
	}

	@RequestMapping("/module/menus")
	public ModelAndView editMenus(@RequestParam(required = true) String module, String edit) {
		AccountModule moduleVo = accountModuleBo.getByCode(module);
		String menuStr = moduleVo.getMenuTree();
		MenuTree tree = MenuTreeUtils.buildTree(menuStr);
		ModelAndView mv = new ModelAndView("/admin/menu_edit").addObject("module", module)
				.addObject("editable", StringUtils.equals(edit, "1"));
		if (tree != null) {
			mv.addObject("root", tree.getTreeRoot());
		}
		return mv;
	}

	@RequestMapping("/module/ajax/save")
	@ResponseBody
	public DataResult saveModule(AccountModuleVo inVo) {
		DataResult res = new DataResult();
		if (inVo.getId() == null) {
			accountModuleBo.insert(inVo);
		} else {
			accountModuleBo.update(inVo);
		}
		return res;
	}

	@RequestMapping("/module/menus/ajax/save")
	@ResponseBody
	public DataResult saveMenus(@RequestParam(required = true) String module,
								@RequestParam(required = true) String menuTree) {
		accountModuleBo.updateTreeByCode(module,menuTree);
		return new DataResult();
	}


	@RequestMapping("/module/ajax/delModule")
	@ResponseBody
	public DataResult delModule(@RequestParam(required = true) String module) {


		DataResult res = new DataResult();
		AccountModule accountModule = accountModuleBo.getByCode(module);
		if(accountModule == null){
			res.setMessage("对象不存在");
		}else{
			int count = accountModuleBo.deleteById(accountModule.getId());
			res.put("used", true).put("count", count);
		}
		return res;

	}

	@RequestMapping("/module/menus/ajax/checkMenu")
	@ResponseBody
	public DataResult checkMenuUsed(
			@RequestParam(required = true) String module,
			@RequestParam(required = true) String menuId) {

		AccountRoleRefMenuVo queryVo = new AccountRoleRefMenuVo();
		queryVo.setRoleCode(module);
		queryVo.setMenuCode(menuId);
		List<AccountRoleRefMenu> lists = accountRoleRefMenuBo.getListByCode(queryVo);
		if (lists.size() > 0) {
			return new DataResult().put("used", true).put("roleCodes", lists);
		}
		return new DataResult().put("used", false);
	}
}
