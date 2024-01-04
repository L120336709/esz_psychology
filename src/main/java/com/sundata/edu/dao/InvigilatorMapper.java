package com.sundata.edu.dao;

import com.sundata.edu.domain.Invigilator;
import com.sundata.edu.framework.core.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  数据层
 * 
 * @author shunguo
 * @date 2021-12-10
 */
public interface InvigilatorMapper extends Mapper<Invigilator>
{
	/**
     * 查询信息
     * 
     * @param teachid ID
     * @return 信息
     */
	 Invigilator selectInvigilatorById(String teachid);
	
	/**
     * 查询列表
     * 
     * @param invigilator 信息
     * @return 集合
     */
	 List<Invigilator> selectInvigilatorList(Invigilator invigilator);

	/**
	 * 批量查询列表
	 *
	 * @param idnumber 信息
	 * @return 集合
	 */
	List<Invigilator> findUserListByIdnumberList(@Param("list") List<String> idnumber);

	/**
	 * 批量插入数据
	 * @param Invigilator
	 * @return
	 */
	int insertInvigilatorList(@Param("list")List<Invigilator> Invigilator);
	/**
	 * 批量更新数据
	 * @param Invigilator
	 * @return
	 */
	int updateInvigilatorList(@Param("list")List<Invigilator> Invigilator);



	/**
     * 新增
     * 
     * @param invigilator 信息
     * @return 结果
     */
	 int insertInvigilator(Invigilator invigilator);
	
	/**
     * 修改
     * 
     * @param invigilator 信息
     * @return 结果
     */
	 int updateInvigilator(Invigilator invigilator);
	
	/**
     * 删除
     * 
     * @param teachid ID
     * @return 结果
     */
	 int deleteInvigilatorById(String teachid);
	
	/**
     * 批量删除
     * 
     * @param teachids 需要删除的数据ID
     * @return 结果
     */
	 int deleteInvigilatorByIds(String[] teachids);
	
}