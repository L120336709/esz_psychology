package com.sundata.edu.service.impl;

import java.util.Date;
import java.util.List;
import com.sundata.edu.dao.PsyUserjdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sundata.edu.domain.PsyUserjd;
import com.sundata.edu.service.IPsyUserjdService;
import com.sundata.edu.util.Convert;
/**
 * 课程章节学习进度表Service业务层处理
 * 
 * @author ljg
 * @date 2023-11-14
 */
@Service
public class PsyUserjdServiceImpl implements IPsyUserjdService 
{
    @Autowired
    private PsyUserjdMapper psyUserjdMapper;

    /**
     * 查询课程章节学习进度表
     * 
     * @param id 课程章节学习进度表主键
     * @return 课程章节学习进度表
     */
    @Override
    public PsyUserjd selectPsyUserjdById(Long id)
    {
        return psyUserjdMapper.selectPsyUserjdById(id);
    }

    /**
     * 查询课程章节学习进度表列表
     * 
     * @param psyUserjd 课程章节学习进度表
     * @return 课程章节学习进度表
     */
    @Override
    public List<PsyUserjd> selectPsyUserjdList(PsyUserjd psyUserjd)
    {
        return psyUserjdMapper.selectPsyUserjdList(psyUserjd);
    }

    /**
     * 新增课程章节学习进度表
     * 
     * @param psyUserjd 课程章节学习进度表
     * @return 结果
     */
    @Override
    public int insertPsyUserjd(PsyUserjd psyUserjd)
    {
        psyUserjd.setCreateTime(new Date());
        return psyUserjdMapper.insertPsyUserjd(psyUserjd);
    }

    /**
     * 修改课程章节学习进度表
     * 
     * @param psyUserjd 课程章节学习进度表
     * @return 结果
     */
    @Override
    public int updatePsyUserjd(PsyUserjd psyUserjd)
    {
        return psyUserjdMapper.updatePsyUserjd(psyUserjd);
    }

    /**
     * 批量删除课程章节学习进度表
     * 
     * @param ids 需要删除的课程章节学习进度表主键
     * @return 结果
     */
    @Override
    public int deletePsyUserjdByIds(String ids)
    {
        return psyUserjdMapper.deletePsyUserjdByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除课程章节学习进度表信息
     * 
     * @param id 课程章节学习进度表主键
     * @return 结果
     */
    @Override
    public int deletePsyUserjdById(Long id)
    {
        return psyUserjdMapper.deletePsyUserjdById(id);
    }
}
