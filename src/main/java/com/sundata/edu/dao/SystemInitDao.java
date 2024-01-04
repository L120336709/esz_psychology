package com.sundata.edu.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 学业成绩 数据层
 *
 * @author whj
 * @date 2019-07-16 15:22:45
 */
@Repository
public interface SystemInitDao {

	/**
	 * 查询学科
	 */
	List<Map<String, Object>> querySubjects();

	/**
	 * 查询学年
	 */
	List<Map<String, Object>> querySchoolYear();

	/**
	 * 查询考试
	 *
	 * @return
	 */
	List<Map<String, Object>> queryExams();

	/**
	 * 查询学生
	 *
	 * @return
	 */
	List<Map<String, Object>> queryUsers();
}
