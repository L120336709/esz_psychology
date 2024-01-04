package com.sundata.edu.vo;
import java.util.Date;
/**
 * 表 jobfiletable
 *
 * @author whj
 * @date 2021-11-02 14:46:08
 */
public class JobfiletableVo extends BaseVo {
    /**
     * 主键
     */
	private Long id;
    /**
     * 学校id
     */
	private String schoolid;

	private String schoolname;
    /**
     * 年级id
     */
	private String graded;
    /**
     * 年级名称
     */
	private String gradename;
    /**
     * 班级id
     */
	private String classid;
    /**
     * 班级名称
     */
	private String classname;
	/*
	* 作业类型
	* */
	private String jobtype;
	private String jobname;

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	/**
     * 登记时间
     */
	private String recorddate;
    /**
     * 登记老师
     */
	private String teacherid;
    /**
     * 登记老师姓名
     */
	private String teachername;
    /**
     * 科目id
     */
	private String subjectid;
    /**
     * 作业科目名称
     */
	private String subjectname;
    /**
     * 学生id
     */
	private String studentid;
    /**
     * 学生姓名
     */
	private String studentname;
    /**
     * 作业分数
     */
	private String homeworkscores;
    /**
     * 作业照片
     */
	private String workphoto;
    /**
     * 优秀作业(1代表是，0代表否)
     */
	private String excellentwork;
    /**
     * 优秀作业评语
     */
	private String excellentremark;
    /**
     * 典型作业(1代表是，0代表否)
     */
	private String typicaljobmark;

	@Override
	public String toString() {
		return "JobfiletableVo{" +
				"id=" + id +
				", schoolid='" + schoolid + '\'' +
				", schoolname='" + schoolname + '\'' +
				", graded='" + graded + '\'' +
				", gradename='" + gradename + '\'' +
				", classid='" + classid + '\'' +
				", classname='" + classname + '\'' +
				", jobtype='" + jobtype + '\'' +
				", jobname='" + jobname + '\'' +
				", recorddate='" + recorddate + '\'' +
				", teacherid='" + teacherid + '\'' +
				", teachername='" + teachername + '\'' +
				", subjectid='" + subjectid + '\'' +
				", subjectname='" + subjectname + '\'' +
				", studentid='" + studentid + '\'' +
				", studentname='" + studentname + '\'' +
				", homeworkscores='" + homeworkscores + '\'' +
				", workphoto='" + workphoto + '\'' +
				", excellentwork='" + excellentwork + '\'' +
				", excellentremark='" + excellentremark + '\'' +
				", typicaljobmark='" + typicaljobmark + '\'' +
				", typicalworkmark='" + typicalworkmark + '\'' +
				'}';
	}

	public String getRecorddate() {
		return recorddate;
	}

	public void setRecorddate(String recorddate) {
		this.recorddate = recorddate;
	}

	/**
     * 典型作业标注
     */
	private String typicalworkmark;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}

	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}

	public String getSchoolid()
	{
		return schoolid;
	}

	public void setGraded(String graded) {
		this.graded = graded;
	}

	public String getGraded()
	{
		return graded;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public String getGradename()
	{
		return gradename;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getClassid()
	{
		return classid;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getClassname()
	{
		return classname;
	}


	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	public String getTeacherid()
	{
		return teacherid;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getTeachername()
	{
		return teachername;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubjectid()
	{
		return subjectid;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getSubjectname()
	{
		return subjectname;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getStudentid()
	{
		return studentid;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getStudentname()
	{
		return studentname;
	}

	public void setHomeworkscores(String homeworkscores) {
		this.homeworkscores = homeworkscores;
	}

	public String getHomeworkscores()
	{
		return homeworkscores;
	}

	public void setWorkphoto(String workphoto) {
		this.workphoto = workphoto;
	}

	public String getWorkphoto()
	{
		return workphoto;
	}

	public void setExcellentwork(String excellentwork) {
		this.excellentwork = excellentwork;
	}

	public String getExcellentwork()
	{
		return excellentwork;
	}

	public void setExcellentremark(String excellentremark) {
		this.excellentremark = excellentremark;
	}

	public String getExcellentremark()
	{
		return excellentremark;
	}

	public void setTypicaljobmark(String typicaljobmark) {
		this.typicaljobmark = typicaljobmark;
	}

	public String getTypicaljobmark()
	{
		return typicaljobmark;
	}

	public void setTypicalworkmark(String typicalworkmark) {
		this.typicalworkmark = typicalworkmark;
	}

	public String getTypicalworkmark()
	{
		return typicalworkmark;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
}
