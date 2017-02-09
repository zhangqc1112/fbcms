package com.comp.admin.biz;

import com.comp.admin.dao.AccountModuleDao;
import com.comp.admin.entities.AccountModule;
import com.comp.admin.vo.AccountModuleVo;
import com.fbcms.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class AccountModuleBo {

    @Autowired
    private AccountModuleDao accountModuleDao;

    public AccountModule insert(AccountModuleVo accountModuleVo) {
        AccountModule accountModule = new AccountModule();
        BeanUtils.copyProperties(accountModuleVo,accountModule);
        accountModule.setCreateTime(DateUtil.getCurrentDateTime());
        accountModule.setUpdateTime(DateUtil.getCurrentDateTime());
        int count = accountModuleDao.insert(accountModule);
        if(0 == count){
            return null;
        }
        return accountModule; 
    }

    public AccountModule update(AccountModuleVo accountModuleVo) {
        AccountModule accountModule = getById(accountModuleVo.getId());
        accountModule.setMenuTree(accountModuleVo.getMenuTree());
        accountModule.setCode(accountModuleVo.getCode());
        accountModule.setIndexUrl(accountModuleVo.getIndexUrl());
        accountModule.setLogoUrl(accountModuleVo.getLogoUrl());
        accountModule.setName(accountModuleVo.getName());
        accountModule.setNote(accountModuleVo.getNote());
        accountModule.setUpdateTime(DateUtil.getCurrentDateTime());
        int count = accountModuleDao.update(accountModule);
        if(0 == count){
            return null;
        }
        return accountModule; 
    }

    public AccountModule updateTreeByCode(String code,String menuTree) {
        AccountModule accountModule = getByCode(code);
        accountModule.setMenuTree(menuTree);
        accountModule.setUpdateTime(DateUtil.getCurrentDateTime());
        int count = accountModuleDao.update(accountModule);
        if(0 == count){
            return null;
        }
        return accountModule;
    }

    public int deleteById(int id) {
        AccountModule accountModule = getById(id);
        int count = accountModuleDao.deleteById(id);
        if(0 == count){
            return 0;
        }
        return count;
    }

    public AccountModule getById(int id) {
        AccountModule accountModule = accountModuleDao.getById(id);
        return accountModule; 
    }

    public AccountModule getByCode(String code) {
        AccountModule accountModule = accountModuleDao.getByCode(code);
        return accountModule;
    }

    public int count(AccountModuleVo queryVo){
        int count = accountModuleDao.count(queryVo);
        return count ;
    }

    public List<AccountModule> pageQuery(AccountModuleVo queryVo){
        List<AccountModule> lists = accountModuleDao.pageQuery(queryVo);
        return lists ;
    }

}