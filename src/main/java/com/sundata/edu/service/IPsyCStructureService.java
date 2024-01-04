package com.sundata.edu.service;

import java.util.List;
import com.sundata.edu.domain.PsyCStructure;

/**
 * 课程结构表Service接口
 * 
 * @author ljg
 * @date 2023-11-09
 */
public interface IPsyCStructureService 
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
     * 批量删除课程结构表
     * 
     * @param ids 需要删除的课程结构表主键集合
     * @return 结果
     */
    public int deletePsyCStructureByIds(String ids);

    /**
     * 删除课程结构表信息
     * 
     * @param id 课程结构表主键
     * @return 结果
     */
    public int deletePsyCStructureById(Long id);
}
