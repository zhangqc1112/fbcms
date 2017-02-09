package com.comp.admin.biz;

import com.comp.admin.dao.AccountGroupDao;
import com.comp.admin.dao.AccountLoginInfoDao;
import com.comp.admin.dao.AccountUserDao;
import com.comp.admin.dao.AccountUserRefRoleDao;
import com.comp.admin.entities.AccountGroup;
import com.comp.admin.entities.AccountLoginInfo;
import com.comp.admin.entities.AccountUser;
import com.comp.admin.entities.AccountUserRefRole;
import com.comp.admin.enums.StatusEnum;
import com.comp.admin.utils.ConstantUtil;
import com.comp.admin.utils.UUIDUtils;
import com.comp.admin.vo.AccountUserVo;
import com.fbcms.util.DateUtil;
import com.fbcms.util.MD5Encrypt;
import com.fbcms.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Service
public class AccountUserBo {

    @Autowired
    private AccountUserDao accountUserDao;

    @Autowired
    private AccountUserRefRoleDao accountUserRefRoleDao;

    @Autowired
    private AccountLoginInfoDao accountLoginInfoDao;

    @Autowired
    private AccountGroupDao accountGroupDao;

    public AccountUser insert(AccountUser accountUser) {
        accountUser.setCreateTime(DateUtil.getCurrentDateTime());
        accountUser.setUpdateTime(DateUtil.getCurrentDateTime());
        int count = accountUserDao.insert(accountUser);
        if(0 == count){
            return null;
        }
        return accountUser; 
    }


    public AccountUser update(AccountUser accountUser) {
        accountUser.setUpdateTime(DateUtil.getCurrentDateTime());
        int count = accountUserDao.update(accountUser);
        if(0 == count){
            return null;
        }
        return accountUser; 
    }

    /**
     * @param user
     */
    @Transactional
    public AccountUser save(AccountUserVo user) {
        AccountUser accountUser = new AccountUser();
        AccountGroup group = accountGroupDao.getByNo(user.getGroupNo());
        if (StringUtil.isEmptyOrNull(user.getUserId())) {
            String userId = UUIDUtils.createUserId();
            BeanUtils.copyProperties(user,accountUser);
            accountUser.setUserId(userId);
            accountUser.setStatus(StatusEnum.STATUS_VALID.getCode());
            accountUser.setGroupName(group.getGroupName());
            accountUser.setUserNo(group.getGroupNo());
            insert(accountUser);
        }else{
            String userId = user.getUserId();
            BeanUtils.copyProperties(user,accountUser);
            accountUser.setGroupName(group.getGroupName());
            accountUser.setUserNo(group.getGroupNo());
            update(accountUser);
            accountUserRefRoleDao.deleteByUserId(userId);
        }
        if (user.getRoleCodes() != null) {
            for (String code : user.getRoleCodes()) {
                AccountUserRefRole userrole = new AccountUserRefRole();
                userrole.setRoleCode(code);
                userrole.setUserId(accountUser.getUserId());
                userrole.setUserType("e");
                accountUserRefRoleDao.insert(userrole);
            }
        }
        return accountUser;
    }
    public AccountUser deleteByUserId(String userId) {
        AccountUser accountUser = getByUserId(userId);
        int count = accountUserDao.deleteByUserId(userId);
        if(0 == count){
            return null;
        }
        return accountUser; 
    }

    public void updateStatus(String userId,Integer status){
        accountUserDao.updateStatus(userId,status);
    }

    public void saveAccount(String userId,String loginName){
        AccountLoginInfo accountUser = accountLoginInfoDao.getByUserId(userId);
        if(accountUser == null){
            accountUser = new AccountLoginInfo();
            accountUser.setUserId(userId);
            accountUser.setLoginName(loginName);
            accountUser.setPassword(MD5Encrypt.md5(ConstantUtil.DEFAULT_PASSWORD));
            accountUser.setUserType("e");
            accountUser.setSalt("mis");
            accountUser.setStatus(StatusEnum.STATUS_VALID.getCode());
            accountUser.setCreateTime(DateUtil.getCurrentDateTime());
            accountUser.setUpdateTime(DateUtil.getCurrentDateTime());
            accountLoginInfoDao.insert(accountUser);
        }else{
            accountLoginInfoDao.saveAccount(userId,loginName);
        }
    }

    public void resetPwd(String userId,String password){
        accountLoginInfoDao.resetPwd(userId, MD5Encrypt.md5(password));
    }

    public AccountUser getByUserId(String userId) {
        AccountUser accountUser = accountUserDao.getByUserId(userId);
        return accountUser; 
    }

    public int count(AccountUserVo queryVo){
        int count = accountUserDao.count(queryVo);
        return count ;
    }

    public List<AccountUser> pageQuery(AccountUserVo queryVo){
        List<AccountUser> lists = accountUserDao.pageQuery(queryVo);
        return lists ;
    }

}