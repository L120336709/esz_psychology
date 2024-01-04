package com.sundata.edu.service.impl;

import java.util.Date;
import java.util.List;

import com.sundata.edu.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sundata.edu.dao.PsyCStructureMapper;
import com.sundata.edu.domain.PsyCStructure;
import com.sundata.edu.service.IPsyCStructureService;


/**
 * 课程结构表Service业务层处理
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Service
public class PsyCStructureServiceImpl implements IPsyCStructureService 
{
    @Autowired
    private PsyCStructureMapper psyCStructureMapper;

    /**
     * 查询课程结构表
     * 
     * @param id 课程结构表主键
     * @return 课程结构表
     */
    @Override
    public PsyCStructure selectPsyCStructureById(Long id)
    {
        return psyCStructureMapper.selectPsyCStructureById(id);
    }

    /**
     * 查询课程结构表列表
     * 
     * @param psyCStructure 课程结构表
     * @return 课程结构表
     */
    @Override
    public List<PsyCStructure> selectPsyCStructureList(PsyCStructure psyCStructure)
    {
        return psyCStructureMapper.selectPsyCStructureList(psyCStructure);
    }

    /**
     * 新增课程结构表
     * 
     * @param psyCStructure 课程结构表
     * @return 结果
     */
    @Override
    public int insertPsyCStructure(PsyCStructure psyCStructure)
    {
        psyCStructure.setCreateTime(new Date());
        return psyCStructureMapper.insertPsyCStructure(psyCStructure);
    }

    /**
     * 修改课程结构表
     * 
     * @param psyCStructure 课程结构表
     * @return 结果
     */
    @Override
    public int updatePsyCStructure(PsyCStructure psyCStructure)
    {
        psyCStructure.setUpdateTime(new Date());
        return psyCStructureMapper.updatePsyCStructure(psyCStructure);
    }

    /**
     * 批量删除课程结构表
     * 
     * @param ids 需要删除的课程结构表主键
     * @return 结果
     */
    @Override
    public int deletePsyCStructureByIds(String ids)
    {
        return psyCStructureMapper.deletePsyCStructureByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除课程结构表信息
     * 
     * @param id 课程结构表主键
     * @return 结果
     */
    @Override
    public int deletePsyCStructureById(Long id)
    {
        return psyCStructureMapper.deletePsyCStructureById(id);
    }
}
