package com.sundata.edu.dao;

import java.util.List;
import com.sundata.edu.domain.PsyXExamination;

/**
 * 考试表Mapper接口
 * 
 * @author ljg
 * @date 2023-11-24
 */
public interface PsyXExaminationMapper 
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
     * 删除考试表
     * 
     * @param id 考试表主键
     * @return 结果
     */
    public int deletePsyXExaminationById(Long id);

    /**
     * 批量删除考试表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyXExaminationByIds(String[] ids);
}
