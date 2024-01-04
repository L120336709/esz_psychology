package com.sundata.edu.vo;
import java.util.List;
/**
 * 表 studentinfo
 *
 * @author 周天宇
 * @date 2020-01-07 10:58:42
 */
public class StudentinfoVo extends BaseVo {

	/**
	 * id-value逗号拼接的形式
	 */
	private String extAttrs;

	/**
	 * 拓展字段内容
	 */
	private List<List<SelectVo>> extList;

    /**
     * 用户uid
     */
	private String userId;

	/**
	 * 姓名
	 *
	 */
	private String studentName;

	private String gradeName;

	private String className;

	private String orgName;

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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
     * 学校编码
     */
	private String schoolcode;
	/**
	 * 作业编号
	 */
	private String jobnumber;

	/**
	 * 班级id
	 */
	private String gradeCode;

	/**
	 * 班级id
	 */
	private String classCode;

	@Override
	public String toString() {
		return "StudentinfoVo{" +
				"extAttrs='" + extAttrs + '\'' +
				", extList=" + extList +
				", userId='" + userId + '\'' +
				", studentName='" + studentName + '\'' +
				", gradeName='" + gradeName + '\'' +
				", className='" + className + '\'' +
				", orgName='" + orgName + '\'' +
				", schoolcode='" + schoolcode + '\'' +
				", jobnumber='" + jobnumber + '\'' +
				", gradeCode='" + gradeCode + '\'' +
				", classCode='" + classCode + '\'' +
				", gender='" + gender + '\'' +
				", birthday='" + birthday + '\'' +
				", ativecode='" + ativecode + '\'' +
				", nativeplace='" + nativeplace + '\'' +
				", nation='" + nation + '\'' +
				", nations='" + nations + '\'' +
				", nationality='" + nationality + '\'' +
				", idcardtype='" + idcardtype + '\'' +
				", idcard='" + idcard + '\'' +
				", gat='" + gat + '\'' +
				", healthy='" + healthy + '\'' +
				", politytype='" + politytype + '\'' +
				", registeredresidence='" + registeredresidence + '\'' +
				", registeredresidencexzq='" + registeredresidencexzq + '\'' +
				", xqh='" + xqh + '\'' +
				", bh='" + bh + '\'' +
				", lxnf='" + lxnf + '\'' +
				", rxfs='" + rxfs + '\'' +
				", jdfs='" + jdfs + '\'' +
				", address='" + address + '\'' +
				", postaladdress='" + postaladdress + '\'' +
				", homeaddress='" + homeaddress + '\'' +
				", mobile='" + mobile + '\'' +
				", postalcode='" + postalcode + '\'' +
				", onlychild='" + onlychild + '\'' +
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

	public String getJobnumber() {
		return jobnumber;
	}

	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	/**
     * 性别
     */
	private String gender;
    /**
     * 出生年月
     */
	private String birthday;
    /**
     * 出生地行政区划代码
     */
	private String ativecode;
    /**
     * 籍贯
     */
	private String nativeplace;
    /**
     * 名族
     */
	private String nation;


	/**后面怎加的读取 不做修改*/
	private String nations;
    /**
     * 国籍/地区
     */
	private String nationality;
    /**
     * 身份证类型
     */
	private String idcardtype;
    /**
     * 身份证
     */
	private String idcard;
    /**
     * 港澳台侨外
     */
	private String gat;
    /**
     * 健康状况
     */
	private String healthy;
    /**
     * 政治面貌
     */
	private String politytype;
    /**
     * 户口性质
     */
	private String registeredresidence;
    /**
     * 户口所在地行政区划
     */
	private String registeredresidencexzq;
    /**
     * 校区号
     */
	private String xqh;
    /**
     * 班号
     */
	private String bh;
    /**
     * 入学年月
     */
	private String lxnf;
    /**
     * 入学方式
     */
	private String rxfs;
    /**
     * 就读方式
     */
	private String jdfs;
    /**
     * 现住址
     */
	private String address;
    /**
     * 通信地址
     */
	private String postaladdress;
    /**
     * 家庭地址
     */
	private String homeaddress;
    /**
     * 联系电话
     */
	private String mobile;
    /**
     * 邮政编码
     */
	private String postalcode;
    /**
     * 是否独生子女
     */
	private String onlychild;
    /**
     * 是否受过学前教育
     */
	private String xqjy;
    /**
     * 是否留守儿童
     */
	private String lset;
    /**
     * 是否农村留守儿童
     */
	private String lscet;
    /**
     * 是否残疾人
     */
	private String cjr;
    /**
     * 是否需要申请资助
     */
	private String sqzz;
    /**
     * 是否享受一补
     */
	private String yb;
    /**
     * 是否孤儿
     */
	private String gr;
    /**
     * 是否烈士或优抚子女
     */
	private String yfzn;
    /**
     * 是否随迁子女
     */
	private String sqzn;
    /**
     * 是否进城务工人员随迁子女
     */
	private String cwsqzn;
    /**
     * 上下学距离
     */
	private String sxx;
    /**
     * 上下学交通方式
     */
	private String sxxfs;
    /**
     * 是否乘坐校车
     */
	private String sfcwxc;
    /**
     * 曾用名
     */
	private String cym;
    /**
     * 身份证有效期
     */
	private String sfzyxq;
    /**
     * 血型
     */
	private String xx;
    /**
     * 特长
     */
	private String tc;
    /**
     * 学籍辅号
     */
	private String xjfh;
    /**
     * 班内学号
     */
	private String blxh;
    /**
     * 学生来源
     */
	private String xsly;
    /**
     * 电子信箱
     */
	private String dzxx;
    /**
     * 主页地址
     */
	private String zydz;
    /**
     * 是否由政府购买学位
     */
	private String zfgmxw;
    /**
     * 成员1姓名
     */
	private String cyxm;
    /**
     * 成员1关系
     */
	private String cygx;
    /**
     * 成员1关系说明
     */
	private String cygxsm;
    /**
     * 成员1现住址
     */
	private String cyzz;
    /**
     * 成员1户口所在地行政区划
     */
	private String cyxzqh;
    /**
     * 成员1联系电话
     */
	private String cydh;
    /**
     * 成员1是否监护人
     */
	private String cyjhr;
    /**
     * 成员1身份证件类型
     */
	private String cysfzlx;
    /**
     * 成员1身份证件号
     */
	private String cysfzh;
    /**
     * 成员1民族
     */
	private String cymz;
    /**
     * 成员1工作单位
     */
	private String cygz;
    /**
     * 成员1职务
     */
	private String cyzw;
    /**
     * 成员2姓名
     */
	private String cyrxm;
    /**
     * 成员2关系
     */
	private String cyrgx;
    /**
     * 成员2关系说明
     */
	private String cyrgxsm;
    /**
     * 成员2现住址
     */
	private String cyrzz;
    /**
     * 成员2户口所在地行政区划
     */
	private String cyrxzq;
    /**
     * 成员2联系电话
     */
	private String cyrdh;
    /**
     * 成员2是否监护人
     */
	private String cyrjhr;
    /**
     * 成员2身份证件类型
     */
	private String cyrsfzlx;
    /**
     * 成员2身份证件号
     */
	private String cyrzjh;
    /**
     * 成员2民族
     */
	private String cyrmz;
    /**
     * 成员2工作单位
     */
	private String cyrgzdw;
    /**
     * 成员2职务
     */
	private String cyrzw;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setSchoolcode(String schoolcode) {
		this.schoolcode = schoolcode;
	}

	public String getSchoolcode()
	{
		return schoolcode;
	}



	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthday()
	{
		return birthday;
	}

	public void setAtivecode(String ativecode) {
		this.ativecode = ativecode;
	}

	public String getAtivecode()
	{
		return ativecode;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}

	public String getNativeplace()
	{
		return nativeplace;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNation()
	{
		return nation;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNationality()
	{
		return nationality;
	}

	public void setIdcardtype(String idcardtype) {
		this.idcardtype = idcardtype;
	}

	public String getIdcardtype()
	{
		return idcardtype;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getIdcard()
	{
		return idcard;
	}

	public void setGat(String gat) {
		this.gat = gat;
	}

	public String getGat()
	{
		return gat;
	}

	public void setHealthy(String healthy) {
		this.healthy = healthy;
	}

	public String getHealthy()
	{
		return healthy;
	}

	public void setPolitytype(String politytype) {
		this.politytype = politytype;
	}

	public String getPolitytype()
	{
		return politytype;
	}

	public void setRegisteredresidence(String registeredresidence) {
		this.registeredresidence = registeredresidence;
	}

	public String getRegisteredresidence()
	{
		return registeredresidence;
	}

	public void setRegisteredresidencexzq(String registeredresidencexzq) {
		this.registeredresidencexzq = registeredresidencexzq;
	}

	public String getRegisteredresidencexzq()
	{
		return registeredresidencexzq;
	}

	public void setXqh(String xqh) {
		this.xqh = xqh;
	}

	public String getXqh()
	{
		return xqh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getBh()
	{
		return bh;
	}

	public void setLxnf(String lxnf) {
		this.lxnf = lxnf;
	}

	public String getLxnf()
	{
		return lxnf;
	}

	public void setRxfs(String rxfs) {
		this.rxfs = rxfs;
	}

	public String getRxfs()
	{
		return rxfs;
	}

	public void setJdfs(String jdfs) {
		this.jdfs = jdfs;
	}

	public String getJdfs()
	{
		return jdfs;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress()
	{
		return address;
	}

	public void setPostaladdress(String postaladdress) {
		this.postaladdress = postaladdress;
	}

	public String getPostaladdress()
	{
		return postaladdress;
	}

	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}

	public String getHomeaddress()
	{
		return homeaddress;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getPostalcode()
	{
		return postalcode;
	}

	public void setOnlychild(String onlychild) {
		this.onlychild = onlychild;
	}

	public String getOnlychild()
	{
		return onlychild;
	}

	public void setXqjy(String xqjy) {
		this.xqjy = xqjy;
	}

	public String getXqjy()
	{
		return xqjy;
	}

	public void setLset(String lset) {
		this.lset = lset;
	}

	public String getLset()
	{
		return lset;
	}

	public void setLscet(String lscet) {
		this.lscet = lscet;
	}

	public String getLscet()
	{
		return lscet;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getCjr()
	{
		return cjr;
	}

	public void setSqzz(String sqzz) {
		this.sqzz = sqzz;
	}

	public String getSqzz()
	{
		return sqzz;
	}

	public void setYb(String yb) {
		this.yb = yb;
	}

	public String getYb()
	{
		return yb;
	}

	public void setGr(String gr) {
		this.gr = gr;
	}

	public String getGr()
	{
		return gr;
	}

	public void setYfzn(String yfzn) {
		this.yfzn = yfzn;
	}

	public String getYfzn()
	{
		return yfzn;
	}

	public void setSqzn(String sqzn) {
		this.sqzn = sqzn;
	}

	public String getSqzn()
	{
		return sqzn;
	}

	public void setCwsqzn(String cwsqzn) {
		this.cwsqzn = cwsqzn;
	}

	public String getCwsqzn()
	{
		return cwsqzn;
	}

	public void setSxx(String sxx) {
		this.sxx = sxx;
	}

	public String getSxx()
	{
		return sxx;
	}

	public void setSxxfs(String sxxfs) {
		this.sxxfs = sxxfs;
	}

	public String getSxxfs()
	{
		return sxxfs;
	}

	public void setSfcwxc(String sfcwxc) {
		this.sfcwxc = sfcwxc;
	}

	public String getSfcwxc()
	{
		return sfcwxc;
	}

	public void setCym(String cym) {
		this.cym = cym;
	}

	public String getCym()
	{
		return cym;
	}

	public void setSfzyxq(String sfzyxq) {
		this.sfzyxq = sfzyxq;
	}

	public String getSfzyxq()
	{
		return sfzyxq;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}

	public String getXx()
	{
		return xx;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public String getTc()
	{
		return tc;
	}

	public void setXjfh(String xjfh) {
		this.xjfh = xjfh;
	}

	public String getXjfh()
	{
		return xjfh;
	}

	public void setBlxh(String blxh) {
		this.blxh = blxh;
	}

	public String getBlxh()
	{
		return blxh;
	}

	public void setXsly(String xsly) {
		this.xsly = xsly;
	}

	public String getXsly()
	{
		return xsly;
	}

	public void setDzxx(String dzxx) {
		this.dzxx = dzxx;
	}

	public String getDzxx()
	{
		return dzxx;
	}

	public void setZydz(String zydz) {
		this.zydz = zydz;
	}

	public String getZydz()
	{
		return zydz;
	}

	public void setZfgmxw(String zfgmxw) {
		this.zfgmxw = zfgmxw;
	}

	public String getZfgmxw()
	{
		return zfgmxw;
	}

	public void setCyxm(String cyxm) {
		this.cyxm = cyxm;
	}

	public String getCyxm()
	{
		return cyxm;
	}

	public void setCygx(String cygx) {
		this.cygx = cygx;
	}

	public String getCygx()
	{
		return cygx;
	}

	public void setCygxsm(String cygxsm) {
		this.cygxsm = cygxsm;
	}

	public String getCygxsm()
	{
		return cygxsm;
	}

	public void setCyzz(String cyzz) {
		this.cyzz = cyzz;
	}

	public String getCyzz()
	{
		return cyzz;
	}

	public void setCyxzqh(String cyxzqh) {
		this.cyxzqh = cyxzqh;
	}

	public String getCyxzqh()
	{
		return cyxzqh;
	}

	public void setCydh(String cydh) {
		this.cydh = cydh;
	}

	public String getCydh()
	{
		return cydh;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setCyjhr(String cyjhr) {
		this.cyjhr = cyjhr;
	}

	public String getCyjhr()
	{
		return cyjhr;
	}

	public void setCysfzlx(String cysfzlx) {
		this.cysfzlx = cysfzlx;
	}

	public String getCysfzlx()
	{
		return cysfzlx;
	}

	public void setCysfzh(String cysfzh) {
		this.cysfzh = cysfzh;
	}

	public String getCysfzh()
	{
		return cysfzh;
	}

	public void setCymz(String cymz) {
		this.cymz = cymz;
	}

	public String getCymz()
	{
		return cymz;
	}

	public void setCygz(String cygz) {
		this.cygz = cygz;
	}

	public String getCygz()
	{
		return cygz;
	}

	public void setCyzw(String cyzw) {
		this.cyzw = cyzw;
	}

	public String getCyzw()
	{
		return cyzw;
	}

	public void setCyrxm(String cyrxm) {
		this.cyrxm = cyrxm;
	}

	public String getCyrxm()
	{
		return cyrxm;
	}

	public void setCyrgx(String cyrgx) {
		this.cyrgx = cyrgx;
	}

	public String getCyrgx()
	{
		return cyrgx;
	}

	public void setCyrgxsm(String cyrgxsm) {
		this.cyrgxsm = cyrgxsm;
	}

	public String getCyrgxsm()
	{
		return cyrgxsm;
	}

	public void setCyrzz(String cyrzz) {
		this.cyrzz = cyrzz;
	}

	public String getCyrzz()
	{
		return cyrzz;
	}

	public void setCyrxzq(String cyrxzq) {
		this.cyrxzq = cyrxzq;
	}

	public String getCyrxzq()
	{
		return cyrxzq;
	}

	public void setCyrdh(String cyrdh) {
		this.cyrdh = cyrdh;
	}

	public String getCyrdh()
	{
		return cyrdh;
	}

	public void setCyrjhr(String cyrjhr) {
		this.cyrjhr = cyrjhr;
	}

	public String getCyrjhr()
	{
		return cyrjhr;
	}

	public void setCyrsfzlx(String cyrsfzlx) {
		this.cyrsfzlx = cyrsfzlx;
	}

	public String getCyrsfzlx()
	{
		return cyrsfzlx;
	}

	public void setCyrzjh(String cyrzjh) {
		this.cyrzjh = cyrzjh;
	}

	public String getCyrzjh()
	{
		return cyrzjh;
	}

	public void setCyrmz(String cyrmz) {
		this.cyrmz = cyrmz;
	}

	public String getCyrmz()
	{
		return cyrmz;
	}

	public void setCyrgzdw(String cyrgzdw) {
		this.cyrgzdw = cyrgzdw;
	}

	public String getCyrgzdw()
	{
		return cyrgzdw;
	}

	public void setCyrzw(String cyrzw) {
		this.cyrzw = cyrzw;
	}

	public String getCyrzw()
	{
		return cyrzw;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getNations() {
		return nations;
	}

	public void setNations(String nations) {
		this.nations = nations;
	}

	public List<List<SelectVo>> getExtList() {
		return extList;
	}

	public void setExtList(List<List<SelectVo>> extList) {
		this.extList = extList;
	}

	public String getExtAttrs() {
		return extAttrs;
	}

	public void setExtAttrs(String extAttrs) {
		this.extAttrs = extAttrs;
	}

}
