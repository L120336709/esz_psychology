package com.sundata.edu.service;

import java.util.List;
import com.sundata.edu.domain.PsyCChapters;

/**
 * 课程章节列表Service接口
 * 
 * @author ljg
 * @date 2023-11-09
 */
public interface IPsyCChaptersService 
{
    /**
     * 查询课程章节列表
     * 
     * @param id 课程章节列表主键
     * @return 课程章节列表
     */
    public PsyCChapters selectPsyCChaptersById(Long id);

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
     * 批量删除课程章节列表
     * 
     * @param ids 需要删除的课程章节列表主键集合
     * @return 结果
     */
    public int deletePsyCChaptersByIds(String ids);

    /**
     * 删除课程章节列表信息
     * 
     * @param id 课程章节列表主键
     * @return 结果
     */
    public int deletePsyCChaptersById(Long id);
}
