package com.sundata.edu.service;

import java.util.List;
import com.sundata.edu.domain.PsyXExamination;

/**
 * 考试表Service接口
 * 
 * @author ljg
 * @date 2023-11-24
 */
public interface IPsyXExaminationService 
{
    /**
     * 查询考试表
     * 
     * @param id 考试表主键
     * @return 考试表
     */
    public PsyXExamination selectPsyXExaminationById(Long id);

    /**
     * 查询考试表列表
     * 
     * @param psyXExamination 考试表
     * @return 考试表集合
     */
    public List<PsyXExamination> selectPsyXExaminationList(PsyXExamination psyXExamination);

    /**
     * 新增考试表
     * 
     * @param psyXExamination 考试表
     * @return 结果
     */
    public int insertPsyXExamination(PsyXExamination psyXExamination);

    /**
     * 修改考试表
     * 
     * @param psyXExamination 考试表
     * @return 结果
     */
    public int updatePsyXExamination(PsyXExamination psyXExamination);

    /**
     * 批量删除考试表
     * 
     * @param ids 需要删除的考试表主键集合
     * @return 结果
     */
    public int deletePsyXExaminationByIds(String ids);

    /**
     * 删除考试表信息
     * 
     * @param id 考试表主键
     * @return 结果
     */
    public int deletePsyXExaminationById(Long id);
}
