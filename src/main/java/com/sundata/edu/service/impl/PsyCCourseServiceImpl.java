package com.sundata.edu.service.impl;

import java.util.Date;
import java.util.List;

import com.sundata.edu.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sundata.edu.dao.PsyCCourseMapper;
import com.sundata.edu.domain.PsyCCourse;
import com.sundata.edu.service.IPsyCCourseService;


/**
 * 课程表Service业务层处理
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Service
public class PsyCCourseServiceImpl implements IPsyCCourseService 
{
    @Autowired
    private PsyCCourseMapper psyCCourseMapper;

    /**
     * 查询课程表
     * 
     * @param id 课程表主键
     * @return 课程表
     */
    @Override
    public PsyCCourse selectPsyCCourseById(Long id)
    {

        return psyCCourseMapper.selectPsyCCourseById(id);
    }

    /**
     * 查询课程表列表
     * 
     * @param psyCCourse 课程表
     * @return 课程表
     */
    @Override
    public List<PsyCCourse> selectPsyCCourseList(PsyCCourse psyCCourse)
    {
        return psyCCourseMapper.selectPsyCCourseList(psyCCourse);
    }

    /**
     * 新增课程表
     * 
     * @param psyCCourse 课程表
     * @return 结果
     */
    @Override
    public int insertPsyCCourse(PsyCCourse psyCCourse)
    {

        psyCCourse.setCreateTime(new Date());
        return psyCCourseMapper.insertPsyCCourse(psyCCourse);
    }

    /**
     * 修改课程表
     * 
     * @param psyCCourse 课程表
     * @return 结果
     */
    @Override
    public int updatePsyCCourse(PsyCCourse psyCCourse)
    {
        psyCCourse.setUpdateTime(new Date());
        return psyCCourseMapper.updatePsyCCourse(psyCCourse);
    }

    /**
     * 批量删除课程表
     * 
     * @param ids 需要删除的课程表主键
     * @return 结果
     */
    @Override
    public int deletePsyCCourseByIds(String ids)
    {
        return psyCCourseMapper.deletePsyCCourseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除课程表信息
     * 
     * @param id 课程表主键
     * @return 结果
     */
    @Override
    public int deletePsyCCourseById(Long id)
    {
        return psyCCourseMapper.deletePsyCCourseById(id);
    }
}
