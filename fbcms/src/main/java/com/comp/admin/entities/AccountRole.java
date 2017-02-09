package com.comp.admin.entities;


public class AccountRole {

    private Integer id; 
    private String userType; 
    private String roleCode; 
    private String roleName; 
    private String note; 
    private String groupName; 

    public AccountRole(){ 
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

    public void setRoleName(String roleName) { 
        this.roleName = roleName;
    }

    public String getRoleName( ) { 
        return this.roleName;
    }

    public void setNote(String note) { 
        this.note = note;
    }

    public String getNote( ) { 
        return this.note;
    }

    public void setGroupName(String groupName) { 
        this.groupName = groupName;
    }

    public String getGroupName( ) { 
        return this.groupName;
    }

}