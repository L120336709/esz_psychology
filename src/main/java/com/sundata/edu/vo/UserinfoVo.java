package com.sundata.edu.vo;

import java.util.List;

/**
 * 用户表 userinfo
 *
 * @author 侯鹏
 * @date 2019-04-17 10:25:26
 */
public class UserinfoVo extends BaseVo {
    /**
     *
     */
    private String userId;
    /**
     *
     */
    private String realName;
    /**
     *
     */
    private String orgId;

    private String orgName;
    /**
     *
     */
    private Integer identity;
    /**
     * 状态 1=正常 0=禁用
     */
    private Integer status;


    private String token;

    private String classId;

    private String gradeId;

    private String gradeName;

    private String className;

    /**
     * 是否包含子集(1包含子集 2不包含子集，只有自己)
     */
    private Integer includeSubset;
//    private String adminUrl;

//    /** 用户id集合,查询使用 */
//    private List<String> userIdList;

    private String userNo;

    private String mobile;

    /**
     * 学校Ids，查询用
     */
    private List<String> orgIds;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
//
//    public String getAdminUrl() {
//        return adminUrl;
//    }
//
//    public void setAdminUrl(String adminUrl) {
//        this.adminUrl = adminUrl;
//    }

//    public List<String> getUserIdList() {
//        return userIdList;
//    }
//
//    public void setUserIdList(List<String> userIdList) {
//        this.userIdList = userIdList;
//    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getIncludeSubset() {
        return includeSubset;
    }

    public void setIncludeSubset(Integer includeSubset) {
        this.includeSubset = includeSubset;
    }


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<String> orgIds) {
        this.orgIds = orgIds;
    }

    @Override
    public String toString() {
        return "UserinfoVo{" +
                "userId='" + userId + '\'' +
                ", realName='" + realName + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", identity=" + identity +
                ", status=" + status +
                ", token='" + token + '\'' +
                ", includeSubset=" + includeSubset +
//                ", adminUrl='" + adminUrl + '\'' +
                '}';
    }
}
