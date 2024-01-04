package com.sundata.edu.service.impl;

import java.util.List;

import com.sundata.edu.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sundata.edu.dao.PsyRecordMapper;
import com.sundata.edu.domain.PsyRecord;
import com.sundata.edu.service.IPsyRecordService;


/**
 * 学习记录表Service业务层处理
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Service
public class PsyRecordServiceImpl implements IPsyRecordService 
{
    @Autowired
    private PsyRecordMapper psyRecordMapper;

    /**
     * 查询学习记录表
     * 
     * @param id 学习记录表主键
     * @return 学习记录表
     */
    @Override
    public PsyRecord selectPsyRecordById(Long id)
    {
        return psyRecordMapper.selectPsyRecordById(id);
    }

    /**
     * 查询学习记录表列表
     * 
     * @param psyRecord 学习记录表
     * @return 学习记录表
     */
    @Override
    public List<PsyRecord> selectPsyRecordList(PsyRecord psyRecord)
    {
        return psyRecordMapper.selectPsyRecordList(psyRecord);
    }

    /**
     * 新增学习记录表
     * 
     * @param psyRecord 学习记录表
     * @return 结果
     */
    @Override
    public int insertPsyRecord(PsyRecord psyRecord)
    {
        return psyRecordMapper.insertPsyRecord(psyRecord);
    }

    /**
     * 修改学习记录表
     * 
     * @param psyRecord 学习记录表
     * @return 结果
     */
    @Override
    public int updatePsyRecord(PsyRecord psyRecord)
    {
        return psyRecordMapper.updatePsyRecord(psyRecord);
    }

    /**
     * 批量删除学习记录表
     * 
     * @param ids 需要删除的学习记录表主键
     * @return 结果
     */
    @Override
    public int deletePsyRecordByIds(String ids)
    {
        return psyRecordMapper.deletePsyRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学习记录表信息
     * 
     * @param id 学习记录表主键
     * @return 结果
     */
    @Override
    public int deletePsyRecordById(Long id)
    {
        return psyRecordMapper.deletePsyRecordById(id);
    }
}
