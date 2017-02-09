package com.comp.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MenuExtendDao {

	@SuppressWarnings("rawtypes")
	@Select("SELECT role_code,count(distinct menu_code) as menuNum from account_role_ref_menu where user_type=#{userType} group by role_code")
	public List<Map> queryRoleMenuNum(@Param("userType") String userType);

	@SuppressWarnings("rawtypes")
	@Select("SELECT role_code,count(distinct user_id) as userNum from account_user_ref_role where user_type=#{userType} group by role_code")
	public List<Map> queryRoleUserNum(@Param("userType") String userType);



}
