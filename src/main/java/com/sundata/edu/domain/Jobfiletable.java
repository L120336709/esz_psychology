package com.sundata.edu.domain;



import com.sundata.edu.annotation.Excel;

import javax.persistence.*;
import java.util.Date;
/**
 * 表 jobfiletable
 *
 * @author whj
 * @date 2021-11-02 14:46:08
 */
@Table(name = "`jobfiletable`")
public class Jobfiletable {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`id`")
	private Long id;
	/**
	 * 学校id
	 */
	@Excel(name="学校名称")//需要手动查询id，获取学校名称
	@Column(name = "`schoolid`")
	private String schoolid;

	@Excel(name="学校实际名称")
	@Column(name = "`schoolname`")
	private String schoolname;


	/**
	 * 年级id
	 */
	@Excel(name="年级id")
	@Column(name = "`graded`")
	private String graded;
	/**
	 * 年级名称
	 */
	@Excel(name="年级名称")
	@Column(name = "`gradename`")
	private String gradename;
	/**
	 * 作业类型
	 */
	@Excel(name="作业类型")
	@Column(name = "`jobtype`")
	private String jobtype;

	@Excel(name="作业名称")
	@Column(name = "`jobname`")
	private String jobname;

	/**
	 * 班级id
	 */
	@Excel(name="班级id")
	@Column(name = "`classid`")
	private String classid;
	/**
	 * 班级名称
	 */
	@Excel(name="班级名称")
	@Column(name = "`classname`")
	private String classname;
	/**
	 * 登记时间
	 */
	@Excel(name="登记时间")
	@Column(name = "`recorddate`")
	private String  recorddate;

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	public String getRecorddate() {
		return recorddate;
	}

	public void setRecorddate(String recorddate) {
		this.recorddate = recorddate;
	}

	@Override
	public String toString() {
		return "Jobfiletable{" +
				"id=" + id +
				", schoolid='" + schoolid + '\'' +
				", schoolname='" + schoolname + '\'' +
				", graded='" + graded + '\'' +
				", gradename='" + gradename + '\'' +
				", jobtype='" + jobtype + '\'' +
				", jobname='" + jobname + '\'' +
				", classid='" + classid + '\'' +
				", classname='" + classname + '\'' +
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

	/**
	 * 登记老师
	 */
	@Excel(name="登记老师id")
	@Column(name = "`teacherid`")
	private String teacherid;
	/**
	 * 登记老师姓名
	 */
	@Excel(name="登记老师姓名")
	@Column(name = "`teachername`")
	private String teachername;
	/**
	 * 科目id
	 */
	@Excel(name="科目id")
	@Column(name = "`subjectid`")
	private String subjectid;
	/**
	 * 作业科目名称
	 */
	@Excel(name="作业科目名称")
	@Column(name = "`subjectname`")
	private String subjectname;
	/**
	 * 学生id
	 */
	@Excel(name="学生id")
	@Column(name = "`studentid`")
	private String studentid;
	/**
	 * 学生姓名
	 */
	@Excel(name="学生姓名")
	@Column(name = "`studentname`")
	private String studentname;
	/**
	 * 作业分数
	 */
	@Excel(name="作业分数")
	@Column(name = "`homeworkscores`")
	private String homeworkscores;
	/**
	 * 作业照片
	 */
	@Column(name = "`workphoto`")
	private String workphoto;
	/**
	 * 优秀作业(1代表是，0代表否)
	 */
	@Column(name = "`excellentwork`")
	private String excellentwork;
	/**
	 * 优秀作业评语
	 */
	@Column(name = "`excellentremark`")
	private String excellentremark;
	/**
	 * 典型作业(1代表是，0代表否)
	 */
	@Column(name = "`typicaljobmark`")
	private String typicaljobmark;
	/**
	 * 典型作业标注
	 */
	@Column(name = "`typicalworkmark`")
	private String typicalworkmark;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}

	public String getSchoolid() {
		return schoolid;
	}

	public void setGraded(String graded) {
		this.graded = graded;
	}

	public String getGraded() {
		return graded;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public String getGradename() {
		return gradename;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getClassname() {
		return classname;
	}



	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	public String getTeacherid() {
		return teacherid;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubjectid() {
		return subjectid;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setHomeworkscores(String homeworkscores) {
		this.homeworkscores = homeworkscores;
	}

	public String getHomeworkscores() {
		return homeworkscores;
	}

	public void setWorkphoto(String workphoto) {
		this.workphoto = workphoto;
	}

	public String getWorkphoto() {
		return workphoto;
	}

	public void setExcellentwork(String excellentwork) {
		this.excellentwork = excellentwork;
	}

	public String getExcellentwork() {
		return excellentwork;
	}

	public void setExcellentremark(String excellentremark) {
		this.excellentremark = excellentremark;
	}

	public String getExcellentremark() {
		return excellentremark;
	}

	public void setTypicaljobmark(String typicaljobmark) {
		this.typicaljobmark = typicaljobmark;
	}

	public String getTypicaljobmark() {
		return typicaljobmark;
	}

	public void setTypicalworkmark(String typicalworkmark) {
		this.typicalworkmark = typicalworkmark;
	}

	public String getTypicalworkmark() {
		return typicalworkmark;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
}
