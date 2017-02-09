package com.comp.admin.biz;

import com.comp.admin.dao.AccountUserRefRoleDao;
import com.comp.admin.entities.AccountUserRefRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AccountUserRefRoleBo {

    @Autowired
    private AccountUserRefRoleDao accountUserRefRoleDao;

    public AccountUserRefRole insert(AccountUserRefRole accountUserRefRole) {
        int count = accountUserRefRoleDao.insert(accountUserRefRole);
        if(0 == count){
            return null;
        }
        return accountUserRefRole; 
    }

    public AccountUserRefRole update(AccountUserRefRole accountUserRefRole) {
        int count = accountUserRefRoleDao.update(accountUserRefRole);
        if(0 == count){
            return null;
        }
        return accountUserRefRole; 
    }

    public AccountUserRefRole deleteById(int id) {
        AccountUserRefRole accountUserRefRole = getById(id);
        int count = accountUserRefRoleDao.deleteById(id);
        if(0 == count){
            return null;
        }
        return accountUserRefRole; 
    }

    public AccountUserRefRole getById(int id) {
        AccountUserRefRole accountUserRefRole = accountUserRefRoleDao.getById(id);
        return accountUserRefRole; 
    }

    public int count(){
        int count = accountUserRefRoleDao.count();
        return count ;
    }

    public List<AccountUserRefRole> pageQuery(int pageNo,int pageSize){ 
        List<AccountUserRefRole> lists = accountUserRefRoleDao.pageQuery(pageNo, pageSize);
        return lists ;
    }

}