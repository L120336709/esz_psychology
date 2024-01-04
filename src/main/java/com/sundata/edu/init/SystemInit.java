package com.sundata.edu.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.sundata.edu.dao.SystemInitDao;

//@Component
public class SystemInit implements CommandLineRunner {

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 学科名称_id的map
	 */
	public static Map<String, String> subjectMap = new HashMap<String, String>();

	public static List<String> subjectList = new ArrayList<String>();

	/**
	 * 学年名称_id的map
	 */
	public static Map<String, String> schoolYearMap = new HashMap<String, String>();

	/**
	 * 考试场次名称_id的map
	 */
	public static Map<String, String> examMap = new HashMap<String, String>();

	/**
	 * 学生id_姓名的map
	 */
	public static Map<String, String> userMap = new HashMap<String, String>();

	/**
	 * 活动类型map
	 */
	public static Map<String, String> activityTypeMap = new HashMap<String, String>();

	/**
	 * 活动级别map
	 */
	public static Map<String, String> activityLevelMap = new HashMap<String, String>();

	@Autowired
	private SystemInitDao systemInitDao;

	@Override
	public void run(String... args) throws Exception {
		initSubject();
		initSchoolYear();
		initExam();
		initUser();
		initActivity();
	}

	// 从学科数据库中读取学科名称和学科编码的对应关系
	private void initSubject() {
		List<Map<String, Object>> subjects = systemInitDao.querySubjects();
		for (Map<String, Object> params : subjects) {
			subjectMap.put(params.get("name").toString(), params.get("id").toString());
			subjectList.add(params.get("name").toString());
		}
		// subjectMap.put("语文","1");
		// subjectMap.put("数学","2");
		// subjectMap.put("英语","3");
		logger.debug("学科：" + subjects);
	}

	//
	private void initSchoolYear() {

		List<Map<String, Object>> schoolYears = systemInitDao.querySchoolYear();
		for (Map<String, Object> params : schoolYears) {
			schoolYearMap.put(params.get("name").toString(), params.get("id").toString());
		}
		logger.debug("学年：" + schoolYears);
		/*
		 * schoolYearMap.put("2017-2018","2"); schoolYearMap.put("2018-2019","1");
		 */
	}

	public static Integer getSchoolYearId(String name) {
		if (!name.contains("学年")) {
			name += "学年";
		}
		String id = schoolYearMap.get(name);
		return id == null ? 0 : Integer.parseInt(id);
	}

	private void initExam() {

		List<Map<String, Object>> exams = systemInitDao.queryExams();
		for (Map<String, Object> params : exams) {
			examMap.put(params.get("name").toString(), params.get("id").toString());
		}
		logger.debug("考试场次：" + examMap);

		/*
		 * examMap.put("一月份考试","1"); examMap.put("二月份考试","2");
		 */
	}

	private void initUser() {

		List<Map<String, Object>> users = systemInitDao.queryUsers();
		for (Map<String, Object> params : users) {
			userMap.put(params.get("id").toString(), params.get("name").toString());
		}
		logger.debug("学生信息：" + userMap);
	}

	/**
	 * 活动参数初始化
	 */
	private void initActivity() {
		activityTypeMap.put("101", "社团活动");
		activityTypeMap.put("102", "党团活动");
		// 101 班级 102校级 103区、县 104市级 105省级
		activityLevelMap.put("101", "班级");
		activityLevelMap.put("102", "校级");
		activityLevelMap.put("103", "区、县");
		activityLevelMap.put("104", "市级");
		activityLevelMap.put("105", "省级");
	}

}
