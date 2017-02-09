package com.comp.admin.entities;

import java.util.Date;

public class AccountGroup {

    private Integer id; 
    private String userType; 
    private String groupNo; 
    private String groupName; 
    private Integer level; 
    private Integer pId; 
    private String note; 
    private String leader; 
    private String contactPhone; 
    private String email; 
    private Date createTime; 
    private Date updateTime; 

    public AccountGroup(){ 
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

    public void setLevel(Integer level) { 
        this.level = level;
    }

    public Integer getLevel( ) { 
        return this.level;
    }

    public void setPId(Integer pId) { 
        this.pId = pId;
    }

    public Integer getPId( ) { 
        return this.pId;
    }

    public void setNote(String note) { 
        this.note = note;
    }

    public String getNote( ) { 
        return this.note;
    }

    public void setLeader(String leader) { 
        this.leader = leader;
    }

    public String getLeader( ) { 
        return this.leader;
    }

    public void setContactPhone(String contactPhone) { 
        this.contactPhone = contactPhone;
    }

    public String getContactPhone( ) { 
        return this.contactPhone;
    }

    public void setEmail(String email) { 
        this.email = email;
    }

    public String getEmail( ) { 
        return this.email;
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