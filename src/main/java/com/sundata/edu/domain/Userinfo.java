package com.sundata.edu.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 用户表 userinfo
 *
 * @author whj
 * @date 2019-04-17 10:25:26
 */
@Table(name = "`userinfo`")
public class Userinfo implements Serializable {
    /**
     *
     */
    @Id
    @Column(name = "`user_id`")
    private String userId;
    /**
     *
     */
    @Column(name = "`real_name`")
    private String realName;
    /**
     *
     */
    @Column(name = "`org_id`")
    private String orgId;

    @Column(name = "`grade_id`")
    private String gradeId;

    @Column(name = "`class_id`")
    private String classId;
    /**
     *
     */
    @Column(name = "`identity`")
    private Integer identity;
    /**
     * 状态 1=正常 0=禁用
     */
    @Column(name = "`status`")
    private Integer status;

    @Column(name="`id_card`")
    private String idCard;

    @Column(name="`user_no`")
    private String userNo;

    @Column(name="`nativePlace`")
    private String nativePlace;

    @Column(name="`national`")
    private String national;

    @Column(name="`gender`")
    private String gender;

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        return "Userinfo{" +
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
