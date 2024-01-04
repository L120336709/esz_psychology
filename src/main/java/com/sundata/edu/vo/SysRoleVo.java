package com.sundata.edu.vo;

import java.util.Arrays;
import java.util.Date;

/**
 * 角色表 sys_role
 *
 * @author 侯鹏
 * @date 2019-04-17 15:22:45
 */
public class SysRoleVo extends BaseVo {
    /**
     * 主键id
     */
    private Integer roleId;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 是否系统角色 1=是 0=否
     */
    private Integer system;
    /**
     * 状态 1=可用 0=不可用
     */
    private Integer status;
    /**
     * 创建人机构id
     */
    private String orgId;
    /**
     * 创建用户user_id
     */
    private String userId;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 修改时间
     */
    private Date updated;
    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单组
     */
    private Integer[] menuIds;

    private Integer level;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Integer[] menuIds) {
        this.menuIds = menuIds;
    }

    @Override
    public String toString() {
        return "SysRoleVo{" +
                "roleId=" + roleId +
                ", roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", system=" + system +
                ", status=" + status +
                ", orgId='" + orgId + '\'' +
                ", userId='" + userId + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", remark='" + remark + '\'' +
                ", menuIds=" + Arrays.toString(menuIds) +
                ", level=" + level +
                '}';
    }
}
