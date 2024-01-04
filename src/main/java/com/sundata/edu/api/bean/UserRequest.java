package com.sundata.edu.api.bean;

public class UserRequest {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String realName;

    /**
     * 用户机构
     */
    private String orgId;

    private String gradeId;

    private String classId;

    /**
     * 用户身份
     */
    private Integer identity;

    /**
     * 状态 1=正常 0=禁用
     */
    private Integer status;

    private String idCard;

    private String userNo;

    private String gender;

    private String national;

    private String nativePlace;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "userId='" + userId + '\'' +
                ", realName='" + realName + '\'' +
                ", orgId='" + orgId + '\'' +
                ", gradeId='" + gradeId + '\'' +
                ", classId='" + classId + '\'' +
                ", identity=" + identity +
                ", status=" + status +
                '}';
    }
}
