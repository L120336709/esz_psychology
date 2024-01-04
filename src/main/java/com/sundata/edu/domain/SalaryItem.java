package com.sundata.edu.domain;

import java.util.Date;

/**
 * 工资条
 */
public class SalaryItem extends Person {
    /**
     * 薪资信息，json格式
     */
    private String salaryInfo;
    /**
     * 所属月份
     */
    private Date belongDate;
    /**
     * 导入操作人，谁导入的，身份证号
     */
    private String creatorId;

    public Date getBelongDate() {
        return belongDate;
    }

    public void setBelongDate(Date belongDate) {
        this.belongDate = belongDate;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getSalaryInfo() {
        return salaryInfo;
    }

    public void setSalaryInfo(String salaryInfo) {
        this.salaryInfo = salaryInfo;
    }
}
