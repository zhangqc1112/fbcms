package com.comp.admin.dao;

import com.comp.admin.entities.AccountRoleRefMenu;
import com.comp.admin.entities.AccountUserRefRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AccountUserRefRoleDao {

    @Insert("INSERT INTO account_user_ref_role\n" +
            "       (\n" +
            "            user_type, user_id, role_code\n" +
            "       )\n" +
            "       VALUES \n" +
            "       ( \n" +
            "           #{userType}, #{userId}, #{roleCode}\n" +
            "       )")
    public int insert(AccountUserRefRole accountUserRefRole);

    @Update("UPDATE account_user_ref_role SET\n" +
            "       user_type = #{userType}, user_id = #{userId}, role_code = #{roleCode}\n" +
            "       WHERE  id = #{id}")
    public int update(AccountUserRefRole accountUserRefRole);

    @Delete("DELETE FROM account_user_ref_role WHERE  id = #{id}")
    public int deleteById(@Param("id") int id);

    @Delete("DELETE FROM account_user_ref_role WHERE  user_id = #{userId}")
    public int deleteByUserId(@Param("userId") String userId);

    @Select("SELECT * FROM account_user_ref_role WHERE  id = #{id}")
    public AccountUserRefRole getById(@Param("id") int id);

    @Select("select * from account_user_ref_role  WHERE  role_code = #{roleCode}")
    public List<AccountRoleRefMenu> getListByCode(@Param("roleCode") String roleCode);

    @Select("")
    public int count();

    @Select("")
    public List<AccountUserRefRole> pageQuery(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);
}