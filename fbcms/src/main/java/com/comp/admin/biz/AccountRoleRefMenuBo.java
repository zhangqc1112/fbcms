package com.comp.admin.biz;

import com.comp.admin.dao.AccountRoleRefMenuDao;
import com.comp.admin.entities.AccountRoleRefMenu;
import com.comp.admin.vo.AccountRoleRefMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class AccountRoleRefMenuBo {

    @Autowired
    private AccountRoleRefMenuDao accountRoleRefMenuDao;

    public AccountRoleRefMenu insert(AccountRoleRefMenu accountRoleRefMenu) {
        accountRoleRefMenu.setDelete(0);
        accountRoleRefMenu.setRead(0);
        accountRoleRefMenu.setEdit(0);
        int count = accountRoleRefMenuDao.insert(accountRoleRefMenu);
        if(0 == count){
            return null;
        }
        return accountRoleRefMenu; 
    }

    public AccountRoleRefMenu update(AccountRoleRefMenu accountRoleRefMenu) {
        int count = accountRoleRefMenuDao.update(accountRoleRefMenu);
        if(0 == count){
            return null;
        }
        return accountRoleRefMenu; 
    }

    public AccountRoleRefMenu deleteById(int id) {
        AccountRoleRefMenu accountRoleRefMenu = getById(id);
        int count = accountRoleRefMenuDao.deleteById(id);
        if(0 == count){
            return null;
        }
        return accountRoleRefMenu; 
    }

    public AccountRoleRefMenu getById(int id) {
        AccountRoleRefMenu accountRoleRefMenu = accountRoleRefMenuDao.getById(id);
        return accountRoleRefMenu; 
    }

    public int count(AccountRoleRefMenuVo queryVo){
        int count = accountRoleRefMenuDao.count(queryVo);
        return count ;
    }

    public List<AccountRoleRefMenu> pageQuery(AccountRoleRefMenuVo queryVo){
        List<AccountRoleRefMenu> lists = accountRoleRefMenuDao.pageQuery(queryVo);
        return lists ;
    }

    public List<AccountRoleRefMenu> getListByCode(AccountRoleRefMenuVo queryVo){
        List<AccountRoleRefMenu> lists = accountRoleRefMenuDao.getListByRoleAndMenuCode(queryVo);
        return lists ;
    }

    public List<String> findRoleCodesByUserId(String userId){
        List<String> lists = accountRoleRefMenuDao.findRoleCodesByUserId(userId);
        return lists ;
    }

    @Transactional
    public void updateRoleRefMenus(String userCode,String userType,List<AccountRoleRefMenu> lists ){
        accountRoleRefMenuDao.deleteByRoleCode(userCode);
        for(AccountRoleRefMenu roleRefMenu:lists){
            roleRefMenu.setRoleCode(userCode);
            roleRefMenu.setUserType(userType);
            insert(roleRefMenu);
        }
    }

}