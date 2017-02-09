package com.comp.admin.dao;

import com.comp.admin.entities.AccountModule;
import com.comp.admin.entities.AccountRoleRefMenu;
import com.comp.admin.vo.AccountRoleRefMenuVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AccountRoleRefMenuDao {

    @Insert("INSERT INTO account_role_ref_menu \n" +
            "       (\n" +
            "            user_type, role_code, module_code, menu_code, sub_funcs\n" +
            "       )\n" +
            "       VALUES \n" +
            "       ( \n" +
            "           #{userType}, #{roleCode}, #{moduleCode}, #{menuCode}, #{subFuncs}\n" +
            "       )")
    public int insert(AccountRoleRefMenu accountRoleRefMenu);

    @Update("UPDATE account_role_ref_menu SET\n" +
            "       user_type = #{userType}, role_code = #{roleCode}, module_code = #{moduleCode}, menu_code = #{menuCode}, sub_funcs = #{subFuncs}\n" +
            "       WHERE  id = #{id}")
    public int update(AccountRoleRefMenu accountRoleRefMenu);

    @Delete("DELETE FROM account_role_ref_menu WHERE  id = #{id}")
    public int deleteById(@Param("id") int id);

    @Delete("DELETE FROM account_role_ref_menu WHERE  role_code = #{roleCode}")
    public int deleteByRoleCode(@Param("roleCode") String roleCode);

    @Select("SELECT * FROM account_role_ref_menu WHERE  id = #{id}")
    public AccountRoleRefMenu getById(@Param("id") int id);

    @Select("<script>select count(id) from account_role_ref_menu where 1=1 <if test='userType != null'>and user_type=#{userType}</if><if test='roleCode != null'> and role_code =#{roleCode}</if></script>")
    public int count(AccountRoleRefMenuVo queryVo);

    @Select("<script>select * from account_role_ref_menu where 1=1 <if test='userType != null'>and user_type=#{userType}</if><if test='roleCode != null'> and role_code =#{roleCode}</if></script>")
    public List<AccountRoleRefMenu> pageQuery(AccountRoleRefMenuVo queryVo);

    @Select("select * from account_role_ref_menu  WHERE  role_code = #{roleCode} and menu_code = #{menuCode}")
    public List<AccountRoleRefMenu> getListByRoleAndMenuCode(AccountRoleRefMenuVo queryVo);

    @Select("select * from account_role_ref_menu  WHERE  role_code = #{roleCode}")
    public List<AccountRoleRefMenu> getListByCode(@Param("roleCode") String roleCode);

    @Select("select role_code from account_user_ref_role  WHERE  user_id= #{userId}")
    public List<String> findRoleCodesByUserId(@Param("userId") String userId);

}