package com.sundata.edu.dao;

import java.util.List;
import com.sundata.edu.domain.PsyXExercises;

/**
 * 习题表Mapper接口
 * 
 * @author ljg
 * @date 2023-11-24
 */
public interface PsyXExercisesMapper 
{
    /**
     * 查询习题表
     * 
     * @param id 习题表主键
     * @return 习题表
     */
    public PsyXExercises selectPsyXExercisesById(Long id);

    /**
     * 查询习题表列表
     * 
     * @param psyXExercises 习题表
     * @return 习题表集合
     */
    public List<PsyXExercises> selectPsyXExercisesList(PsyXExercises psyXExercises);

    /**
     * 新增习题表
     * 
     * @param psyXExercises 习题表
     * @return 结果
     */
    public int insertPsyXExercises(PsyXExercises psyXExercises);

    /**
     * 修改习题表
     * 
     * @param psyXExercises 习题表
     * @return 结果
     */
    public int updatePsyXExercises(PsyXExercises psyXExercises);

    /**
     * 删除习题表
     * 
     * @param id 习题表主键
     * @return 结果
     */
    public int deletePsyXExercisesById(Long id);

    /**
     * 批量删除习题表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyXExercisesByIds(String[] ids);
}
