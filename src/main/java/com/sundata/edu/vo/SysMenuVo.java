package com.sundata.edu.vo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单表 sys_menu
 * 
 * @author 侯鹏
 * @date 2019-04-17 15:22:45
 */
public class SysMenuVo extends BaseVo {
    /**
     * 菜单ID
     */
	private Integer menuId;
    /**
     * 菜单名称
     */
	private String menuName;
    /**
     * 父菜单ID
     */
	private Integer parentId;
    /**
     * 显示顺序
     */
	private Integer sort;
    /**
     * 请求地址
     */
	private String url;
    /**
     * 菜单类型（1目录 2菜单 3按钮）
     */
	private Integer menuType;
    /**
     * 菜单状态（1显示 0隐藏）
     */
	private Integer status;
    /**
     * 权限标识
     */
	private String perms;
    /**
     * 菜单图标
     */
	private String icon;
    /**
     * 备注
     */
	private String remark;
    /**
     * 创建时间
     */
	private Date created;
    /**
     * 修改时间
     */
	private Date updated;
    /**
     * 是否删除 1=已删除 0=未删除
     */
	private Integer isdelete;
	/**
	 * 子菜单
	 */
	private List<SysMenuVo> children = new ArrayList<SysMenuVo>();

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getMenuId()
	{
		return menuId;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuName()
	{
		return menuName;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getParentId()
	{
		return parentId;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort()
	{
		return sort;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl()
	{
		return url;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getMenuType()
	{
		return menuType;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public String getPerms()
	{
		return perms;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon()
	{
		return icon;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated()
	{
		return created;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getUpdated()
	{
		return updated;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public Integer getIsdelete()
	{
		return isdelete;
	}

	public List<SysMenuVo> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenuVo> children) {
		this.children = children;
	}

	@Override
    public String toString() {
        return "SysMenuVo{" +
        		"menuId=" + menuId +
        		"menuName=" + menuName +
        		"parentId=" + parentId +
        		"sort=" + sort +
        		"url=" + url +
        		"menuType=" + menuType +
        		"status=" + status +
        		"perms=" + perms +
        		"icon=" + icon +
        		"remark=" + remark +
        		"created=" + created +
        		"updated=" + updated +
        		"isdelete=" + isdelete +
				"}";
    }
}
