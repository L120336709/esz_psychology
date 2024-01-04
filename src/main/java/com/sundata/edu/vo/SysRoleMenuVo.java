package com.sundata.edu.vo;

/**
 * 角色菜单表 sys_role_menu
 * 
 * @author 侯鹏
 * @date 2019-04-17 15:22:45
 */
public class SysRoleMenuVo extends BaseVo {
    /**
     * 主键
     */
	private Integer id;
    /**
     * 角色ID
     */
	private Integer roleId;
    /**
     * 菜单ID
     */
	private Integer menuId;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId()
	{
		return roleId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getMenuId()
	{
		return menuId;
	}

	@Override
    public String toString() {
        return "SysRoleMenuVo{" +
        		"id=" + id +
        		"roleId=" + roleId +
        		"menuId=" + menuId +
				"}";
    }
}
