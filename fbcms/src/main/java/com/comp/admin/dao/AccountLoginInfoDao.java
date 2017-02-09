package com.comp.admin.dao;

import com.comp.admin.entities.AccountLoginInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AccountLoginInfoDao {

    @Insert("INSERT INTO account_login_info\n" +
            "       (\n" +
            "            user_id, login_name, password, salt, user_type, fund_id, status, version, create_time, update_time\n" +
            "       )\n" +
            "       VALUES \n" +
            "       ( \n" +
            "           #{userId}, #{loginName}, #{password}, #{salt}, #{userType}, #{fundId}, #{status}, #{version}, #{createTime}, #{updateTime}\n" +
            "       )")
    public int insert(AccountLoginInfo accountLoginInfo);

    @Update("UPDATE account_login_info SET\n" +
            "       user_id = #{userId}, login_name = #{loginName}, password = #{password}, salt = #{salt}, user_type = #{userType}, fund_id = #{fundId}, status = #{status}, version = #{version}, create_time = #{createTime}, update_time = #{updateTime}\n" +
            "       WHERE  id = #{id}")
    public int update(AccountLoginInfo accountLoginInfo);

    @Delete("UPDATE account_login_info set login_name = #{loginName} WHERE  user_id = #{userId}")
    public int saveAccount(@Param("userId") String userId,@Param("loginName") String loginName);

    @Delete("UPDATE account_login_info set password = #{password} WHERE  user_id = #{userId}")
    public int resetPwd(@Param("userId") String userId,@Param("password") String password);

    @Delete("DELETE FROM account_login_info WHERE  id = #{id}")
    public int deleteById(@Param("id") int id);

    @Select("SELECT * FROM account_login_info WHERE  id = #{id}")
    public AccountLoginInfo getById(@Param("id") int id);

    @Select("SELECT * FROM account_login_info WHERE  user_id = #{userId}")
    public AccountLoginInfo getByUserId(@Param("userId") String userId);

    @Select("SELECT * FROM account_login_info WHERE  login_name = #{loginName} and password = #{password}")
    public AccountLoginInfo login(@Param("loginName") String name,@Param("password") String password);

    public int count();

    public List<AccountLoginInfo> pageQuery(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);
}