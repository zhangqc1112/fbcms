package com.comp.admin.dao;

import com.comp.admin.entities.AccountRole;
import com.comp.admin.vo.AccountRoleVo;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface AccountRoleDao {

    @Insert("INSERT INTO account_role\n" +
            "       (\n" +
            "            user_type, role_code, role_name, note, group_name\n" +
            "       )\n" +
            "       VALUES \n" +
            "       ( \n" +
            "           #{userType}, #{roleCode}, #{roleName}, #{note}, #{groupName}\n" +
            "       )")
    public int insert(AccountRole accountRole);

    @Update("UPDATE account_role SET\n" +
            "       user_type = #{userType}, role_code = #{roleCode}, role_name = #{roleName}, note = #{note}, group_name = #{groupName}\n" +
            "       WHERE  id = #{id}")
    public int update(AccountRole accountRole);

    @Delete("DELETE FROM account_role WHERE  id = #{id}")
    public int deleteById(@Param("id") int id);

    @Select("SELECT * FROM account_role WHERE  id = #{id}")
    public AccountRole getById(@Param("id") int id);

    @Select("<script>select " +
            "   count(id) " +
            "from " +
            "   account_role " +
            "<if test=\" userType != null \">user_type = #{userType}</if> " +
            "<if test=\" roleCode != null \">role_code = #{roleCode}</if> </script>" )
    public int count(AccountRoleVo queryVo);

    @Select("<script>select " +
            "   * " +
            "from " +
            "   account_role " +
            "<if test=\" userType != null \">user_type = #{userType}</if> " +
            "<if test=\" roleCode != null \">role_code = #{roleCode}</if> " +
            "limit #{offset},#{limit}</script>")
    public List<AccountRole> pageQuery(AccountRoleVo queryVo);

    @Select("<script>select " +
            "   * " +
            "from " +
            "   account_role </script>")
    public List<AccountRole> findAll();
}