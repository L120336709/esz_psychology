package com.sundata.edu.domain;

import com.sundata.edu.annotation.Excel;

import javax.persistence.*;
import java.util.Date;
/**
 * 字典数据表 sys_dict_data
 *
 * @author whj
 * @date 2019-04-17 16:21:00
 */
@Table(name = "`sys_dict_data`")
public class SysDictData {
	/**
	 * 字典编码  dictCode
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`data_id`")
	@Excel(name = "字典编码", cellType = Excel.ColumnType.NUMERIC)
	private Integer dataId;
	/**
	 * 字典排序  dictSort
	 */
	@Column(name = "`data_sort`")
	@Excel(name = "字典排序", cellType = Excel.ColumnType.NUMERIC)
	private Integer dataSort;
	/**
	 * 字典标签  dataLabel
	 */
	@Column(name = "`data_label`")
	@Excel(name = "字典标签")
	private String dataLabel;
	/**
	 * 字典键值  dataValue
	 */
	@Column(name = "`data_value`")
	@Excel(name = "字典键值")
	private String dataValue;
	/**
	 * 字典类型
	 */
	@Column(name = "`type_code`")
	@Excel(name = "字典类型")
	private String typeCode;
	/**
	 * 样式属性
	 */
	@Column(name = "`css_class`")
	@Excel(name = "样式属性")
	private String cssClass;
	/**
	 * 回显样式
	 */
	@Column(name = "`list_class`")
	@Excel(name = "回显样式")
	private String listClass;
	/**
	 * 是否默认（1是 0否）
	 */
	@Column(name = "`is_default`")
	@Excel(name = "是否默认")
	private Integer isDefault;
	/**
	 * 状态（1正常 0停用）
	 */
	@Column(name = "`status`")
	@Excel(name = "状态")
	private Integer status;
	/**
	 * 备注
	 */
	@Column(name = "`remark`")
	@Excel(name = "备注")
	private String remark;
	/**
	 * 创建时间
	 */
	@Column(name = "`created`")
	@Excel(name = "创建时间")
	private Date created;
	/**
	 * 修改时间
	 */
	@Column(name = "`updated`")
	@Excel(name = "修改时间")
	private Date updated;

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataSort(Integer dataSort) {
		this.dataSort = dataSort;
	}

	public Integer getDataSort() {
		return dataSort;
	}

	public void setDataLabel(String dataLabel) {
		this.dataLabel = dataLabel;
	}

	public String getDataLabel() {
		return dataLabel;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setListClass(String listClass) {
		this.listClass = listClass;
	}

	public String getListClass() {
		return listClass;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getUpdated() {
		return updated;
	}

	@Override
    public String toString() {
        return "SysDictData{" +
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
