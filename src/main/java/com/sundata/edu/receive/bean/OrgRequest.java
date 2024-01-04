package com.sundata.edu.receive.bean;

public class OrgRequest {

    private String outOrgId;

    private String outParentOrgId;

    private String orgName;

    private Integer status;

    private String orgType;

    private Integer elevel;

    public Integer getElevel() {
        return elevel;
    }

    public void setElevel(Integer elevel) {
        this.elevel = elevel;
    }

    public String getOutOrgId() {
        return outOrgId;
    }

    public void setOutOrgId(String outOrgId) {
        this.outOrgId = outOrgId;
    }

    public String getOutParentOrgId() {
        return outParentOrgId;
    }

    public void setOutParentOrgId(String outParentOrgId) {
        this.outParentOrgId = outParentOrgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }
}
