package com.sundata.edu.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 表 studentinfo
 *
 * @author 周天宇
 * @date 2020-01-07 18:42:40
 */
@Table(name = "`studentinfo`")
public class Studentinfo {
	/**
	 * 用户uid
	 */
	@Id
	@Column(name = "`user_id`")
	private String userId;

	@Column(name = "`schoolCode`")
	private String schoolcode;
	/**
	 * 作业编号
	 */
	@Column(name = "`jobnumber`")
	private String jobnumber;
	/**
	 * 班级id
	 */
	@Column(name = "`gradeCode`")
	private String gradeCode;

	/**
	 * 班级id
	 */
	@Column(name = "`classCode`")
	private String classCode;

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

	public String getStudentName() {
		return studentName;
	}

	@Override
	public String toString() {
		return "Studentinfo{" +
				"userId='" + userId + '\'' +
				", schoolcode='" + schoolcode + '\'' +
				", jobnumber='" + jobnumber + '\'' +
				", gradeCode='" + gradeCode + '\'' +
				", classCode='" + classCode + '\'' +
				", studentName='" + studentName + '\'' +
				", gender='" + gender + '\'' +
				", birthday='" + birthday + '\'' +
				", ativecode='" + ativecode + '\'' +
				", nativeplace='" + nativeplace + '\'' +
				", nation='" + nation + '\'' +
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

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Column(name = "`studentName`")
	private String studentName;

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	/**
	 * 性别
	 */
	@Column(name = "`gender`")
	private String gender;
	/**
	 * 出生年月
	 */
	@Column(name = "`birthday`")
	private String birthday;
	/**
	 * 出生地行政区划代码
	 */
	@Column(name = "`ativeCode`")
	private String ativecode;
	/**
	 * 籍贯
	 */
	@Column(name = "`nativePlace`")
	private String nativeplace;
	/**
	 * 名族
	 */
	@Column(name = "`nation`")
	private String nation;
	/**
	 * 国籍/地区
	 */
	@Column(name = "`nationality`")
	private String nationality;
	/**
	 * 身份证类型
	 */
	@Column(name = "`idCardType`")
	private String idcardtype;
	/**
	 * 身份证
	 */
	@Column(name = "`idCard`")
	private String idcard;
	/**
	 * 港澳台侨外
	 */
	@Column(name = "`gat`")
	private String gat;
	/**
	 * 健康状况
	 */
	@Column(name = "`healthy`")
	private String healthy;
	/**
	 * 政治面貌
	 */
	@Column(name = "`polityType`")
	private String politytype;
	/**
	 * 户口性质
	 */
	@Column(name = "`registeredResidence`")
	private String registeredresidence;
	/**
	 * 户口所在地行政区划
	 */
	@Column(name = "`registeredResidenceXzq`")
	private String registeredresidencexzq;
	/**
	 * 校区号
	 */
	@Column(name = "`xqh`")
	private String xqh;
	/**
	 * 班号
	 */
	@Column(name = "`bh`")
	private String bh;
	/**
	 * 入学年月
	 */
	@Column(name = "`lxnf`")
	private String lxnf;
	/**
	 * 入学方式
	 */
	@Column(name = "`rxfs`")
	private String rxfs;
	/**
	 * 就读方式
	 */
	@Column(name = "`jdfs`")
	private String jdfs;
	/**
	 * 现住址
	 */
	@Column(name = "`address`")
	private String address;
	/**
	 * 通信地址
	 */
	@Column(name = "`postalAddress`")
	private String postaladdress;
	/**
	 * 家庭地址
	 */
	@Column(name = "`homeAddress`")
	private String homeaddress;
	/**
	 * 联系电话
	 */
	@Column(name = "`mobile`")
	private String mobile;
	/**
	 * 邮政编码
	 */
	@Column(name = "`postalCode`")
	private String postalcode;
	/**
	 * 是否独生子女
	 */
	@Column(name = "`onlyChild`")
	private String onlychild;
	/**
	 * 是否受过学前教育
	 */
	@Column(name = "`xqjy`")
	private String xqjy;
	/**
	 * 是否留守儿童
	 */
	@Column(name = "`lset`")
	private String lset;
	/**
	 * 是否农村留守儿童
	 */
	@Column(name = "`lscet`")
	private String lscet;
	/**
	 * 是否残疾人
	 */
	@Column(name = "`cjr`")
	private String cjr;
	/**
	 * 是否需要申请资助
	 */
	@Column(name = "`sqzz`")
	private String sqzz;
	/**
	 * 是否享受一补
	 */
	@Column(name = "`yb`")
	private String yb;
	/**
	 * 是否孤儿
	 */
	@Column(name = "`gr`")
	private String gr;
	/**
	 * 是否烈士或优抚子女
	 */
	@Column(name = "`yfzn`")
	private String yfzn;
	/**
	 * 是否随迁子女
	 */
	@Column(name = "`sqzn`")
	private String sqzn;
	/**
	 * 是否进城务工人员随迁子女
	 */
	@Column(name = "`cwsqzn`")
	private String cwsqzn;
	/**
	 * 上下学距离
	 */
	@Column(name = "`sxx`")
	private String sxx;
	/**
	 * 上下学交通方式
	 */
	@Column(name = "`sxxfs`")
	private String sxxfs;
	/**
	 * 是否乘坐校车
	 */
	@Column(name = "`sfcwxc`")
	private String sfcwxc;
	/**
	 * 曾用名
	 */
	@Column(name = "`cym`")
	private String cym;
	/**
	 * 身份证有效期
	 */
	@Column(name = "`sfzyxq`")
	private String sfzyxq;
	/**
	 * 血型
	 */
	@Column(name = "`xx`")
	private String xx;
	/**
	 * 特长
	 */
	@Column(name = "`tc`")
	private String tc;
	/**
	 * 学籍辅号
	 */
	@Column(name = "`xjfh`")
	private String xjfh;
	/**
	 * 班内学号
	 */
	@Column(name = "`blxh`")
	private String blxh;
	/**
	 * 学生来源
	 */
	@Column(name = "`xsly`")
	private String xsly;
	/**
	 * 电子信箱
	 */
	@Column(name = "`dzxx`")
	private String dzxx;
	/**
	 * 主页地址
	 */
	@Column(name = "`zydz`")
	private String zydz;
	/**
	 * 是否由政府购买学位
	 */
	@Column(name = "`zfgmxw`")
	private String zfgmxw;
	/**
	 * 成员1姓名
	 */
	@Column(name = "`cyxm`")
	private String cyxm;
	/**
	 * 成员1关系
	 */
	@Column(name = "`cygx`")
	private String cygx;
	/**
	 * 成员1关系说明
	 */
	@Column(name = "`cygxsm`")
	private String cygxsm;
	/**
	 * 成员1现住址
	 */
	@Column(name = "`cyzz`")
	private String cyzz;
	/**
	 * 成员1户口所在地行政区划
	 */
	@Column(name = "`cyxzqh`")
	private String cyxzqh;
	/**
	 * 成员1联系电话
	 */
	@Column(name = "`cydh`")
	private String cydh;
	/**
	 * 成员1是否监护人
	 */
	@Column(name = "`cyjhr`")
	private String cyjhr;
	/**
	 * 成员1身份证件类型
	 */
	@Column(name = "`cysfzlx`")
	private String cysfzlx;
	/**
	 * 成员1身份证件号
	 */
	@Column(name = "`cysfzh`")
	private String cysfzh;
	/**
	 * 成员1民族
	 */
	@Column(name = "`cymz`")
	private String cymz;
	/**
	 * 成员1工作单位
	 */
	@Column(name = "`cygz`")
	private String cygz;
	/**
	 * 成员1职务
	 */
	@Column(name = "`cyzw`")
	private String cyzw;
	/**
	 * 成员2姓名
	 */
	@Column(name = "`cyrxm`")
	private String cyrxm;
	/**
	 * 成员2关系
	 */
	@Column(name = "`cyrgx`")
	private String cyrgx;
	/**
	 * 成员2关系说明
	 */
	@Column(name = "`cyrgxsm`")
	private String cyrgxsm;
	/**
	 * 成员2现住址
	 */
	@Column(name = "`cyrzz`")
	private String cyrzz;
	/**
	 * 成员2户口所在地行政区划
	 */
	@Column(name = "`cyrxzq`")
	private String cyrxzq;
	/**
	 * 成员2联系电话
	 */
	@Column(name = "`cyrdh`")
	private String cyrdh;
	/**
	 * 成员2是否监护人
	 */
	@Column(name = "`cyrjhr`")
	private String cyrjhr;
	/**
	 * 成员2身份证件类型
	 */
	@Column(name = "`cyrsfzlx`")
	private String cyrsfzlx;
	/**
	 * 成员2身份证件号
	 */
	@Column(name = "`cyrzjh`")
	private String cyrzjh;
	/**
	 * 成员2民族
	 */
	@Column(name = "`cyrmz`")
	private String cyrmz;
	/**
	 * 成员2工作单位
	 */
	@Column(name = "`cyrgzdw`")
	private String cyrgzdw;
	/**
	 * 成员2职务
	 */
	@Column(name = "`cyrzw`")
	private String cyrzw;

	public Studentinfo() {
	}

	public Studentinfo(String userId, String gender, String birthday, String ativecode, String nativeplace, String nation, String nationality, String idcardtype, String idcard, String healthy, String politytype, String registeredresidence, String registeredresidencexzq,
					   String address, String postaladdress, String homeaddress, String mobile, String postalcode, String sfzyxq, String xx) {
		this.userId = userId;
		this.gender = gender;
		this.birthday = birthday;
		this.ativecode = ativecode;
		this.nativeplace = nativeplace;
		this.nation = nation;
		this.nationality = nationality;
		this.idcardtype = idcardtype;
		this.idcard = idcard;
		this.healthy = healthy;
		this.politytype = politytype;
		this.registeredresidence = registeredresidence;
		this.registeredresidencexzq = registeredresidencexzq;
		this.address = address;
		this.postaladdress = postaladdress;
		this.homeaddress = homeaddress;
		this.mobile = mobile;
		this.postalcode = postalcode;
		this.sfzyxq = sfzyxq;
		this.xx = xx;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setSchoolcode(String schoolcode) {
		this.schoolcode = schoolcode;
	}

	public String getSchoolcode() {
		return schoolcode;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setAtivecode(String ativecode) {
		this.ativecode = ativecode;
	}

	public String getAtivecode() {
		return ativecode;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}

	public String getNativeplace() {
		return nativeplace;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNation() {
		return nation;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNationality() {
		return nationality;
	}

	public void setIdcardtype(String idcardtype) {
		this.idcardtype = idcardtype;
	}

	public String getIdcardtype() {
		return idcardtype;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setGat(String gat) {
		this.gat = gat;
	}

	public String getGat() {
		return gat;
	}

	public void setHealthy(String healthy) {
		this.healthy = healthy;
	}

	public String getHealthy() {
		return healthy;
	}

	public void setPolitytype(String politytype) {
		this.politytype = politytype;
	}

	public String getPolitytype() {
		return politytype;
	}

	public void setRegisteredresidence(String registeredresidence) {
		this.registeredresidence = registeredresidence;
	}

	public String getRegisteredresidence() {
		return registeredresidence;
	}

	public void setRegisteredresidencexzq(String registeredresidencexzq) {
		this.registeredresidencexzq = registeredresidencexzq;
	}

	public String getRegisteredresidencexzq() {
		return registeredresidencexzq;
	}

	public void setXqh(String xqh) {
		this.xqh = xqh;
	}

	public String getXqh() {
		return xqh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getBh() {
		return bh;
	}

	public void setLxnf(String lxnf) {
		this.lxnf = lxnf;
	}

	public String getLxnf() {
		return lxnf;
	}

	public void setRxfs(String rxfs) {
		this.rxfs = rxfs;
	}

	public String getRxfs() {
		return rxfs;
	}

	public void setJdfs(String jdfs) {
		this.jdfs = jdfs;
	}

	public String getJdfs() {
		return jdfs;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setPostaladdress(String postaladdress) {
		this.postaladdress = postaladdress;
	}

	public String getPostaladdress() {
		return postaladdress;
	}

	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}

	public String getHomeaddress() {
		return homeaddress;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setOnlychild(String onlychild) {
		this.onlychild = onlychild;
	}

	public String getOnlychild() {
		return onlychild;
	}

	public void setXqjy(String xqjy) {
		this.xqjy = xqjy;
	}

	public String getXqjy() {
		return xqjy;
	}

	public void setLset(String lset) {
		this.lset = lset;
	}

	public String getLset() {
		return lset;
	}

	public void setLscet(String lscet) {
		this.lscet = lscet;
	}

	public String getLscet() {
		return lscet;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getCjr() {
		return cjr;
	}

	public void setSqzz(String sqzz) {
		this.sqzz = sqzz;
	}

	public String getSqzz() {
		return sqzz;
	}

	public void setYb(String yb) {
		this.yb = yb;
	}

	public String getYb() {
		return yb;
	}

	public void setGr(String gr) {
		this.gr = gr;
	}

	public String getGr() {
		return gr;
	}

	public void setYfzn(String yfzn) {
		this.yfzn = yfzn;
	}

	public String getYfzn() {
		return yfzn;
	}

	public void setSqzn(String sqzn) {
		this.sqzn = sqzn;
	}

	public String getSqzn() {
		return sqzn;
	}

	public void setCwsqzn(String cwsqzn) {
		this.cwsqzn = cwsqzn;
	}

	public String getCwsqzn() {
		return cwsqzn;
	}

	public void setSxx(String sxx) {
		this.sxx = sxx;
	}

	public String getSxx() {
		return sxx;
	}

	public void setSxxfs(String sxxfs) {
		this.sxxfs = sxxfs;
	}

	public String getSxxfs() {
		return sxxfs;
	}

	public void setSfcwxc(String sfcwxc) {
		this.sfcwxc = sfcwxc;
	}

	public String getSfcwxc() {
		return sfcwxc;
	}

	public void setCym(String cym) {
		this.cym = cym;
	}

	public String getCym() {
		return cym;
	}

	public void setSfzyxq(String sfzyxq) {
		this.sfzyxq = sfzyxq;
	}

	public String getSfzyxq() {
		return sfzyxq;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}

	public String getXx() {
		return xx;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public String getTc() {
		return tc;
	}

	public void setXjfh(String xjfh) {
		this.xjfh = xjfh;
	}

	public String getXjfh() {
		return xjfh;
	}

	public void setBlxh(String blxh) {
		this.blxh = blxh;
	}

	public String getBlxh() {
		return blxh;
	}

	public void setXsly(String xsly) {
		this.xsly = xsly;
	}

	public String getXsly() {
		return xsly;
	}

	public void setDzxx(String dzxx) {
		this.dzxx = dzxx;
	}

	public String getDzxx() {
		return dzxx;
	}

	public void setZydz(String zydz) {
		this.zydz = zydz;
	}

	public String getZydz() {
		return zydz;
	}

	public void setZfgmxw(String zfgmxw) {
		this.zfgmxw = zfgmxw;
	}

	public String getZfgmxw() {
		return zfgmxw;
	}

	public void setCyxm(String cyxm) {
		this.cyxm = cyxm;
	}

	public String getCyxm() {
		return cyxm;
	}

	public void setCygx(String cygx) {
		this.cygx = cygx;
	}

	public String getCygx() {
		return cygx;
	}

	public void setCygxsm(String cygxsm) {
		this.cygxsm = cygxsm;
	}

	public String getCygxsm() {
		return cygxsm;
	}

	public void setCyzz(String cyzz) {
		this.cyzz = cyzz;
	}

	public String getCyzz() {
		return cyzz;
	}

	public void setCyxzqh(String cyxzqh) {
		this.cyxzqh = cyxzqh;
	}

	public String getCyxzqh() {
		return cyxzqh;
	}

	public void setCydh(String cydh) {
		this.cydh = cydh;
	}

	public String getCydh() {
		return cydh;
	}

	public void setCyjhr(String cyjhr) {
		this.cyjhr = cyjhr;
	}

	public String getCyjhr() {
		return cyjhr;
	}

	public void setCysfzlx(String cysfzlx) {
		this.cysfzlx = cysfzlx;
	}

	public String getCysfzlx() {
		return cysfzlx;
	}

	public void setCysfzh(String cysfzh) {
		this.cysfzh = cysfzh;
	}

	public String getCysfzh() {
		return cysfzh;
	}

	public void setCymz(String cymz) {
		this.cymz = cymz;
	}

	public String getCymz() {
		return cymz;
	}

	public void setCygz(String cygz) {
		this.cygz = cygz;
	}

	public String getCygz() {
		return cygz;
	}

	public void setCyzw(String cyzw) {
		this.cyzw = cyzw;
	}

	public String getCyzw() {
		return cyzw;
	}

	public void setCyrxm(String cyrxm) {
		this.cyrxm = cyrxm;
	}

	public String getCyrxm() {
		return cyrxm;
	}

	public void setCyrgx(String cyrgx) {
		this.cyrgx = cyrgx;
	}

	public String getCyrgx() {
		return cyrgx;
	}

	public void setCyrgxsm(String cyrgxsm) {
		this.cyrgxsm = cyrgxsm;
	}

	public String getCyrgxsm() {
		return cyrgxsm;
	}

	public void setCyrzz(String cyrzz) {
		this.cyrzz = cyrzz;
	}

	public String getCyrzz() {
		return cyrzz;
	}

	public void setCyrxzq(String cyrxzq) {
		this.cyrxzq = cyrxzq;
	}

	public String getCyrxzq() {
		return cyrxzq;
	}

	public void setCyrdh(String cyrdh) {
		this.cyrdh = cyrdh;
	}

	public String getCyrdh() {
		return cyrdh;
	}

	public void setCyrjhr(String cyrjhr) {
		this.cyrjhr = cyrjhr;
	}

	public String getCyrjhr() {
		return cyrjhr;
	}

	public void setCyrsfzlx(String cyrsfzlx) {
		this.cyrsfzlx = cyrsfzlx;
	}

	public String getCyrsfzlx() {
		return cyrsfzlx;
	}

	public void setCyrzjh(String cyrzjh) {
		this.cyrzjh = cyrzjh;
	}

	public String getCyrzjh() {
		return cyrzjh;
	}

	public void setCyrmz(String cyrmz) {
		this.cyrmz = cyrmz;
	}

	public String getCyrmz() {
		return cyrmz;
	}

	public void setCyrgzdw(String cyrgzdw) {
		this.cyrgzdw = cyrgzdw;
	}

	public String getCyrgzdw() {
		return cyrgzdw;
	}

	public void setCyrzw(String cyrzw) {
		this.cyrzw = cyrzw;
	}

	public String getCyrzw() {
		return cyrzw;
	}

}
