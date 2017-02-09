package com.comp.admin.dao;

import com.comp.admin.entities.AccountGroupArea;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface AccountGroupAreaDao {

    @Insert("INSERT INTO account_group_area\n" +
            "       (\n" +
            "            group_no, area_type, area_id, area_mark, user_type\n" +
            "       )\n" +
            "       VALUES \n" +
            "       ( \n" +
            "           #{groupNo}, #{areaType}, #{areaId}, #{areaMark}, #{userType}\n" +
            "       )")
    public int insert(AccountGroupArea accountGroupArea);

    @Update("UPDATE account_group_area SET\n" +
            "       group_no = #{groupNo}, area_type = #{areaType}, area_id = #{areaId}, area_mark = #{areaMark}, user_type = #{userType}\n" +
            "       WHERE  id = #{id}")
    public int update(AccountGroupArea accountGroupArea);

    @Delete("DELETE FROM account_group_area WHERE  id = #{id}")
    public int deleteById(@Param("id") int id);

    @Select("SELECT * FROM account_group_area WHERE  id = #{id}")
    public AccountGroupArea getById(@Param("id") int id);

    @Select("SELECT  COUNT( DISTINCT(ID)) FROM account_group_area")
    public int count();

    @Select("SELECT * FROM account_group_area limit #{offset},#{limit}")
    public List<AccountGroupArea> pageQuery(@Param("offset") int offset, @Param("limit") int limit);
}