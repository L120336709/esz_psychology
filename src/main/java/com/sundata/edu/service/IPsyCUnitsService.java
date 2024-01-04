package com.sundata.edu.service;

import java.util.List;
import com.sundata.edu.domain.PsyCUnits;

/**
 * 课程课件列表Service接口
 * 
 * @author ljg
 * @date 2023-11-09
 */
public interface IPsyCUnitsService 
{
    /**
     * 查询课程课件列表
     * 
     * @param id 课程课件列表主键
     * @return 课程课件列表
     */
    public PsyCUnits selectPsyCUnitsById(Long id);

    /**
     * 查询课程课件列表列表
     * 
     * @param psyCUnits 课程课件列表
     * @return 课程课件列表集合
     */
    public List<PsyCUnits> selectPsyCUnitsList(PsyCUnits psyCUnits);

    /**
     * 新增课程课件列表
     * 
     * @param psyCUnits 课程课件列表
     * @return 结果
     */
    public int insertPsyCUnits(PsyCUnits psyCUnits);

    /**
     * 修改课程课件列表
     * 
     * @param psyCUnits 课程课件列表
     * @return 结果
     */
    public int updatePsyCUnits(PsyCUnits psyCUnits);

    /**
     * 批量删除课程课件列表
     * 
     * @param ids 需要删除的课程课件列表主键集合
     * @return 结果
     */
    public int deletePsyCUnitsByIds(String ids);

    /**
     * 删除课程课件列表信息
     * 
     * @param id 课程课件列表主键
     * @return 结果
     */
    public int deletePsyCUnitsById(Long id);
}
