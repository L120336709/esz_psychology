package com.sundata.edu.dao;

import java.util.List;
import com.sundata.edu.domain.PsyCCourse;

/**
 * 课程表Mapper接口
 * 
 * @author ljg
 * @date 2023-11-09
 */
public interface PsyCCourseMapper 
{
    /**
     * 查询课程表
     * 
     * @param id 课程表主键
     * @return 课程表
     */
    public PsyCCourse selectPsyCCourseById(Long id);

    /**
     * 查询课程表列表
     * 
     * @param psyCCourse 课程表
     * @return 课程表集合
     */
    public List<PsyCCourse> selectPsyCCourseList(PsyCCourse psyCCourse);

    /**
     * 新增课程表
     * 
     * @param psyCCourse 课程表
     * @return 结果
     */
    public int insertPsyCCourse(PsyCCourse psyCCourse);

    /**
     * 修改课程表
     * 
     * @param psyCCourse 课程表
     * @return 结果
     */
    public int updatePsyCCourse(PsyCCourse psyCCourse);

    /**
     * 删除课程表
     * 
     * @param id 课程表主键
     * @return 结果
     */
    public int deletePsyCCourseById(Long id);

    /**
     * 批量删除课程表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyCCourseByIds(String[] ids);
}
