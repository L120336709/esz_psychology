package com.sundata.edu.dao;

import java.util.List;
import com.sundata.edu.domain.PsyCUnits;
import org.apache.ibatis.annotations.Param;

/**
 * 课程课件列表Mapper接口
 * 
 * @author ljg
 * @date 2023-11-09
 */
public interface PsyCUnitsMapper 
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


    List<PsyCUnits> selectPsyCUnitsListBylessonsId(@Param("list") List<Long> lessonsId);
    List<PsyCUnits> selectPsyCUnitsListByIds(@Param("list") List<String> id);


    /**
     * 批量插入数据
     * @param psyCUnits
     * @return
     */
    int insertPsyCUnitsList(@Param("list")List<PsyCUnits> psyCUnits);
    /**
     * 批量更新数据
     * @param psyCUnits
     * @return
     */
    int updatePsyCUnitsList(@Param("list")List<PsyCUnits> psyCUnits);

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
     * 删除课程课件列表
     * 
     * @param id 课程课件列表主键
     * @return 结果
     */
    public int deletePsyCUnitsById(Long id);

    /**
     * 批量删除课程课件列表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyCUnitsByIds(String[] ids);
}
