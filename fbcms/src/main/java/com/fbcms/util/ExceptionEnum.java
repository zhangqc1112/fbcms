package com.fbcms.util;

public enum ExceptionEnum {

    USER_INFO_IS_NOT_EXIT(2000001,"用户信息不存在"),
    USER_INFO_IS_EXIT(2000002,"用户账号已存在"),
    MENU_INFO_HAS_SUB_MENU(2000003,"请先删除子菜单"),
    SESSION_DISAPPEAR(2000004,"会话失效！！！");
    
    private final Integer code;
    private final String desc;

    private ExceptionEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        // TODO Auto-generated method stub
        return code;
    }

    public String getMessage() {
        // TODO Auto-generated method stub
        return desc;
    }

}
