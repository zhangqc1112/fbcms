package com.comp.admin.vo;

import java.util.Date;

public class AccountModuleVo {

    private Integer id;
    private String userType;
    private String code;
    private String name;
    private String logoUrl;
    private String indexUrl;
    private Integer isHidden;
    private String note;
    private String menuTree;
    private Date createTime;
    private Date updateTime;

    public AccountModuleVo(){
    } 

    public void setId(Integer id) { 
        this.id = id;
    }

    public Integer getId( ) { 
        return this.id;
    }

    public void setUserType(String userType) { 
        this.userType = userType;
    }

    public String getUserType( ) { 
        return this.userType;
    }

    public void setCode(String code) { 
        this.code = code;
    }

    public String getCode( ) { 
        return this.code;
    }

    public void setName(String name) { 
        this.name = name;
    }

    public String getName( ) { 
        return this.name;
    }

    public void setLogoUrl(String logoUrl) { 
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl( ) { 
        return this.logoUrl;
    }

    public void setIndexUrl(String indexUrl) { 
        this.indexUrl = indexUrl;
    }

    public String getIndexUrl( ) { 
        return this.indexUrl;
    }

    public void setIsHidden(Integer isHidden) { 
        this.isHidden = isHidden;
    }

    public Integer getIsHidden( ) { 
        return this.isHidden;
    }

    public void setNote(String note) { 
        this.note = note;
    }

    public String getNote( ) { 
        return this.note;
    }

    public void setMenuTree(String menuTree) { 
        this.menuTree = menuTree;
    }

    public String getMenuTree( ) { 
        return this.menuTree;
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