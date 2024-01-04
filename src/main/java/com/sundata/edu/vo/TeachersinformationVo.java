package com.sundata.edu.vo;
import java.util.Date;
/**
 * 表 teachersinformation
 * 
 * @author whj
 * @date 2021-10-20 09:19:42
 */
public class TeachersinformationVo extends BaseVo {
    /**
     * 主键
     */
	private Long id;
    /**
     * 姓名
     */
	private String nameofstaff;
    /**
     * 身份证号
     */
	private String idcardnumber;
    /**
     * 性别
     */
	private String gendersexsexuality;
    /**
     * 学段类别
     */
	private String learningcategory;
    /**
     * 学科代码
     */
	private String disciplinecode;
    /**
     * 所属单位
     */
	private String affiliatedunit;
    /**
     * 职务职称
     */
	private String jobtitle;
    /**
     * 联系电话
     */
	private String telephonenumber;
    /**
     * 银行卡号
     */
	private String bankaccount;
    /**
     * 开户行
     */
	private String bankaddress;
    /**
     * 岗位
     */
	private String jobquarters;
    /**
     * 工作人员照片
     */
	private String staffphoto;
    /**
     * 备注一
     */
	private String postscriptone;
    /**
     * 备注二
     */
	private String postscripttwo;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}

	public void setNameofstaff(String nameofstaff) {
		this.nameofstaff = nameofstaff;
	}

	public String getNameofstaff()
	{
		return nameofstaff;
	}

	public void setIdcardnumber(String idcardnumber) {
		this.idcardnumber = idcardnumber;
	}

	public String getIdcardnumber()
	{
		return idcardnumber;
	}

	public void setGendersexsexuality(String gendersexsexuality) {
		this.gendersexsexuality = gendersexsexuality;
	}

	public String getGendersexsexuality()
	{
		return gendersexsexuality;
	}

	public void setLearningcategory(String learningcategory) {
		this.learningcategory = learningcategory;
	}

	public String getLearningcategory()
	{
		return learningcategory;
	}

	public void setDisciplinecode(String disciplinecode) {
		this.disciplinecode = disciplinecode;
	}

	public String getDisciplinecode()
	{
		return disciplinecode;
	}

	public void setAffiliatedunit(String affiliatedunit) {
		this.affiliatedunit = affiliatedunit;
	}

	public String getAffiliatedunit()
	{
		return affiliatedunit;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getJobtitle()
	{
		return jobtitle;
	}

	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}

	public String getTelephonenumber()
	{
		return telephonenumber;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getBankaccount()
	{
		return bankaccount;
	}

	public void setBankaddress(String bankaddress) {
		this.bankaddress = bankaddress;
	}

	public String getBankaddress()
	{
		return bankaddress;
	}

	public void setJobquarters(String jobquarters) {
		this.jobquarters = jobquarters;
	}

	public String getJobquarters()
	{
		return jobquarters;
	}

	public void setStaffphoto(String staffphoto) {
		this.staffphoto = staffphoto;
	}

	public String getStaffphoto()
	{
		return staffphoto;
	}

	public void setPostscriptone(String postscriptone) {
		this.postscriptone = postscriptone;
	}

	public String getPostscriptone()
	{
		return postscriptone;
	}

	public void setPostscripttwo(String postscripttwo) {
		this.postscripttwo = postscripttwo;
	}

	public String getPostscripttwo()
	{
		return postscripttwo;
	}

	@Override
    public String toString() {
        return "TeachersinformationVo{" +
        		"id=" + id +
        		"nameofstaff=" + nameofstaff +
        		"idcardnumber=" + idcardnumber +
        		"gendersexsexuality=" + gendersexsexuality +
        		"learningcategory=" + learningcategory +
        		"disciplinecode=" + disciplinecode +
        		"affiliatedunit=" + affiliatedunit +
        		"jobtitle=" + jobtitle +
        		"telephonenumber=" + telephonenumber +
        		"bankaccount=" + bankaccount +
        		"bankaddress=" + bankaddress +
        		"jobquarters=" + jobquarters +
        		"staffphoto=" + staffphoto +
        		"postscriptone=" + postscriptone +
        		"postscripttwo=" + postscripttwo +
				"}";
    }
}
