package com.sundata.edu.service;

import java.util.List;
import com.sundata.edu.domain.PsyCLessons;

/**
 * 课程课节列表Service接口
 * 
 * @author ljg
 * @date 2023-11-09
 */
public interface IPsyCLessonsService 
{
    /**
     * 查询课程课节列表
     * 
     * @param id 课程课节列表主键
     * @return 课程课节列表
     */
    public PsyCLessons selectPsyCLessonsById(Long id);

    /**
     * 查询课程课节列表列表
     * 
     * @param psyCLessons 课程课节列表
     * @return 课程课节列表集合
     */
    public List<PsyCLessons> selectPsyCLessonsList(PsyCLessons psyCLessons);

    /**
     * 新增课程课节列表
     * 
     * @param psyCLessons 课程课节列表
     * @return 结果
     */
    public int insertPsyCLessons(PsyCLessons psyCLessons);

    /**
     * 修改课程课节列表
     * 
     * @param psyCLessons 课程课节列表
     * @return 结果
     */
    public int updatePsyCLessons(PsyCLessons psyCLessons);

    /**
     * 批量删除课程课节列表
     * 
     * @param ids 需要删除的课程课节列表主键集合
     * @return 结果
     */
    public int deletePsyCLessonsByIds(String ids);

    /**
     * 删除课程课节列表信息
     * 
     * @param id 课程课节列表主键
     * @return 结果
     */
    public int deletePsyCLessonsById(Long id);
}
