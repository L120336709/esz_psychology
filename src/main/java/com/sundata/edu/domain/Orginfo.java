package com.sundata.edu.domain;

import javax.persistence.*;

/**
 * 机构表 orginfo
 *
 * @author whj
 * @date 2019-04-17 11:36:29
 */
@Table(name = "`orginfo`")
public class  Orginfo {
    /**
     * 组织机构id
     */
    @Id
    @Column(name = "`org_id`")
    private String orgId;
    /**
     * 父组织机构ID
     */
    @Column(name = "`parent_org_id`")
    private String parentOrgId;
    /**
     * 组织机构名称
     */
    @Column(name = "`org_name`")
    private String orgName;
    /**
     * 状态 1=正常 0=禁用
     */
    @Column(name = "`status`")
    private Integer status;

    @Column(name = "`org_type`")
    private Integer orgType;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`levels`")
    private Integer levels;

    @Column(name = "`elevel`")
    private Integer elevel;

    public Integer getElevel() {
        return elevel;
    }

    public void setElevel(Integer elevel) {
        this.elevel = elevel;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    @Override
    public String toString() {
        return "Orginfo{" +
                "orgId='" + orgId + '\'' +
                ", parentOrgId='" + parentOrgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", status=" + status +
                '}';
    }
}
