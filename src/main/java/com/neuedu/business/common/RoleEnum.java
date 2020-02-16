package com.neuedu.business.common;

/**
 * @类 名： RoleEnum <br/>
 * @描 述： <br/>
 * @日 期： 2020/2/16 10:43<br/>
 * @作 者： 鼠小倩<br/>
 * @版 本： 1.0.0
 * @since JDK 1.8
 */
public enum  RoleEnum {
    ADMIN(0,"管理员"),
    USER(1,"普通用户"),
    ;
    private int role;
    private String desc;
    RoleEnum(int role,String desc){
        this.role=role;
        this.desc=desc;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}



