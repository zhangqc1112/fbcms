package com.comp.admin.entities;


public class AccountUserRefRole {

    private Integer id; 
    private String userType; 
    private String userId;
    private String roleCode; 

    public AccountUserRefRole(){ 
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId( ) {
        return this.userId;
    }

    public void setRoleCode(String roleCode) { 
        this.roleCode = roleCode;
    }

    public String getRoleCode( ) { 
        return this.roleCode;
    }

}