package com.sundata.edu.service.impl;

import java.util.Date;
import java.util.List;

import com.sundata.edu.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sundata.edu.dao.PsyCChaptersMapper;
import com.sundata.edu.domain.PsyCChapters;
import com.sundata.edu.service.IPsyCChaptersService;


/**
 * 课程章节列表Service业务层处理
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Service
public class PsyCChaptersServiceImpl implements IPsyCChaptersService 
{
    @Autowired
    private PsyCChaptersMapper psyCChaptersMapper;

    /**
     * 查询课程章节列表
     * 
     * @param id 课程章节列表主键
     * @return 课程章节列表
     */
    @Override
    public PsyCChapters selectPsyCChaptersById(Long id)
    {
        return psyCChaptersMapper.selectPsyCChaptersById(id);
    }

    /**
     * 查询课程章节列表列表
     * 
     * @param psyCChapters 课程章节列表
     * @return 课程章节列表
     */
    @Override
    public List<PsyCChapters> selectPsyCChaptersList(PsyCChapters psyCChapters)
    {
        return psyCChaptersMapper.selectPsyCChaptersList(psyCChapters);
    }

    /**
     * 新增课程章节列表
     * 
     * @param psyCChapters 课程章节列表
     * @return 结果
     */
    @Override
    public int insertPsyCChapters(PsyCChapters psyCChapters)
    {
        psyCChapters.setCreateTime(new Date());
        return psyCChaptersMapper.insertPsyCChapters(psyCChapters);
    }

    /**
     * 修改课程章节列表
     * 
     * @param psyCChapters 课程章节列表
     * @return 结果
     */
    @Override
    public int updatePsyCChapters(PsyCChapters psyCChapters)
    {
        psyCChapters.setUpdateTime(new Date());
        return psyCChaptersMapper.updatePsyCChapters(psyCChapters);
    }

    /**
     * 批量删除课程章节列表
     * 
     * @param ids 需要删除的课程章节列表主键
     * @return 结果
     */
    @Override
    public int deletePsyCChaptersByIds(String ids)
    {
        return psyCChaptersMapper.deletePsyCChaptersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除课程章节列表信息
     * 
     * @param id 课程章节列表主键
     * @return 结果
     */
    @Override
    public int deletePsyCChaptersById(Long id)
    {
        return psyCChaptersMapper.deletePsyCChaptersById(id);
    }
}
