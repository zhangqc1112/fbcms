package com.comp.admin.biz;

import com.comp.admin.dao.AccountRoleDao;
import com.comp.admin.dao.AccountRoleRefMenuDao;
import com.comp.admin.dao.AccountUserRefRoleDao;
import com.comp.admin.dao.MenuExtendDao;
import com.comp.admin.entities.AccountRole;
import com.comp.admin.entities.AccountRoleRefMenu;
import com.comp.admin.entities.AccountUserRefRole;
import com.comp.admin.utils.BaseException;
import com.comp.admin.vo.AccountRoleVo;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AccountRoleBo {

    @Autowired
    private AccountRoleDao accountRoleDao;

    @Autowired
    private MenuExtendDao menuExtendDao;

    @Autowired
    private AccountRoleRefMenuDao accountRoleRefMenuDao;

    @Autowired
    private AccountUserRefRoleDao accountUserRefRoleDao;

    public AccountRole insert(AccountRoleVo accountRole) {
        AccountRole role = new AccountRole();
        BeanUtils.copyProperties(accountRole,role);
        int count = accountRoleDao.insert(role);
        if(0 == count){
            return null;
        }
        return role;
    }

    public AccountRole update(AccountRoleVo accountRole) {
        AccountRole role = new AccountRole();
        BeanUtils.copyProperties(accountRole,role);
        int count = accountRoleDao.update(role);
        if(0 == count){
            return null;
        }
        return role;
    }

    @Transactional
    public AccountRole deleteById(int id) {
        AccountRole accountRole = getById(id);

        List<AccountRoleRefMenu> list1 = accountRoleRefMenuDao.getListByCode(accountRole.getRoleCode());

        if(list1 != null && !list1.isEmpty()){
            throw new BaseException("该角色正在被使用，请解除相关用户和角色的关联后再删除。");
        }
        List<AccountRoleRefMenu> list2 = accountUserRefRoleDao.getListByCode(accountRole.getRoleCode());
        if(list2 != null && !list2.isEmpty()){
            throw new BaseException("该角色正在被使用，请解除相关用户和角色的关联后再删除。");
        }
        int count = accountRoleDao.deleteById(id);

        if(0 == count){
            return null;
        }
        accountRoleRefMenuDao.deleteByRoleCode(accountRole.getRoleCode());
        return accountRole; 
    }



    public AccountRole getById(int id) {
        AccountRole accountRole = accountRoleDao.getById(id);
        return accountRole; 
    }

    public int count(AccountRoleVo queryVo){
        int count = accountRoleDao.count(queryVo);
        return count ;
    }

    public List<AccountRole> pageQuery(AccountRoleVo queryVo){
        List<AccountRole> lists = accountRoleDao.pageQuery(queryVo);
        return lists ;
    }

    public List<AccountRole> findAll(){
        List<AccountRole> lists = accountRoleDao.findAll();
        return lists ;
    }

    public List<AccountRoleVo> getRoleList(String userType) {
        List<AccountRoleVo> result = new ArrayList<>();
        List<Map> menuNumList = menuExtendDao.queryRoleMenuNum("e");
        Map<String, Long> menuNumMap = new HashMap<>();
        for (Map map : menuNumList) {
            String roleCode = (String) map.get("role_code");
            Long menuNum = (Long) map.get("menuNum");
            if (menuNum != null) {
                menuNumMap.put(roleCode, menuNum);
            }
        }
        List<Map> userNumList = menuExtendDao.queryRoleUserNum("e");
        Map<String, Long> userNumMap = new HashMap<>();
        for (Map map : userNumList) {
            String roleCode = (String) map.get("role_code");
            Long userNum = (Long) map.get("userNum");
            if (userNum != null) {
                userNumMap.put(roleCode, userNum);
            }
        }
        AccountRoleVo queryVo = new AccountRoleVo();
        queryVo.setUserType(userType);
        queryVo.setOffset(0);
        queryVo.setLimit(Integer.MAX_VALUE);
        List<AccountRole> rolePoList = pageQuery(queryVo);
        for (AccountRole rolePo : rolePoList) {
            AccountRoleVo vo = new AccountRoleVo();
            BeanUtils.copyProperties(rolePo, vo);
            Long num = menuNumMap.get(rolePo.getRoleCode());
            vo.setMenuNum(num == null ? 0 : num.intValue());
            num = userNumMap.get(rolePo.getRoleCode());
            vo.setUserNum(num == null ? 0 : num.intValue());
            result.add(vo);
        }
        return result;
    }
}