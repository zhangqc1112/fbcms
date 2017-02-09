package com.comp.admin.biz;

import com.comp.admin.dao.AccountLoginInfoDao;
import com.comp.admin.entities.AccountLoginInfo;
import com.comp.admin.utils.BaseException;
import com.fbcms.util.MD5Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AccountLoginInfoBo {

    @Autowired
    private AccountLoginInfoDao accountLoginInfoDao;

    public AccountLoginInfo insert(AccountLoginInfo accountLoginInfo) {
        int count = accountLoginInfoDao.insert(accountLoginInfo);
        if(0 == count){
            return null;
        }
        return accountLoginInfo; 
    }

    public AccountLoginInfo update(AccountLoginInfo accountLoginInfo) {
        int count = accountLoginInfoDao.update(accountLoginInfo);
        if(0 == count){
            return null;
        }
        return accountLoginInfo; 
    }

    public AccountLoginInfo deleteById(int id) {
        AccountLoginInfo accountLoginInfo = getById(id);
        int count = accountLoginInfoDao.deleteById(id);
        if(0 == count){
            return null;
        }
        return accountLoginInfo; 
    }

    public AccountLoginInfo getById(int id) {
        AccountLoginInfo accountLoginInfo = accountLoginInfoDao.getById(id);
        return accountLoginInfo; 
    }

    public int count(){
        int count = accountLoginInfoDao.count();
        return count ;
    }

    public List<AccountLoginInfo> pageQuery(int pageNo,int pageSize){ 
        List<AccountLoginInfo> lists = accountLoginInfoDao.pageQuery(pageNo, pageSize);
        return lists ;
    }


    public AccountLoginInfo login(String loginName,String password) {
        password = MD5Encrypt.md5(password);
        AccountLoginInfo loginInfo = accountLoginInfoDao.login(loginName,password);
        if(loginInfo == null){
            throw new BaseException("用户名/密码输入错误");
        }
        return loginInfo;
    }


}