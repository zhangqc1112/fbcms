package com.comp.admin.dao;

import com.comp.admin.entities.AccountUser;
import com.comp.admin.vo.AccountUserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AccountUserDao {

    @Insert("INSERT INTO account_user\n" +
            "       (\n" +
            "           user_id, user_no, group_no, group_name, user_name,phone, email, status, update_time, create_time\n" +
            "       )\n" +
            "       VALUES \n" +
            "       ( \n" +
            "           #{userId}, #{userNo}, #{groupNo}, #{groupName}, #{userName}, #{phone}, #{email}, #{status}, #{updateTime}, #{createTime}\n" +
            "       )")
    public int insert(AccountUser accountUser);

    @Update("UPDATE account_user SET\n" +
            "       user_id = #{userId}, user_no = #{userNo}, group_no = #{groupNo}, group_name = #{groupName}, user_name = #{userName}, phone = #{phone}, email = #{email}, status = #{status}, update_time = #{updateTime}, create_time = #{createTime}\n" +
            "       WHERE  user_id = #{userId}")
    public int update(AccountUser accountUser);

    public int deleteByUserId(@Param("userId") String userId);

    @Update("update account_user set status = #{status} where user_id = #{userId}")
    public int updateStatus(@Param("userId") String userId,@Param("status") Integer status);

    @Select("select * from account_user where user_id= #{userId}")
    public AccountUser getByUserId(@Param("userId") String userId);

    @Select("<script>select count(user_id) from account_user where status != -1 </script>")
    public int count(AccountUserVo queryVo);

    @Select("<script>SELECT\n" +
            "\ta.*, ali.login_name\n" +
            "FROM\n" +
            "\taccount_user a\n" +
            "LEFT JOIN account_login_info ali ON a.user_id = ali.user_id" +
            " where a.status != -1 limit #{offset},#{limit}</script>")
    public List<AccountUser> pageQuery(AccountUserVo queryVo);

}