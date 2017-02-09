package com.comp.admin.entities;

import java.util.Date;

public class AccountLoginInfo {

    private Integer id; 
    private String userId; 
    private String loginName; 
    private String password; 
    private String salt; 
    private String userType; 
    private String fundId; 
    private Integer status; 
    private Integer version; 
    private Date createTime; 
    private Date updateTime; 

    public AccountLoginInfo(){ 
    } 

    public void setId(Integer id) { 
        this.id = id;
    }

    public Integer getId( ) { 
        return this.id;
    }

    public void setUserId(String userId) { 
        this.userId = userId;
    }

    public String getUserId( ) { 
        return this.userId;
    }

    public void setLoginName(String loginName) { 
        this.loginName = loginName;
    }

    public String getLoginName( ) { 
        return this.loginName;
    }

    public void setPassword(String password) { 
        this.password = password;
    }

    public String getPassword( ) { 
        return this.password;
    }

    public void setSalt(String salt) { 
        this.salt = salt;
    }

    public String getSalt( ) { 
        return this.salt;
    }

    public void setUserType(String userType) { 
        this.userType = userType;
    }

    public String getUserType( ) { 
        return this.userType;
    }

    public void setFundId(String fundId) { 
        this.fundId = fundId;
    }

    public String getFundId( ) { 
        return this.fundId;
    }

    public void setStatus(Integer status) { 
        this.status = status;
    }

    public Integer getStatus( ) { 
        return this.status;
    }

    public void setVersion(Integer version) { 
        this.version = version;
    }

    public Integer getVersion( ) { 
        return this.version;
    }

    public void setCreateTime(Date createTime) { 
        this.createTime = createTime;
    }

    public Date getCreateTime( ) { 
        return this.createTime;
    }

    public void setUpdateTime(Date updateTime) { 
        this.updateTime = updateTime;
    }

    public Date getUpdateTime( ) { 
        return this.updateTime;
    }

}