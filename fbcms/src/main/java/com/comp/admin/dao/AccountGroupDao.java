package com.comp.admin.dao;

import com.comp.admin.entities.AccountGroup;
import com.comp.admin.vo.AccountGroupVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AccountGroupDao {

    @Insert("INSERT INTO account_group\n" +
            "       (\n" +
            "            user_type, group_no, group_name, level, p_id, note, leader, contact_phone, email, create_time, update_time\n" +
            "       )\n" +
            "       VALUES \n" +
            "       ( \n" +
            "           #{userType}, #{groupNo}, #{groupName}, #{level}, #{pId}, #{note}, #{leader}, #{contactPhone}, #{email}, #{createTime}, #{updateTime}\n" +
            "       )")
    public int insert(AccountGroup accountGroup);

    @Update("UPDATE account_group SET\n" +
            "       user_type = #{userType}, group_no = #{groupNo}, group_name = #{groupName}, level = #{level}, p_id = #{pId}, note = #{note}, leader = #{leader}, contact_phone = #{contactPhone}, email = #{email}, create_time = #{createTime}, update_time = #{updateTime}\n" +
            "       WHERE  id = #{id}")
    public int update(AccountGroup accountGroup);

    @Delete("DELETE FROM account_group WHERE  id = #{id}")
    public int deleteById(@Param("id") int id);

    @Select("SELECT * FROM account_group WHERE  id = #{id}")
    public AccountGroup getById(@Param("id") int id);

    @Select("SELECT * FROM account_group WHERE  group_no = #{groupNo}")
    public AccountGroup getByNo(@Param("groupNo") String groupNo);

    @Select("<script>SELECT  COUNT( DISTINCT(ID)) FROM account_group where 1=1 " +
            "<if test='level != null'> and level = #{level}</if> " +
            "<if test='pId != null'> and p_id = #{pId}</if></script> ")
    public int count(AccountGroupVo queryVo);

    @Select("<script>select * from account_group where 1=1" +
            "<if test='level != null'> and level = #{level}</if> " +
            "<if test='pId != null'>and p_id = #{pId}</if> " +
            "order by group_no asc " +
            "limit #{offset},#{limit}</script>")
    public List<AccountGroup> pageQuery(AccountGroupVo queryVo);
}