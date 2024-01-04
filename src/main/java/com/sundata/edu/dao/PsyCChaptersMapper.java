package com.sundata.edu.dao;

import java.util.List;
import com.sundata.edu.domain.PsyCChapters;
import org.apache.ibatis.annotations.Param;

/**
 * 课程章节列表Mapper接口
 * 
 * @author ljg
 * @date 2023-11-09
 */
public interface PsyCChaptersMapper 
{
    /**
     * 查询课程章节列表
     * 
     * @param id 课程章节列表主键
     * @return 课程章节列表
     */
    public PsyCChapters selectPsyCChaptersById(Long id);

    List<PsyCChapters> selectPsyCChaptersListByIds(@Param("list") List<String> id);


    /**
     * 批量插入数据
     * @param psyCChapters
     * @return
     */
    int insertPsyCChaptersList(@Param("list")List<PsyCChapters> psyCChapters);
    /**
     * 批量更新数据
     * @param psyCChapters
     * @return
     */
    int updatePsyCChaptersList(@Param("list")List<PsyCChapters> psyCChapters);
    /**
     * 查询课程章节列表列表
     * 
     * @param psyCChapters 课程章节列表
     * @return 课程章节列表集合
     */
    public List<PsyCChapters> selectPsyCChaptersList(PsyCChapters psyCChapters);

    /**
     * 新增课程章节列表
     * 
     * @param psyCChapters 课程章节列表
     * @return 结果
     */
    public int insertPsyCChapters(PsyCChapters psyCChapters);

    /**
     * 修改课程章节列表
     * 
     * @param psyCChapters 课程章节列表
     * @return 结果
     */
    public int updatePsyCChapters(PsyCChapters psyCChapters);

    /**
     * 删除课程章节列表
     * 
     * @param id 课程章节列表主键
     * @return 结果
     */
    public int deletePsyCChaptersById(Long id);

    /**
     * 批量删除课程章节列表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyCChaptersByIds(String[] ids);
}
