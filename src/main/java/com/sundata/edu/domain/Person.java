package com.sundata.edu.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息实体
 */
public class Person implements Serializable {
    /**
     * 状态，0：被禁用
     */
    public static final int STATE_DISABLE = 0;

    /**
     * 状态，1：被启用
     */
    public static final int STATE_NORMAL = 1;

    /**
     * 状态，-1：被删除
     */
    public static final int STATE_DELETE = -1;

    /**
     * 主键
     */
    protected String id;

    /**
     * 身份证号码
     */
    protected String idCard;

    /**
     * 姓名
     */
    protected String name;

    /**
     * 状态，0：正常，1：被禁用，-1：被删除
     */
    protected int state;

    /**
     * 角色id，目前只有一个角色，1:管理员<br>取值范围见：{@link com.yunxinlink.salary.enums.Role}
     */
    protected int roleId;

    /**
     * 创建时间
     */
    protected Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * 用户状态是否已被删除
     */
    public boolean checkDeleted() {
        return state == STATE_DELETE;
    }
}
