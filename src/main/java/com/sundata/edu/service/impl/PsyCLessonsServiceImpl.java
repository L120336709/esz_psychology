package com.sundata.edu.service.impl;

import java.util.Date;
import java.util.List;

import com.sundata.edu.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sundata.edu.dao.PsyCLessonsMapper;
import com.sundata.edu.domain.PsyCLessons;
import com.sundata.edu.service.IPsyCLessonsService;


/**
 * 课程课节列表Service业务层处理
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Service
public class PsyCLessonsServiceImpl implements IPsyCLessonsService 
{
    @Autowired
    private PsyCLessonsMapper psyCLessonsMapper;

    /**
     * 查询课程课节列表
     * 
     * @param id 课程课节列表主键
     * @return 课程课节列表
     */
    @Override
    public PsyCLessons selectPsyCLessonsById(Long id)
    {
        return psyCLessonsMapper.selectPsyCLessonsById(id);
    }

    /**
     * 查询课程课节列表列表
     * 
     * @param psyCLessons 课程课节列表
     * @return 课程课节列表
     */
    @Override
    public List<PsyCLessons> selectPsyCLessonsList(PsyCLessons psyCLessons)
    {
        return psyCLessonsMapper.selectPsyCLessonsList(psyCLessons);
    }

    /**
     * 新增课程课节列表
     * 
     * @param psyCLessons 课程课节列表
     * @return 结果
     */
    @Override
    public int insertPsyCLessons(PsyCLessons psyCLessons)
    {

        psyCLessons.setCreateTime(new Date());
        return psyCLessonsMapper.insertPsyCLessons(psyCLessons);
    }

    /**
     * 修改课程课节列表
     * 
     * @param psyCLessons 课程课节列表
     * @return 结果
     */
    @Override
    public int updatePsyCLessons(PsyCLessons psyCLessons)
    {

        psyCLessons.setUpdateTime(new Date());
        return psyCLessonsMapper.updatePsyCLessons(psyCLessons);
    }

    /**
     * 批量删除课程课节列表
     * 
     * @param ids 需要删除的课程课节列表主键
     * @return 结果
     */
    @Override
    public int deletePsyCLessonsByIds(String ids)
    {
        return psyCLessonsMapper.deletePsyCLessonsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除课程课节列表信息
     * 
     * @param id 课程课节列表主键
     * @return 结果
     */
    @Override
    public int deletePsyCLessonsById(Long id)
    {
        return psyCLessonsMapper.deletePsyCLessonsById(id);
    }
}
