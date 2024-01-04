package com.sundata.edu.domain;

import javax.persistence.*;

/**
 * 角色菜单表 sys_role_menu
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
@Table(name = "`sys_role_menu`")
public class SysRoleMenu {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`id`")
	private Integer id;
	/**
	 * 角色ID
	 */
	@Column(name = "`role_id`")
	private Integer roleId;
	/**
	 * 菜单ID
	 */
	@Column(name = "`menu_id`")
	private Integer menuId;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	@Override
    public String toString() {
        return "SysRoleMenu{" +
        		"id=" + id +
        		"roleId=" + roleId +
        		"menuId=" + menuId +
				"}";
    }
}
