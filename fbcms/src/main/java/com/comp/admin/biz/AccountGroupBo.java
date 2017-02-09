package com.comp.admin.biz;

import com.comp.admin.dao.AccountGroupDao;
import com.comp.admin.entities.AccountGroup;
import com.comp.admin.vo.AccountGroupVo;
import com.fbcms.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AccountGroupBo {

    @Autowired
    private AccountGroupDao accountGroupDao;

    public AccountGroup insert(AccountGroup accountGroup) {
        accountGroup.setUserType("e");
        initGroupNo(accountGroup);
        accountGroup.setCreateTime(DateUtil.getCurrentDateTime());
        accountGroup.setUpdateTime(DateUtil.getCurrentDateTime());
        int count = accountGroupDao.insert(accountGroup);
        if(0 == count){
            return null;
        }
        return accountGroup; 
    }

    public AccountGroup update(AccountGroup accountGroup) {
        accountGroup.setUserType("e");
        accountGroup.setUpdateTime(DateUtil.getCurrentDateTime());
        int count = accountGroupDao.update(accountGroup);
        if(0 == count){
            return null;
        }
        return accountGroup; 
    }

    public AccountGroup deleteById(int id) {
        AccountGroup accountGroup = getById(id);
        int count = accountGroupDao.deleteById(id);
        if(0 == count){
            return null;
        }
        return accountGroup; 
    }

    public AccountGroup getById(int id) {
        AccountGroup accountGroup = accountGroupDao.getById(id);
        return accountGroup; 
    }

    public int count(AccountGroupVo queryVo){
        int count = accountGroupDao.count(queryVo);
        return count ;
    }

    public List<AccountGroup> pageQuery(AccountGroupVo queryVo){
        List<AccountGroup> lists = accountGroupDao.pageQuery(queryVo);
        return lists ;
    }

    private void initGroupNo(AccountGroup po) {
        Integer pId = po.getPId();
        if (pId == null) {
            AccountGroupVo queryVo = new AccountGroupVo();
            queryVo.setLevel(1);
            queryVo.setOffset(0);
            queryVo.setLimit(Integer.MAX_VALUE);
            List<AccountGroup> lists = accountGroupDao.pageQuery(queryVo);
            po.setGroupNo(getGroupNo(lists, ""));
            po.setLevel(1);
        } else {
            AccountGroup parent = getById(pId);
            po.setLevel(parent.getLevel() + 1);
            AccountGroupVo queryVo = new AccountGroupVo();
            queryVo.setLevel(po.getLevel());
            queryVo.setOffset(0);
            queryVo.setpId(pId);
            queryVo.setLimit(Integer.MAX_VALUE);
            List<AccountGroup> lists = accountGroupDao.pageQuery(queryVo);
            po.setGroupNo(getGroupNo(lists, parent.getGroupNo()));
        }
    }
    private String getGroupNo(List<AccountGroup> list, String pGroupNo) {
        if (list.isEmpty()) {
            return pGroupNo + "01";
        } else {
            String lastGn = list.get(list.size() - 1).getGroupNo();
            int no = Integer.parseInt(lastGn) + 1;
            return StringUtils.leftPad(String.valueOf(no), lastGn.length(), '0');
        }
    }

}