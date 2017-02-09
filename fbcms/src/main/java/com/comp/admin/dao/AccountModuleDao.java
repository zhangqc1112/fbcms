package com.comp.admin.dao;

import com.comp.admin.entities.AccountModule;
import com.comp.admin.vo.AccountModuleVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AccountModuleDao {
    @SuppressWarnings("rawtypes")
    @Insert("INSERT INTO account_module\n" +
            "       (\n" +
            "            user_type, code, name, logo_url, index_url, is_hidden, note, menu_tree, create_time, update_time\n" +
            "       )\n" +
            "       VALUES \n" +
            "       ( \n" +
            "           #{userType}, #{code}, #{name}, #{logoUrl}, #{indexUrl}, #{isHidden}, #{note}, #{menuTree}, #{createTime}, #{updateTime}\n" +
            "       )")
    public int insert(AccountModule accountModule);

    @Update("UPDATE account_module SET\n" +
            "       user_type = #{userType}, code = #{code}, name = #{name}, logo_url = #{logoUrl}, index_url = #{indexUrl}, is_hidden = #{isHidden}, note = #{note}, menu_tree = #{menuTree}, create_time = #{createTime}, update_time = #{updateTime}\n" +
            "       WHERE  id = #{id}")
    public int update(AccountModule accountModule);

    @Delete("DELETE FROM account_module    WHERE  id = #{id}")
    public int deleteById(@Param("id") int id);

    @Select("SELECT * FROM account_module  WHERE  id = #{id}")
    public AccountModule getById(@Param("id") int id);

    @Select("SELECT * FROM account_module  WHERE  code = #{code} limit 1")
    public AccountModule getByCode(@Param("code") String code);

    @Select("<script>select count(id) from account_module where user_type = #{userType}</script>")
    public int count(AccountModuleVo queryVo);

    @Select("<script>select * from account_module where user_type = #{userType}</script>")
    public List<AccountModule> pageQuery(AccountModuleVo queryVo);
}