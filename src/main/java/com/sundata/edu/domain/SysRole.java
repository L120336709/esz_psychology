package com.sundata.edu.domain;

import javax.persistence.*;
import java.util.Date;
/**
 * 角色表 sys_role
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
@Table(name = "`sys_role`")
public class SysRole {
	/**
	 * 主键id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`role_id`")
	private Integer roleId;
	/**
	 * 角色编码
	 */
	@Column(name = "`role_code`")
	private String roleCode;
	/**
	 * 角色名称
	 */
	@Column(name = "`role_name`")
	private String roleName;
	/**
	 * 是否系统角色 1=是 0=否
	 */
	@Column(name = "`system`")
	private Integer system;
	/**
	 * 状态 1=可用 0=不可用
	 */
	@Column(name = "`status`")
	private Integer status;
	/**
	 * 备注
	 */
	@Column(name = "`remark`")
	private String remark;
	/**
	 * 创建用户机构Id
	 */
	@Column(name = "`org_id`")
	private String orgId;
	/**
	 * 创建人user_id
	 */
	@Column(name = "`user_id`")
	private String userId;
	/**
	 * 创建时间
	 */
	@Column(name = "`created`")
	private Date created;
	/**
	 * 修改时间
	 */
	@Column(name = "`updated`")
	private Date updated;

	@Column(name = "`level`")
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Override
	public String toString() {
		return "SysRole{" +
				"roleId=" + roleId +
				", roleCode='" + roleCode + '\'' +
				", roleName='" + roleName + '\'' +
				", system=" + system +
				", status=" + status +
				", remark='" + remark + '\'' +
				", orgId='" + orgId + '\'' +
				", userId='" + userId + '\'' +
				", created=" + created +
				", updated=" + updated +
				", level=" + level +
				'}';
	}
}
