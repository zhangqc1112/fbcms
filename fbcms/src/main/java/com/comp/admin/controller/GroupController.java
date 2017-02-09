
package com.comp.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.comp.admin.biz.AccountGroupBo;
import com.comp.admin.entities.AccountGroup;
import com.comp.admin.vo.AccountGroupVo;
import com.fbcms.util.DataResult;
import com.fbcms.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/preferences/e")
public class GroupController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

	@Autowired
	private AccountGroupBo accountGroupBo;

	@RequestMapping("/group")
	public ModelAndView groupList(HttpServletRequest request) {
		return new ModelAndView("/admin/group_list").addObject("userType","e");
	}

	@RequestMapping("/group/ajax/list")
	@ResponseBody
	public DataResult ajaxGroupList(AccountGroupVo queryVo) {
		DataResult res = new DataResult();
		res.put("listData", accountGroupBo.pageQuery(queryVo));
		res.put("listCount", accountGroupBo.count(queryVo));
		return res;
	}


	@RequestMapping("/group/ajax/save")
	@ResponseBody
	public DataResult ajaxSave(AccountGroup inVo) {
		DataResult res = new DataResult();
		if(StringUtil.isEmptyOrNull(inVo.getId())){
			accountGroupBo.insert(inVo);
		}else{
			accountGroupBo.update(inVo);
		}
		return res;
	}

	@RequestMapping("/group/ajax/del")
	@ResponseBody
	public DataResult del(AccountGroup queryVo) {
		DataResult res = new DataResult();
		accountGroupBo.deleteById(queryVo.getId());
		return res;
	}

	@RequestMapping("/group/ajax/all")
	@ResponseBody
	public Object ajaxlist(HttpServletRequest request) {
		AccountGroupVo queryVo = new AccountGroupVo();
		queryVo.setOffset(0);
		queryVo.setLimit(Integer.MAX_VALUE);
		List<AccountGroup> list = accountGroupBo.pageQuery(queryVo);
		return list;
	}
}
