package com.sundata.edu.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class ExcelStudentVo extends BaseRowModel {


    @ExcelProperty(value = "学校标示码", index = 0)
    private String schoolCode;

    @ExcelProperty(value = "姓名", index = 1)
    private String studentName;

    @ExcelProperty(value = "性别", index = 2)
    private String gender;

    @ExcelProperty(value = "出生年月", index = 3,format = "yyyy/MM/dd")
    private String birthday;

    @ExcelProperty(value = "出生地行政区划代码", index = 4)
    private String ativeCode;

    @ExcelProperty(value = "籍贯", index = 5)
    private String nativePlace;

    @ExcelProperty(value = "名族", index = 6)
    private String nation;

    @ExcelProperty(value = "国籍/地区", index = 7)
    private String nationality;

    @ExcelProperty(value = "身份证类型", index = 8)
    private String idCardType;

    @ExcelProperty(value = "身份证", index = 9)
    private String idCard;

    @ExcelProperty(value = "港澳台侨外",index = 10)
    private String gat;

    @ExcelProperty(value = "健康状况",index= 11)
    private String healthy;

    @ExcelProperty(value = "政治面貌", index = 12)
    private String polityType;

    @ExcelProperty(value = "户口性质", index = 13)
    private String registeredResidence;

    @ExcelProperty(value = "户口所在地行政区划", index = 14)
    private String registeredResidenceXzq;

    @ExcelProperty(value = "校区号", index = 15)
    private String xqh;

    @ExcelProperty(value = "班号", index = 16)
    private String bh;

    @ExcelProperty(value = "入学年月", index = 17,format = "yyyy/MM/dd")
    private String lxnf;

    @ExcelProperty(value = "入学方式", index = 18)
    private String rxfs;

    @ExcelProperty(value = "就读方式", index = 19)
    private String jdfs;

    @ExcelProperty(value = "现住址", index = 20)
    private String address;

    @ExcelProperty(value = "通信地址", index = 21)
    private String postalAddress;

    @ExcelProperty(value = "家庭地址", index = 22)
    private String homeAddress;

    @ExcelProperty(value = "联系电话", index = 23)
    private String mobile;

    @ExcelProperty(value = "邮政编码", index = 24)
    private String postalCode;

    @ExcelProperty(value = "是否独生子女", index = 25)
    private String onlyChild;

    @ExcelProperty(value = "是否受过学前教育", index = 26)
    private String xqjy;

    @ExcelProperty(value = "是否留守儿童", index = 27)
    private String lset;

    @ExcelProperty(value = "是否农村留守儿童", index = 28)
    private String lscet;


    @ExcelProperty(value = "是否残疾人", index = 29)
    private String cjr;


    @ExcelProperty(value = "是否需要申请资助", index = 30)
    private String sqzz;

    @ExcelProperty(value = "是否享受一补", index = 31)
    private String yb;

    @ExcelProperty(value = "是否孤儿", index = 32)
    private String gr;

    @ExcelProperty(value = "是否烈士或优抚子女", index = 33)
    private String yfzn;

    @ExcelProperty(value = "是否随迁子女", index = 34)
    private String sqzn;

    @ExcelProperty(value = "是否进城务工人员随迁子女", index = 35)
    private String cwsqzn;

    @ExcelProperty(value = "上下学距离", index = 36)
    private String sxx;

    @ExcelProperty(value = "上下学交通方式", index = 37)
    private String sxxfs;

    @ExcelProperty(value = "是否乘坐校车", index = 38)
    private String sfcwxc;

    @ExcelProperty(value = "曾用名", index = 39)
    private String cym;

    @ExcelProperty(value = "身份证有效期", index = 40)
    private String sfzyxq;

    @ExcelProperty(value = "血型", index = 41)
    private String xx;

    @ExcelProperty(value = "特长", index = 42)
    private String tc;

    @ExcelProperty(value = "学籍辅号", index = 43)
    private String xjfh;


    @ExcelProperty(value = "班内学号", index = 44)
    private String blxh;

    @ExcelProperty(value = "学生来源", index = 45)
    private String xsly;

    @ExcelProperty(value = "电子信箱", index = 46)
    private String dzxx;

    @ExcelProperty(value = "主页地址", index = 47)
    private String zydz;

    @ExcelProperty(value = "是否由政府购买学位", index = 48)
    private String zfgmxw;

    @ExcelProperty(value = "成员1姓名", index = 49)
    private String cyxm;

    @ExcelProperty(value = "成员1关系", index = 50)
    private String cygx;

    @ExcelProperty(value = "成员1关系说明", index = 51)
    private String cygxsm;

    @ExcelProperty(value = "成员1现住址", index = 52)
    private String cyzz;

    @ExcelProperty(value = "成员1户口所在地行政区划", index = 53)
    private String cyxzqh;

    @ExcelProperty(value = "成员1联系电话", index = 54)
    private String cydh;

    @ExcelProperty(value = "成员1是否监护人", index = 55)
    private String cyjhr;

    @ExcelProperty(value = "成员1身份证件类型", index = 56)
    private String cysfzlx;

    @ExcelProperty(value = "成员1身份证件号", index = 57)
    private String cysfzh;

    @ExcelProperty(value = "成员1民族", index = 58)
    private String cymz;

    @ExcelProperty(value = "成员1工作单位", index = 59)
    private String cygz;

    @ExcelProperty(value = "成员1职务", index = 60)
    private String cyzw;

    @ExcelProperty(value = "成员2姓名", index = 61)
    private String cyrxm;

    @ExcelProperty(value = "成员2关系", index = 62)
    private String cyrgx;

    @ExcelProperty(value = "成员2关系说明", index = 63)
    private String cyrgxsm;

    @ExcelProperty(value = "成员2现住址", index = 64)
    private String cyrzz;

    @ExcelProperty(value = "成员2户口所在地行政区划", index = 65)
    private String cyrxzq;

    @ExcelProperty(value = "成员2联系电话", index = 66)
    private String cyrdh;

    @ExcelProperty(value = "成员2是否监护人", index = 67)
    private String cyrjhr;

    @ExcelProperty(value = "成员2身份证件类型", index = 68)
    private String cyrsfzlx;

    @ExcelProperty(value = "成员2身份证件号", index = 69)
    private String cyrzjh;

    @ExcelProperty(value = "成员2民族", index = 70)
    private String cyrmz;

    @ExcelProperty(value = "成员2工作单位", index = 71)
    private String cyrgzdw;

    @ExcelProperty(value = "成员2职务", index = 72)
    private String cyrzw;

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAtiveCode() {
        return ativeCode;
    }

    public void setAtiveCode(String ativeCode) {
        this.ativeCode = ativeCode;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getGat() {
        return gat;
    }

    public void setGat(String gat) {
        this.gat = gat;
    }

    public String getHealthy() {
        return healthy;
    }

    public void setHealthy(String healthy) {
        this.healthy = healthy;
    }

    public String getPolityType() {
        return polityType;
    }

    public void setPolityType(String polityType) {
        this.polityType = polityType;
    }

    public String getRegisteredResidence() {
        return registeredResidence;
    }

    public void setRegisteredResidence(String registeredResidence) {
        this.registeredResidence = registeredResidence;
    }

    public String getRegisteredResidenceXzq() {
        return registeredResidenceXzq;
    }

    public void setRegisteredResidenceXzq(String registeredResidenceXzq) {
        this.registeredResidenceXzq = registeredResidenceXzq;
    }

    public String getXqh() {
        return xqh;
    }

    public void setXqh(String xqh) {
        this.xqh = xqh;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getLxnf() {
        return lxnf;
    }

    public void setLxnf(String lxnf) {
        this.lxnf = lxnf;
    }

    public String getRxfs() {
        return rxfs;
    }

    public void setRxfs(String rxfs) {
        this.rxfs = rxfs;
    }

    public String getJdfs() {
        return jdfs;
    }

    public void setJdfs(String jdfs) {
        this.jdfs = jdfs;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getOnlyChild() {
        return onlyChild;
    }

    public void setOnlyChild(String onlyChild) {
        this.onlyChild = onlyChild;
    }

    public String getXqjy() {
        return xqjy;
    }

    public void setXqjy(String xqjy) {
        this.xqjy = xqjy;
    }

    public String getLset() {
        return lset;
    }

    public void setLset(String lset) {
        this.lset = lset;
    }

    public String getLscet() {
        return lscet;
    }

    public void setLscet(String lscet) {
        this.lscet = lscet;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public String getSqzz() {
        return sqzz;
    }

    public void setSqzz(String sqzz) {
        this.sqzz = sqzz;
    }

    public String getYb() {
        return yb;
    }

    public void setYb(String yb) {
        this.yb = yb;
    }

    public String getGr() {
        return gr;
    }

    public void setGr(String gr) {
        this.gr = gr;
    }

    public String getYfzn() {
        return yfzn;
    }

    public void setYfzn(String yfzn) {
        this.yfzn = yfzn;
    }

    public String getSqzn() {
        return sqzn;
    }

    public void setSqzn(String sqzn) {
        this.sqzn = sqzn;
    }

    public String getCwsqzn() {
        return cwsqzn;
    }

    public void setCwsqzn(String cwsqzn) {
        this.cwsqzn = cwsqzn;
    }

    public String getSxx() {
        return sxx;
    }

    public void setSxx(String sxx) {
        this.sxx = sxx;
    }

    public String getSxxfs() {
        return sxxfs;
    }

    public void setSxxfs(String sxxfs) {
        this.sxxfs = sxxfs;
    }

    public String getSfcwxc() {
        return sfcwxc;
    }

    public void setSfcwxc(String sfcwxc) {
        this.sfcwxc = sfcwxc;
    }

    public String getCym() {
        return cym;
    }

    public void setCym(String cym) {
        this.cym = cym;
    }

    public String getSfzyxq() {
        return sfzyxq;
    }

    public void setSfzyxq(String sfzyxq) {
        this.sfzyxq = sfzyxq;
    }

    public String getXx() {
        return xx;
    }

    public void setXx(String xx) {
        this.xx = xx;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getXjfh() {
        return xjfh;
    }

    public void setXjfh(String xjfh) {
        this.xjfh = xjfh;
    }

    public String getBlxh() {
        return blxh;
    }

    public void setBlxh(String blxh) {
        this.blxh = blxh;
    }

    public String getXsly() {
        return xsly;
    }

    public void setXsly(String xsly) {
        this.xsly = xsly;
    }

    public String getDzxx() {
        return dzxx;
    }

    public void setDzxx(String dzxx) {
        this.dzxx = dzxx;
    }

    public String getZydz() {
        return zydz;
    }

    public void setZydz(String zydz) {
        this.zydz = zydz;
    }

    public String getZfgmxw() {
        return zfgmxw;
    }

    public void setZfgmxw(String zfgmxw) {
        this.zfgmxw = zfgmxw;
    }

    public String getCyxm() {
        return cyxm;
    }

    public void setCyxm(String cyxm) {
        this.cyxm = cyxm;
    }

    public String getCygx() {
        return cygx;
    }

    public void setCygx(String cygx) {
        this.cygx = cygx;
    }

    public String getCygxsm() {
        return cygxsm;
    }

    public void setCygxsm(String cygxsm) {
        this.cygxsm = cygxsm;
    }

    public String getCyzz() {
        return cyzz;
    }

    public void setCyzz(String cyzz) {
        this.cyzz = cyzz;
    }

    public String getCyxzqh() {
        return cyxzqh;
    }

    public void setCyxzqh(String cyxzqh) {
        this.cyxzqh = cyxzqh;
    }

    public String getCydh() {
        return cydh;
    }

    public void setCydh(String cydh) {
        this.cydh = cydh;
    }

    public String getCyjhr() {
        return cyjhr;
    }

    public void setCyjhr(String cyjhr) {
        this.cyjhr = cyjhr;
    }

    public String getCysfzlx() {
        return cysfzlx;
    }

    public void setCysfzlx(String cysfzlx) {
        this.cysfzlx = cysfzlx;
    }

    public String getCysfzh() {
        return cysfzh;
    }

    public void setCysfzh(String cysfzh) {
        this.cysfzh = cysfzh;
    }

    public String getCymz() {
        return cymz;
    }

    public void setCymz(String cymz) {
        this.cymz = cymz;
    }

    public String getCygz() {
        return cygz;
    }

    public void setCygz(String cygz) {
        this.cygz = cygz;
    }

    public String getCyzw() {
        return cyzw;
    }

    public void setCyzw(String cyzw) {
        this.cyzw = cyzw;
    }

    public String getCyrxm() {
        return cyrxm;
    }

    public void setCyrxm(String cyrxm) {
        this.cyrxm = cyrxm;
    }

    public String getCyrgx() {
        return cyrgx;
    }

    public void setCyrgx(String cyrgx) {
        this.cyrgx = cyrgx;
    }

    public String getCyrgxsm() {
        return cyrgxsm;
    }

    public void setCyrgxsm(String cyrgxsm) {
        this.cyrgxsm = cyrgxsm;
    }

    public String getCyrzz() {
        return cyrzz;
    }

    public void setCyrzz(String cyrzz) {
        this.cyrzz = cyrzz;
    }

    public String getCyrxzq() {
        return cyrxzq;
    }

    public void setCyrxzq(String cyrxzq) {
        this.cyrxzq = cyrxzq;
    }

    public String getCyrdh() {
        return cyrdh;
    }

    public void setCyrdh(String cyrdh) {
        this.cyrdh = cyrdh;
    }

    public String getCyrjhr() {
        return cyrjhr;
    }

    public void setCyrjhr(String cyrjhr) {
        this.cyrjhr = cyrjhr;
    }

    public String getCyrsfzlx() {
        return cyrsfzlx;
    }

    public void setCyrsfzlx(String cyrsfzlx) {
        this.cyrsfzlx = cyrsfzlx;
    }

    public String getCyrzjh() {
        return cyrzjh;
    }

    public void setCyrzjh(String cyrzjh) {
        this.cyrzjh = cyrzjh;
    }

    public String getCyrmz() {
        return cyrmz;
    }

    public void setCyrmz(String cyrmz) {
        this.cyrmz = cyrmz;
    }

    public String getCyrgzdw() {
        return cyrgzdw;
    }

    public void setCyrgzdw(String cyrgzdw) {
        this.cyrgzdw = cyrgzdw;
    }

    public String getCyrzw() {
        return cyrzw;
    }

    public void setCyrzw(String cyrzw) {
        this.cyrzw = cyrzw;
    }

    @Override
    public String toString() {
        return "ExcelStudentVo{" +
                "schoolCode='" + schoolCode + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", ativeCode='" + ativeCode + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", nation='" + nation + '\'' +
                ", nationality='" + nationality + '\'' +
                ", idCardType='" + idCardType + '\'' +
                ", idCard='" + idCard + '\'' +
                ", gat='" + gat + '\'' +
                ", healthy='" + healthy + '\'' +
                ", polityType='" + polityType + '\'' +
                ", registeredResidence='" + registeredResidence + '\'' +
                ", registeredResidenceXzq='" + registeredResidenceXzq + '\'' +
                ", xqh='" + xqh + '\'' +
                ", bh='" + bh + '\'' +
                ", lxnf='" + lxnf + '\'' +
                ", rxfs='" + rxfs + '\'' +
                ", jdfs='" + jdfs + '\'' +
                ", address='" + address + '\'' +
                ", postalAddress='" + postalAddress + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", mobile='" + mobile + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", onlyChild='" + onlyChild + '\'' +
                ", xqjy='" + xqjy + '\'' +
                ", lset='" + lset + '\'' +
                ", lscet='" + lscet + '\'' +
                ", cjr='" + cjr + '\'' +
                ", sqzz='" + sqzz + '\'' +
                ", yb='" + yb + '\'' +
                ", gr='" + gr + '\'' +
                ", yfzn='" + yfzn + '\'' +
                ", sqzn='" + sqzn + '\'' +
                ", cwsqzn='" + cwsqzn + '\'' +
                ", sxx='" + sxx + '\'' +
                ", sxxfs='" + sxxfs + '\'' +
                ", sfcwxc='" + sfcwxc + '\'' +
                ", cym='" + cym + '\'' +
                ", sfzyxq='" + sfzyxq + '\'' +
                ", xx='" + xx + '\'' +
                ", tc='" + tc + '\'' +
                ", xjfh='" + xjfh + '\'' +
                ", blxh='" + blxh + '\'' +
                ", xsly='" + xsly + '\'' +
                ", dzxx='" + dzxx + '\'' +
                ", zydz='" + zydz + '\'' +
                ", zfgmxw='" + zfgmxw + '\'' +
                ", cyxm='" + cyxm + '\'' +
                ", cygx='" + cygx + '\'' +
                ", cygxsm='" + cygxsm + '\'' +
                ", cyzz='" + cyzz + '\'' +
                ", cyxzqh='" + cyxzqh + '\'' +
                ", cydh='" + cydh + '\'' +
                ", cyjhr='" + cyjhr + '\'' +
                ", cysfzlx='" + cysfzlx + '\'' +
                ", cysfzh='" + cysfzh + '\'' +
                ", cymz='" + cymz + '\'' +
                ", cygz='" + cygz + '\'' +
                ", cyzw='" + cyzw + '\'' +
                ", cyrxm='" + cyrxm + '\'' +
                ", cyrgx='" + cyrgx + '\'' +
                ", cyrgxsm='" + cyrgxsm + '\'' +
                ", cyrzz='" + cyrzz + '\'' +
                ", cyrxzq='" + cyrxzq + '\'' +
                ", cyrdh='" + cyrdh + '\'' +
                ", cyrjhr='" + cyrjhr + '\'' +
                ", cyrsfzlx='" + cyrsfzlx + '\'' +
                ", cyrzjh='" + cyrzjh + '\'' +
                ", cyrmz='" + cyrmz + '\'' +
                ", cyrgzdw='" + cyrgzdw + '\'' +
                ", cyrzw='" + cyrzw + '\'' +
                '}';
    }
}
