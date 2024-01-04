package com.sundata.edu.controller;
import com.sundata.edu.domain.*;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.service.*;
import com.sundata.edu.util.ExcelUtil;
import com.sundata.edu.vo.*;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *  信息操作处理
 *
 * @author whj
 * @date 2021-10-27 10:03:25
 */
@Controller
@RequestMapping("/schedules")
public class SchedulesController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String prefix = "schedules";
	@Autowired
	private SchedulesService schedulesService;
	//注入学生信息Service
	@Autowired
	private StudentinfoService studentinfoService;
	@Autowired
	private ClassinfoService classinfoService;
	@Autowired
	private OrginfoService orginfoService;
	@Autowired
	private UserinfoService userinfoService;
	@Autowired
	private SubjecttableService subjecttableService;

	@Autowired
	private SubjectsteaerService subjectsteaerService;

	@Autowired
	private PhaseofstudyService phaseofstudyService;

	@GetMapping()
	public String schedules()
	{
	    return prefix + "/list";
	}
    //更换页面
	@GetMapping("/teachingClass")
	public String scheduless()
	{
		return prefix + "/teachingClass";
	}
	//更换页面
	@GetMapping("/rtusject")
	public String schedulests()
	{
		return prefix + "/rtusject";
	}
	//更换页面
	@GetMapping("/classnum")
	public String classnum()
	{
		return prefix + "/classnum";
	}
	/**
	 * 查询列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SchedulesVo schedulesVo)
	{
        startPage();
        return getDataTable(schedulesService.getScheduless(schedulesVo));
	}
	/**
	 * 删除任教科目操作
	 */
	@PostMapping( "/removes")
	@ResponseBody
	public AjaxResult removes(String id)
	{
		return toAjax(subjectsteaerService.deleteByIds(id));
		//return toAjax(subjectsteaerService.removes(id));
	}

	/**
	 * 删除授课班级操作
	 */
	@PostMapping( "/deleteEduTeachClass")
	@ResponseBody
	public AjaxResult deleteEduTeachClass(String teachid)
	{
		return toAjax(schedulesService.deleteByIds(teachid));
	}

	/**
	 * 查询该用户，任教科目
	 */
	@PostMapping("/subeaerlist")
	@ResponseBody
	public  TableDataInfo subeaerlist()
	{
		startPage();
		//得到用户信息
		Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
		String userid = userinfo.getUserId();
		SubjectsteaerVo subjectsteaerVo = new  SubjectsteaerVo();
		subjectsteaerVo.setUserid(userid);
		List<Subjectsteaer> subjectsteaers=subjectsteaerService.getSubjectsteaers(subjectsteaerVo);

		for (int i=0;i<subjectsteaers.size();i++) {

			SubjecttableVo subjecttableVo=new SubjecttableVo();

			subjecttableVo.setSubjectid(Long.valueOf(subjectsteaers.get(i).getSubject()));
			List<Subjecttable> subjecttables=subjecttableService.getSubjecttables(subjecttableVo);
			subjectsteaers.get(i).setUserid(userinfo.getRealName());
			subjectsteaers.get(i).setSubject(subjecttables.get(0).getSubjectname());
		}
		return getDataTable(subjectsteaers);
	}


    /**
     * 录入班级后查询作业编号
     */
    @PostMapping("/classnumlist")
    @ResponseBody
    public  TableDataInfo classnumlist(String gradeid,String classid)
    {
        startPage();
        //得到用户信息
        Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
        String userid = userinfo.getUserId();

        //单独某个班学生数据查询时进入下面步骤，否则跳过if，执行后续所有数据查询
        if(gradeid!=null&&!gradeid.equals("")){
			StudentinfoVo studentinfoVo=new StudentinfoVo();
			studentinfoVo.setSchoolcode(userinfo.getOrgId());
			studentinfoVo.setClassCode(classid);
			studentinfoVo.setGradeCode(gradeid);

			List<Studentinfo> studentinfos=studentinfoService.getStudentinfoslist(studentinfoVo);
			String schoolname=orginfoService.getOrgInfoByOrgId(userinfo.getOrgId()).getOrgName();
			String classname=classinfoService.getClassinfoByClassId(studentinfoVo.getClassCode()).getClassName();

			for (int i=0;i<studentinfos.size();i++) {
				studentinfos.get(i).setClassCode(classname);
				studentinfos.get(i).setSchoolcode(schoolname);
			}
			return  getDataTable(studentinfos);
		}

		List<Studentinfo> studentinfolist=new ArrayList<>();
		SchedulesVo schedulesVo=new SchedulesVo();
		schedulesVo.setUserid(userid);
		//查询该用户绑定的所有教学班级信息
        List<Schedules> schedules=schedulesService.getSchedulessbtes(schedulesVo);

        //保存不重复的班级信息，用于判断当前循环班级是否重复
        Map<Integer,List<String>> classmap=new HashMap<>();
		int mapnum=1;

		String schoolname=orginfoService.getOrgInfoByOrgId(userinfo.getOrgId()).getOrgName();
		//System.err.println(schoolname);

		for (int i=0;i<schedules.size();i++) {
			boolean classhave=false;
			//检查是否有重复班级
			int size=classmap.size();
			//有重复班级，则classhave设为true，后续判断classhave，重复则跳过此次循环
			for(int mapx=1;mapx<=size;mapx++){
				if(classmap.get(mapx).get(0).equals(schedules.get(i).getClassid())) {
					if (classmap.get(mapx).get(1).equals(schedules.get(i).getGradeid())) {
						classhave = true;
						//已经判断是重复数据，直接classhave设为true，跳出判断重复的循环
						break;
					}
				}
				List<String> classlist=new ArrayList<>();
				classlist.add(schedules.get(i).getClassid());
				classlist.add(schedules.get(i).getGradeid());
				classmap.put(mapnum,classlist);
				mapnum++;
			}

			if(classhave){
				continue;
			}

			//第一次循环，加上第一次的班级
			if(classmap.size()==0){
				List<String> classlist=new ArrayList<>();
				classlist.add(schedules.get(i).getClassid());
				classlist.add(schedules.get(i).getGradeid());
				classmap.put(mapnum,classlist);
				mapnum++;
			}

			StudentinfoVo studentinfoVo=new StudentinfoVo();
			studentinfoVo.setSchoolcode(userinfo.getOrgId());
			studentinfoVo.setClassCode(schedules.get(i).getClassid());
			studentinfoVo.setGradeCode(schedules.get(i).getGradeid());
			List<Studentinfo> ss=studentinfoService.getStudentinfoslist(studentinfoVo);


			String clazzid="";
			String clazzName="";
			for (Studentinfo stu:ss) {
				if(stu.getJobnumber()!=null){
					if(!clazzid.equals(stu.getClassCode())){
						clazzid=stu.getClassCode();
						clazzName=classinfoService.getClassinfoByClassId(stu.getClassCode()).getClassName();
					}
					stu.setSchoolcode(schoolname);
					stu.setClassCode(clazzName);
					studentinfolist.add(stu);
				}
			}
		}

        return getDataTable(studentinfolist);
    }



	@PostMapping("/phaseofstudylist")
	@ResponseBody
	public TableDataInfo phaseofstudylist(PhaseofstudyVo phaseofstudyVo)
	{
		startPage();
		return getDataTable(phaseofstudyService.getPhaseofstudys(phaseofstudyVo));
	}
	/**
	 * 新增
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	/**
	 * 新增保存
	 */

	@PostMapping("/add")
	@ResponseBody
	public AjaxResult add(Schedules schedules)
	{
		return toAjax(schedulesService.insertSelective(schedules));
	}

	/**
	 * 添加老师任课科目
	 */

	@PostMapping("/addTeachSubject")
	@ResponseBody
	public AjaxResult addTeachSubject(long phaseCode, String subjectId )
	{
		//得到用户信息
		Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
		String userid = userinfo.getUserId();
		//根据phaseCode得到时那个段的
		PhaseofstudyVo phaseofstudyVo = new PhaseofstudyVo();
		phaseofstudyVo.setPhasestudyid(phaseCode);
		List<Phaseofstudy> getPhaseofstudyList =phaseofstudyService.getPhaseofstudystu(phaseofstudyVo);
		String phasestudy = getPhaseofstudyList.get(0).getPhasestudy();
		SubjectsteaerVo subjectsteaerVo = new SubjectsteaerVo();
		subjectsteaerVo.setPhasestudy(phasestudy);
		subjectsteaerVo.setUserid(userid);
		subjectsteaerVo.setSubject(subjectId);
		List<Subjectsteaer> getSubjectsteaerList  = subjectsteaerService.getSubjectsteaersujet(subjectsteaerVo);
		if (getSubjectsteaerList!=null&& !getSubjectsteaerList.isEmpty()){
			return AjaxResult.success("已经存在，该信息请不要重复创建").put("data",getSubjectsteaerList.get(0));
		}else {
			//增加老师所带学科信息
			Subjectsteaer subjectsteaer = new Subjectsteaer();
			subjectsteaer.setUserid(userid);
			subjectsteaer.setPhasestudy(phasestudy);
			subjectsteaer.setSubject(subjectId);
			return toAjax(subjectsteaerService.insertSelective(subjectsteaer));
		}
	}

	//获取学校名称和年级
	@PostMapping("/userschool")
	@ResponseBody
	public AjaxResult userschool()
	{

		//得到用户信息
		Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
		String userschool = userinfo.getOrgId();

		//获取学校名
		Orginfo orginfo=orginfoService.getOrgInfoByOrgId(userschool);
		String schoolname=orginfo.getOrgName();

		//获取年级
		SchedulesVo schedulesVo=new SchedulesVo();
		schedulesVo.setUserid(userinfo.getUserId());
		List<Schedules> schedules=schedulesService.getSchedulesslist(schedulesVo);


		List<Schedules> schedulelist=new ArrayList<>();
		Map<String, List<Schedules>> payAreaPutMap =
				schedules.stream().collect(Collectors.groupingBy(Schedules::getGradeid));


		for (List<Schedules> value : payAreaPutMap.values()) {
			for(int i=0;i<value.size();i++){
				schedulelist.add(value.get(i));
			}

		}

		//获取班级
		for (int i=0;i<schedulelist.size();i++) {
			String classid=schedulelist.get(i).getClassid();
			Classinfo classinfos=classinfoService.getClassinfoByClassId(classid);
			schedulelist.get(i).setSubjectid(classinfos.getClassName());//空值，附上班级名称方便调用
			schedulelist.get(i).setPhasestudy(schoolname);//空值，附上学校名称方便调用
		}

		if (schedulelist!=null){
			return success().put("data",schedulelist);
		}else {
			return success("未加载到数据");
		}
	}





	//学段选择查询出所有的科目
	@PostMapping("/phasestudylist")
	@ResponseBody
	public AjaxResult phasestudylist(long phaseCode)
	{
		//根据phaseCode得到时那个段的
		PhaseofstudyVo phaseofstudyVo = new PhaseofstudyVo();
		phaseofstudyVo.setPhasestudyid(phaseCode);
		List<Phaseofstudy> getPhaseofstudyList  =	phaseofstudyService.getPhaseofstudystu(phaseofstudyVo);
		if (getPhaseofstudyList!=null&& !getPhaseofstudyList.isEmpty()){
			String phasestudy = getPhaseofstudyList.get(0).getPhasestudy();
			SubjecttableVo subjecttableVo  = new SubjecttableVo();
			subjecttableVo.setPhasestudy(phasestudy); //小学
			List<Subjecttable> getSubjectsteaerList  =	subjecttableService.getSubjectertables(subjecttableVo);
			//System.err.println(getSubjectsteaerList.toString());
			return success().put("data",getSubjectsteaerList);
		}else {
			return success("未加载到数据");
		}

	}


	/*
    *   插入老师任课的学科
    * */
	@PostMapping("/subjectsteaerList")
	@ResponseBody
	public AjaxResult subjectsteaerList(String phasestudy,String subjectname )
	{
		//得到用户信息
		Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
		String userid = userinfo.getUserId();
		//查询科目表得到学科id
		SubjecttableVo subjecttableVo = new SubjecttableVo();
		subjecttableVo.setSubjectname(subjectname);
		subjecttableVo.setPhasestudy(phasestudy);
		List<Subjecttable> subjecttableList = subjecttableService.getSubjecttables(subjecttableVo);
		String subjectid =subjecttableList.get(0).getSubjectid().toString();
		//创建查询实体类
		SubjectsteaerVo subjectsteaerVo = new SubjectsteaerVo();
		subjectsteaerVo.setPhasestudy(subjectid);
		subjectsteaerVo.setSubject(subjectname);
		subjectsteaerVo.setUserid(userid);
		//根据前端传入的学段和学科信息查询数据是否有值
		List<Subjectsteaer> getSubjectsteaerList =subjectsteaerService.getSubjectsteaers(subjectsteaerVo);
		if(getSubjectsteaerList!=null&& !getSubjectsteaerList.isEmpty()){
			//有值返回有值
			return AjaxResult.success("已经存在，该信息请不要重复创建").put("data",getSubjectsteaerList.get(0));
		}else{
			//增加老师所带学科信息
			Subjectsteaer subjectsteaer = new Subjectsteaer();
			subjectsteaer.setUserid(userid);
			subjectsteaer.setPhasestudy(phasestudy);
			subjectsteaer.setSubject(subjectname);
			return toAjax(subjectsteaerService.insertSelective(subjectsteaer));
		}
	}


	/*
	  根据老师的userid 查询该老师所有的任教信息
	* */
	@PostMapping("/subjobList")
	@ResponseBody
	public AjaxResult subjobList()
	{
		//得到用户信息
		Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
		String userid = userinfo.getUserId();
		//创建查询实体类
		SchedulesVo schedulesVo = new SchedulesVo();
		schedulesVo.setUserid(userid);
		//根据老师的userid 查询该到教师所有任课科目
		List<Schedules> getSchedulesList =schedulesService.getSchedulessbtes(schedulesVo);

		for (int i=0;i<getSchedulesList.size();i++){
			getSchedulesList.get(i).setUserid(userinfo.getRealName());
			//科目
			SubjecttableVo subjecttableVo=new SubjecttableVo();
			subjecttableVo.setSubjectid(Long.valueOf(getSchedulesList.get(i).getSubjectid()));
			List<Subjecttable> subjecttables=subjecttableService.getSubjecttables(subjecttableVo);
			getSchedulesList.get(i).setSubjectid(subjecttables.get(0).getSubjectname());
			//班级
			ClassinfoVo classinfoVo=new ClassinfoVo();
			classinfoVo.setClassId(getSchedulesList.get(i).getClassid());
			List<Classinfo> classinfos=classinfoService.selectListbtapp(classinfoVo);
			getSchedulesList.get(i).setClassid(classinfos.get(0).getClassName());
		}

		return success().put("data",getSchedulesList);
	}

	/*
	  根据userid、年级、班级查询该老师所有的任教信息（科目名字）
	* */
	@PostMapping("/subjobnameList")
	@ResponseBody
	public AjaxResult subjobnameList(String gradeid,String classid)
	{
		//得到用户信息
		Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
		String userid = userinfo.getUserId();
		//创建查询实体类
		SchedulesVo schedulesVo = new SchedulesVo();
		schedulesVo.setUserid(userid);
		schedulesVo.setGradeid(gradeid);
		schedulesVo.setClassid(classid);
		//根据老师的userid 查询该到教师所有任课科目
		List<Schedules> getSchedulesList =schedulesService.getSchedulesslist(schedulesVo);
		List<Subjecttable> subjecttableList=new ArrayList<>();

		for (Schedules sc:getSchedulesList) {
			SubjecttableVo subjecttableVo=new SubjecttableVo();
			subjecttableVo.setSubjectid(Long.valueOf(sc.getSubjectid()));
			subjecttableVo.setPhasestudy("小学");
			List<Subjecttable> subjecttableLists=subjecttableService.getSubjecttablesject(subjecttableVo);
			subjecttableList.add(subjecttableLists.get(0));
		}
		return success().put("data",subjecttableList);
	}

	/**
	 *
	 * 得到包含学校的所有年级集合
	 */
	//@RequiresPermissions("schedules:add")
	//@Log(title = "", action = BusinessType.INSERT)
	@PostMapping("/gradeList")
	@ResponseBody
	public AjaxResult gradeList()
	{
		//得到用户信息
		 Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
		 String orgId = userinfo.getOrgId();//学校id
		 //String userid = userinfo.getUserId();
		 ClassinfoVo classinfoVo = new ClassinfoVo();
		 classinfoVo.setSchoolId(orgId);
		//根据组织id确定该老师属于那个学校，并查询出该老师所属年级
		 List<Classinfo> classinfoList = classinfoService.selectListapp(classinfoVo);
		 /*  去除年级重合的年级
		 * */
		 List<Classinfo> classinfoListt = classinfoList.stream().filter(distinctByKey(Classinfo::getGradeId)).collect(Collectors.toList());

		 return success().put("data",classinfoListt);
	}


	/*
	 * stream流去重，distinctBykey通用类
	 * */
	private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	/*
	* 根据GradeId得到年级下包含的所有班级集合
	* */
	@PostMapping("/classList")
	@ResponseBody
	public AjaxResult classList(String gradeId)
	{  //得到用户信息
		Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
		String orgId = userinfo.getOrgId();//学校id
		ClassinfoVo classinfoVo = new ClassinfoVo();
		classinfoVo.setGradeId(gradeId);
		classinfoVo.setSchoolId(orgId);
		//根据组织id确定该老师属于那个学校，并查询出该老师所属年级
		List<Classinfo> classinfoListb = classinfoService.selectListclass(classinfoVo);
		List<Classinfo> classinfoList =	classinfoListb.stream().filter(distinctByKey(Classinfo::getClassName)).collect(Collectors.toList());
		return success().put("data",classinfoList);
	}

   /*
   * 根据用户查询去重得到老师任课科目
   * */

	@PostMapping("/getTeaClsSubject")
	@ResponseBody
	public AjaxResult getTeaClsSubject()
	{
		//得到用户信息
		Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
		String userid = userinfo.getUserId();
		SubjectsteaerVo subjectsteaerVo = new  SubjectsteaerVo();
		subjectsteaerVo.setUserid(userid);
		List<Subjectsteaer> getTeaClsSubjectList = subjectsteaerService.getSubjectsteaers(subjectsteaerVo);
		List<Subjectsteaer> getTeaClsSubjectListrt=getTeaClsSubjectList.stream().filter(distinctByKey(Subjectsteaer::getSubject)).collect(Collectors.toList());
		List<SubjectsteaerappVo>  getTeaClsSubjectListrttu=new ArrayList<>();
		//根据学科编码得到学科名称
		for (Subjectsteaer subjectsteaer:getTeaClsSubjectListrt
			 ) {
			SubjectsteaerappVo  subjectsteaerrVo = new SubjectsteaerappVo();
			Long idds =subjectsteaer.getId();
			String subject = subjectsteaer.getSubject();
			String phasestudy= subjectsteaer.getPhasestudy();
			SubjecttableVo subjecttableVo = new SubjecttableVo();
			long lsubject = Long.valueOf(subject).longValue();
			subjecttableVo.setSubjectid(lsubject);
			subjecttableVo.setPhasestudy(phasestudy);
			List<Subjecttable> getsubjecttableList=subjecttableService.getSubjecttablesject(subjecttableVo);
			String Subjectname = getsubjecttableList.get(0).getSubjectname();
			subjectsteaerrVo.setId(idds);
			subjectsteaerrVo.setUserid(userid);
			subjectsteaerrVo.setSubject(Subjectname);
			subjectsteaerrVo.setSubjectid(subject);
			subjectsteaerrVo.setPhasestudy(phasestudy);
			getTeaClsSubjectListrttu.add(subjectsteaerrVo);
		}
		return success().put("data",getTeaClsSubjectListrttu);
	}

	/*
	* 根据老师选择的值 学科subject  年级gradename以及班级classname ,保存老师的任教信息schedules
	* */
	@PostMapping("/saveEduTeachClassList")
	@ResponseBody
	public AjaxResult saveEduTeachClassList( String subjectid,String gradeid,String classid )
	 {
		//得到用户信息
		Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
		String userid = userinfo.getUserId();//用户id
		//构建查询实体类
		SchedulesVo schedulesVo = new SchedulesVo();
		schedulesVo.setClassid(classid);
		schedulesVo.setSubjectid(subjectid);
		schedulesVo.setUserid(userid);
		schedulesVo.setGradeid(gradeid);
		List<Schedules> SchedulesList = schedulesService.getSchedulesslist(schedulesVo);
		 if(SchedulesList!=null&& !SchedulesList.isEmpty()){
			 //有值返回有值
			 return AjaxResult.success("已经存在，该信息请不要重复创建").put("data",SchedulesList.get(0));
		 }else{
			 //创建完成授课班级后，创建班级对应的学生作业编号，
			 StudentinfoVo studentinfoVo = new StudentinfoVo();
			 String schoolCode = userinfo.getOrgId();
			 studentinfoVo.setSchoolcode(schoolCode);
			 studentinfoVo.setGradeCode(gradeid);
			 studentinfoVo.setClassCode(classid);
			 List<Studentinfo> getCalassStudentList = studentinfoService.getStudentinfoslist(studentinfoVo);
			 if(getCalassStudentList.size()>0) {
			 	String jobnum=getCalassStudentList.get(0).getJobnumber();
				 if ( jobnum == null || jobnum.equals("")||jobnum.equals("null")) {
					 for (int i = 0; i < getCalassStudentList.size(); i++) {
						 int count = i + 1;
						 String congttt = count + "";
						 Studentinfo tStudentinfo = getCalassStudentList.get(i);
						 tStudentinfo.setJobnumber(congttt);
						 getCalassStudentList.get(i).setJobnumber(congttt);
					 }
					 studentinfoService.updateStudentinfoList(getCalassStudentList);
				 }
			 }

			 //构建插入实体类
			 Schedules schedules = new Schedules();
			 schedules.setUserid(userid);
			 schedules.setClassid(classid);
			 schedules.setSubjectid(subjectid);
			 schedules.setGradeid(gradeid);
			 return toAjax(schedulesService.insertSelective(schedules));
		 }
	}


	/**
	 * 修改
	 */
	@GetMapping("/edit/{teachid}")
	public String edit(@PathVariable("teachid") Long teachid, ModelMap model)
	{
        model.put("schedules", schedulesService.selectByPrimaryKey(teachid));
	    return prefix + "/edit";
	}

	/**
	 * 修改保存
	 */
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult edit(Schedules schedules)
	{
		return toAjax(schedulesService.updateByPrimaryKeySelective(schedules));
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(schedulesService.removes(ids));
	}

}
