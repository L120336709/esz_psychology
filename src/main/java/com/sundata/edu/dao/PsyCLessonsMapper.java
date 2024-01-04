package com.sundata.edu.dao;

import java.util.List;
import com.sundata.edu.domain.PsyCLessons;
import org.apache.ibatis.annotations.Param;

/**
 * 课程课节列表Mapper接口
 * 
 * @author ljg
 * @date 2023-11-09
 */
public interface PsyCLessonsMapper 
{
    /**
     * 查询课程课节列表
     * 
     * @param id 课程课节列表主键
     * @return 课程课节列表
     */
    public PsyCLessons selectPsyCLessonsById(Long id);


    List<PsyCLessons> selectPsyCLessonsListByCheaperIds(@Param("list") List<Long> chapersId);
    List<PsyCLessons> selectPsyCLessonsListByIds(@Param("list") List<String> id);


    /**
     * 批量插入数据
     * @param psyCLessons
     * @return
     */
    int insertPsyCLessonsList(@Param("list")List<PsyCLessons> psyCLessons);
    /**
     * 批量更新数据
     * @param psyCLessons
     * @return
     */
    int updatePsyCLessonsList(@Param("list")List<PsyCLessons> psyCLessons);

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
     * 删除课程课节列表
     * 
     * @param id 课程课节列表主键
     * @return 结果
     */
    public int deletePsyCLessonsById(Long id);

    /**
     * 批量删除课程课节列表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyCLessonsByIds(String[] ids);
}
