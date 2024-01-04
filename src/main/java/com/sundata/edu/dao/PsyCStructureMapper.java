package com.sundata.edu.dao;

import java.util.List;
import com.sundata.edu.domain.PsyCStructure;

/**
 * 课程结构表Mapper接口
 * 
 * @author ljg
 * @date 2023-11-09
 */
public interface PsyCStructureMapper 
{
    /**
     * 查询课程结构表
     * 
     * @param id 课程结构表主键
     * @return 课程结构表
     */
    public PsyCStructure selectPsyCStructureById(Long id);

    /**
     * 查询课程结构表列表
     * 
     * @param psyCStructure 课程结构表
     * @return 课程结构表集合
     */
    public List<PsyCStructure> selectPsyCStructureList(PsyCStructure psyCStructure);

    /**
     * 新增课程结构表
     * 
     * @param psyCStructure 课程结构表
     * @return 结果
     */
    public int insertPsyCStructure(PsyCStructure psyCStructure);

    /**
     * 修改课程结构表
     * 
     * @param psyCStructure 课程结构表
     * @return 结果
     */
    public int updatePsyCStructure(PsyCStructure psyCStructure);

    /**
     * 删除课程结构表
     * 
     * @param id 课程结构表主键
     * @return 结果
     */
    public int deletePsyCStructureById(Long id);

    /**
     * 批量删除课程结构表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyCStructureByIds(String[] ids);
}
