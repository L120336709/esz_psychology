package com.sundata.edu.service;

import java.util.List;
import com.sundata.edu.domain.PsyRecord;

/**
 * 学习记录表Service接口
 * 
 * @author ljg
 * @date 2023-11-09
 */
public interface IPsyRecordService 
{
    /**
     * 查询学习记录表
     * 
     * @param id 学习记录表主键
     * @return 学习记录表
     */
    public PsyRecord selectPsyRecordById(Long id);

    /**
     * 查询学习记录表列表
     * 
     * @param psyRecord 学习记录表
     * @return 学习记录表集合
     */
    public List<PsyRecord> selectPsyRecordList(PsyRecord psyRecord);

    /**
     * 新增学习记录表
     * 
     * @param psyRecord 学习记录表
     * @return 结果
     */
    public int insertPsyRecord(PsyRecord psyRecord);

    /**
     * 修改学习记录表
     * 
     * @param psyRecord 学习记录表
     * @return 结果
     */
    public int updatePsyRecord(PsyRecord psyRecord);

    /**
     * 批量删除学习记录表
     * 
     * @param ids 需要删除的学习记录表主键集合
     * @return 结果
     */
    public int deletePsyRecordByIds(String ids);

    /**
     * 删除学习记录表信息
     * 
     * @param id 学习记录表主键
     * @return 结果
     */
    public int deletePsyRecordById(Long id);
}
