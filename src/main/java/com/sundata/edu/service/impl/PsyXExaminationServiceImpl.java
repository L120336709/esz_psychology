package com.sundata.edu.service.impl;

import java.util.List;

import com.sundata.edu.dao.PsyXExaminationMapper;
import com.sundata.edu.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sundata.edu.domain.PsyXExamination;
import com.sundata.edu.service.IPsyXExaminationService;

/**
 * 考试表Service业务层处理
 * 
 * @author ljg
 * @date 2023-11-24
 */
@Service
public class PsyXExaminationServiceImpl implements IPsyXExaminationService 
{
    @Autowired
    private PsyXExaminationMapper psyXExaminationMapper;

    /**
     * 查询考试表
     * 
     * @param id 考试表主键
     * @return 考试表
     */
    @Override
    public PsyXExamination selectPsyXExaminationById(Long id)
    {
        return psyXExaminationMapper.selectPsyXExaminationById(id);
    }

    /**
     * 查询考试表列表
     * 
     * @param psyXExamination 考试表
     * @return 考试表
     */
    @Override
    public List<PsyXExamination> selectPsyXExaminationList(PsyXExamination psyXExamination)
    {
        return psyXExaminationMapper.selectPsyXExaminationList(psyXExamination);
    }

    /**
     * 新增考试表
     * 
     * @param psyXExamination 考试表
     * @return 结果
     */
    @Override
    public int insertPsyXExamination(PsyXExamination psyXExamination)
    {
        return psyXExaminationMapper.insertPsyXExamination(psyXExamination);
    }

    /**
     * 修改考试表
     * 
     * @param psyXExamination 考试表
     * @return 结果
     */
    @Override
    public int updatePsyXExamination(PsyXExamination psyXExamination)
    {
        return psyXExaminationMapper.updatePsyXExamination(psyXExamination);
    }

    /**
     * 批量删除考试表
     * 
     * @param ids 需要删除的考试表主键
     * @return 结果
     */
    @Override
    public int deletePsyXExaminationByIds(String ids)
    {
        return psyXExaminationMapper.deletePsyXExaminationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除考试表信息
     * 
     * @param id 考试表主键
     * @return 结果
     */
    @Override
    public int deletePsyXExaminationById(Long id)
    {
        return psyXExaminationMapper.deletePsyXExaminationById(id);
    }
}
