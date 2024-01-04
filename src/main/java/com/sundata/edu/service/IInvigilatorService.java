package com.sundata.edu.service;

import com.sundata.edu.domain.Invigilator;

import java.util.List;
import java.util.Map;

/**
 *  服务层
 * 
 * @author shunguo
 * @date 2021-12-10
 */
public interface IInvigilatorService 
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

	 String[] randomSorting(String[] Invigilators);
	
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
     * 删除信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	 int deleteInvigilatorByIds(String ids);
	/**
	 * 导入监考人员信息
	 */

	 String InvigilatorListImport(List<Invigilator> InvigilatorList, boolean updateSupport);

}
