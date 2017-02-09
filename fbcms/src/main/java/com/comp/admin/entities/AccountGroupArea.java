package com.comp.admin.entities;


public class AccountGroupArea {

    private Integer id; 
    private String groupNo; 
    private Integer areaType; 
    private Integer areaId; 
    private String areaMark; 
    private String userType; 

    public AccountGroupArea(){ 
    } 

    public void setId(Integer id) { 
        this.id = id;
    }

    public Integer getId( ) { 
        return this.id;
    }

    public void setGroupNo(String groupNo) { 
        this.groupNo = groupNo;
    }

    public String getGroupNo( ) { 
        return this.groupNo;
    }

    public void setAreaType(Integer areaType) { 
        this.areaType = areaType;
    }

    public Integer getAreaType( ) { 
        return this.areaType;
    }

    public void setAreaId(Integer areaId) { 
        this.areaId = areaId;
    }

    public Integer getAreaId( ) { 
        return this.areaId;
    }

    public void setAreaMark(String areaMark) { 
        this.areaMark = areaMark;
    }

    public String getAreaMark( ) { 
        return this.areaMark;
    }

    public void setUserType(String userType) { 
        this.userType = userType;
    }

    public String getUserType( ) { 
        return this.userType;
    }

}