package com.comp.admin.vo;


public class AccountRoleRefMenuVo {

    private Integer id;
    private String userType;
    private String roleCode;
    private String moduleCode;
    private String menuCode;
    private String subFuncs;
    private Integer read;
    private Integer edit;
    private Integer delete;

    public AccountRoleRefMenuVo(){
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

    public void setRoleCode(String roleCode) { 
        this.roleCode = roleCode;
    }

    public String getRoleCode( ) { 
        return this.roleCode;
    }

    public void setModuleCode(String moduleCode) { 
        this.moduleCode = moduleCode;
    }

    public String getModuleCode( ) { 
        return this.moduleCode;
    }

    public void setMenuCode(String menuCode) { 
        this.menuCode = menuCode;
    }

    public String getMenuCode( ) { 
        return this.menuCode;
    }

    public void setSubFuncs(String subFuncs) { 
        this.subFuncs = subFuncs;
    }

    public String getSubFuncs( ) { 
        return this.subFuncs;
    }

    public void setRead(Integer read) { 
        this.read = read;
    }

    public Integer getRead( ) { 
        return this.read;
    }

    public void setEdit(Integer edit) { 
        this.edit = edit;
    }

    public Integer getEdit( ) { 
        return this.edit;
    }

    public void setDelete(Integer delete) { 
        this.delete = delete;
    }

    public Integer getDelete( ) { 
        return this.delete;
    }

}