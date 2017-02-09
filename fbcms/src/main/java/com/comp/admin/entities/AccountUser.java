package com.comp.admin.entities;

import java.util.Date;
import java.util.List;

public class AccountUser {

    private String userId; 
    private String userNo; 
    private String groupNo; 
    private String groupName; 
    private String userName; 
    private String phone; 
    private String email; 
    private Integer status; 
    private Date updateTime; 
    private Date createTime;

    private String loginName;
    private String statusName;
    private List<String> roleCodes;

    public AccountUser(){ 
    } 

    public void setUserId(String userId) { 
        this.userId = userId;
    }

    public String getUserId( ) { 
        return this.userId;
    }

    public void setUserNo(String userNo) { 
        this.userNo = userNo;
    }

    public String getUserNo( ) { 
        return this.userNo;
    }

    public void setGroupNo(String groupNo) { 
        this.groupNo = groupNo;
    }

    public String getGroupNo( ) { 
        return this.groupNo;
    }

    public void setGroupName(String groupName) { 
        this.groupName = groupName;
    }

    public String getGroupName( ) { 
        return this.groupName;
    }

    public void setUserName(String userName) { 
        this.userName = userName;
    }

    public String getUserName( ) { 
        return this.userName;
    }

    public void setPhone(String phone) { 
        this.phone = phone;
    }

    public String getPhone( ) { 
        return this.phone;
    }

    public void setEmail(String email) { 
        this.email = email;
    }

    public String getEmail( ) { 
        return this.email;
    }

    public void setStatus(Integer status) { 
        this.status = status;
    }

    public Integer getStatus( ) { 
        return this.status;
    }

    public void setUpdateTime(Date updateTime) { 
        this.updateTime = updateTime;
    }

    public Date getUpdateTime( ) { 
        return this.updateTime;
    }

    public void setCreateTime(Date createTime) { 
        this.createTime = createTime;
    }

    public Date getCreateTime( ) { 
        return this.createTime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(List<String> roleCodes) {
        this.roleCodes = roleCodes;
    }
}