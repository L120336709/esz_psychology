package com.sundata.edu.vo;
import java.util.Date;
/**
 * 字典数据表 sys_dict_data
 * 
 * @author 侯鹏
 * @date 2019-04-17 16:21:00
 */
public class SysDictDataVo extends BaseVo {
    /**
     * 字典编码
     */
	private Integer dataId;
    /**
     * 字典排序
     */
	private Integer dataSort;
    /**
     * 字典标签
     */
	private String dataLabel;
    /**
     * 字典键值
     */
	private String dataValue;
    /**
     * 字典类型
     */
	private String typeCode;
    /**
     * 样式属性
     */
	private String cssClass;
    /**
     * 回显样式
     */
	private String listClass;
    /**
     * 是否默认（1是 0否）
     */
	private Integer isDefault;
    /**
     * 状态（1正常 0停用）
     */
	private Integer status;
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

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public Integer getDataId()
	{
		return dataId;
	}

	public void setDataSort(Integer dataSort) {
		this.dataSort = dataSort;
	}

	public Integer getDataSort()
	{
		return dataSort;
	}

	public void setDataLabel(String dataLabel) {
		this.dataLabel = dataLabel;
	}

	public String getDataLabel()
	{
		return dataLabel;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public String getDataValue()
	{
		return dataValue;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeCode()
	{
		return typeCode;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getCssClass()
	{
		return cssClass;
	}

	public void setListClass(String listClass) {
		this.listClass = listClass;
	}

	public String getListClass()
	{
		return listClass;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getIsDefault()
	{
		return isDefault;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
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

	@Override
    public String toString() {
        return "SysDictDataVo{" +
        		"dataId=" + dataId +
        		"dataSort=" + dataSort +
        		"dataLabel=" + dataLabel +
        		"dataValue=" + dataValue +
        		"typeCode=" + typeCode +
        		"cssClass=" + cssClass +
        		"listClass=" + listClass +
        		"isDefault=" + isDefault +
        		"status=" + status +
        		"remark=" + remark +
        		"created=" + created +
        		"updated=" + updated +
				"}";
    }
}
