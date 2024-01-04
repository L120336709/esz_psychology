package com.sundata.edu.service;

import java.util.List;
import com.sundata.edu.domain.PsyUserjd;

/**
 * 课程章节学习进度表Service接口
 * 
 * @author ljg
 * @date 2023-11-14
 */
public interface IPsyUserjdService 
{
    /**
     * 查询课程章节学习进度表
     * 
     * @param id 课程章节学习进度表主键
     * @return 课程章节学习进度表
     */
    public PsyUserjd selectPsyUserjdById(Long id);

    /**
     * 查询课程章节学习进度表列表
     * 
     * @param psyUserjd 课程章节学习进度表
     * @return 课程章节学习进度表集合
     */
    public List<PsyUserjd> selectPsyUserjdList(PsyUserjd psyUserjd);

    /**
     * 新增课程章节学习进度表
     * 
     * @param psyUserjd 课程章节学习进度表
     * @return 结果
     */
    public int insertPsyUserjd(PsyUserjd psyUserjd);

    /**
     * 修改课程章节学习进度表
     * 
     * @param psyUserjd 课程章节学习进度表
     * @return 结果
     */
    public int updatePsyUserjd(PsyUserjd psyUserjd);

    /**
     * 批量删除课程章节学习进度表
     * 
     * @param ids 需要删除的课程章节学习进度表主键集合
     * @return 结果
     */
    public int deletePsyUserjdByIds(String ids);

    /**
     * 删除课程章节学习进度表信息
     * 
     * @param id 课程章节学习进度表主键
     * @return 结果
     */
    public int deletePsyUserjdById(Long id);
}
