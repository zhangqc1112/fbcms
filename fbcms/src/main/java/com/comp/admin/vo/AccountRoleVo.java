package com.comp.admin.vo;


public class AccountRoleVo {

    private Integer id;
    private String userType;
    private String roleCode;
    private String roleName;
    private String note;
    private String groupName;

    private Integer menuNum;
    private Integer userNum;

    private Integer offset;
    private Integer limit;

    public AccountRoleVo(){
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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getMenuNum() {
        return menuNum;
    }

    public void setMenuNum(Integer menuNum) {
        this.menuNum = menuNum;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }
}