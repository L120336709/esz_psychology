package com.sundata.edu.receive.bean;

public class OrgInfoData {
    private Integer id;
    private Integer pid;
    private String orgid;
    private String orgname;
    private String orgtype;
    private String porgid;
    private String status;
    private Integer privateSchool;
    private Integer elevel;

    public OrgInfoData() {
    }

    public String getPorgid() {
        return this.porgid;
    }

    public void setPorgid(String porgid) {
        this.porgid = porgid;
    }

    public String getOrgid() {
        return this.orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return this.orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrgtype() {
        return this.orgtype;
    }

    public void setOrgtype(String orgtype) {
        this.orgtype = orgtype;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPrivateSchool() {
        return this.privateSchool;
    }

    public void setPrivateSchool(Integer privateSchool) {
        this.privateSchool = privateSchool;
    }

    public Integer getElevel() {
        return elevel;
    }

    public void setElevel(Integer elevel) {
        this.elevel = elevel;
    }
}
