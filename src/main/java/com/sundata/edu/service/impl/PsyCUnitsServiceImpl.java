package com.sundata.edu.service.impl;

import java.util.Date;
import java.util.List;

import com.sundata.edu.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sundata.edu.dao.PsyCUnitsMapper;
import com.sundata.edu.domain.PsyCUnits;
import com.sundata.edu.service.IPsyCUnitsService;


/**
 * 课程课件列表Service业务层处理
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Service
public class PsyCUnitsServiceImpl implements IPsyCUnitsService 
{
    @Autowired
    private PsyCUnitsMapper psyCUnitsMapper;

    /**
     * 查询课程课件列表
     * 
     * @param id 课程课件列表主键
     * @return 课程课件列表
     */
    @Override
    public PsyCUnits selectPsyCUnitsById(Long id)
    {
        return psyCUnitsMapper.selectPsyCUnitsById(id);
    }

    /**
     * 查询课程课件列表列表
     * 
     * @param psyCUnits 课程课件列表
     * @return 课程课件列表
     */
    @Override
    public List<PsyCUnits> selectPsyCUnitsList(PsyCUnits psyCUnits)
    {
        return psyCUnitsMapper.selectPsyCUnitsList(psyCUnits);
    }

    /**
     * 新增课程课件列表
     * 
     * @param psyCUnits 课程课件列表
     * @return 结果
     */
    @Override
    public int insertPsyCUnits(PsyCUnits psyCUnits)
    {
        psyCUnits.setCreateTime(new Date());
        return psyCUnitsMapper.insertPsyCUnits(psyCUnits);
    }

    /**
     * 修改课程课件列表
     * 
     * @param psyCUnits 课程课件列表
     * @return 结果
     */
    @Override
    public int updatePsyCUnits(PsyCUnits psyCUnits)
    {

        psyCUnits.setUpdateTime(new Date());
        return psyCUnitsMapper.updatePsyCUnits(psyCUnits);
    }

    /**
     * 批量删除课程课件列表
     * 
     * @param ids 需要删除的课程课件列表主键
     * @return 结果
     */
    @Override
    public int deletePsyCUnitsByIds(String ids)
    {
        return psyCUnitsMapper.deletePsyCUnitsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除课程课件列表信息
     * 
     * @param id 课程课件列表主键
     * @return 结果
     */
    @Override
    public int deletePsyCUnitsById(Long id)
    {
        return psyCUnitsMapper.deletePsyCUnitsById(id);
    }
}
