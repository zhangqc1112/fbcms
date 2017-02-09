package com.comp.admin.biz;

import com.comp.admin.dao.AccountGroupAreaDao;
import com.comp.admin.entities.AccountGroupArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class AccountGroupAreaBo {

    @Autowired
    private AccountGroupAreaDao accountGroupAreaDao;

    public AccountGroupArea insert(AccountGroupArea accountGroupArea) {
        int count = accountGroupAreaDao.insert(accountGroupArea);
        if(0 == count){
            return null;
        }
        return accountGroupArea; 
    }

    public AccountGroupArea update(AccountGroupArea accountGroupArea) {
        int count = accountGroupAreaDao.update(accountGroupArea);
        if(0 == count){
            return null;
        }
        return accountGroupArea; 
    }

    public AccountGroupArea deleteById(int id) {
        AccountGroupArea accountGroupArea = getById(id);
        int count = accountGroupAreaDao.deleteById(id);
        if(0 == count){
            return null;
        }
        return accountGroupArea; 
    }

    public AccountGroupArea getById(int id) {
        AccountGroupArea accountGroupArea = accountGroupAreaDao.getById(id);
        return accountGroupArea; 
    }

    public int count(){
        int count = accountGroupAreaDao.count();
        return count ;
    }

    public List<AccountGroupArea> pageQuery(int pageNo,int pageSize){ 
        List<AccountGroupArea> lists = accountGroupAreaDao.pageQuery(pageNo, pageSize);
        return lists ;
    }

}