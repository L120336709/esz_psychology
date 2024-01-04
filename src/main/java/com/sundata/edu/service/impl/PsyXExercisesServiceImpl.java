package com.sundata.edu.service.impl;

import java.util.List;

import com.sundata.edu.dao.PsyXExercisesMapper;
import com.sundata.edu.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sundata.edu.domain.PsyXExercises;
import com.sundata.edu.service.IPsyXExercisesService;

/**
 * 习题表Service业务层处理
 * 
 * @author ljg
 * @date 2023-11-24
 */
@Service
public class PsyXExercisesServiceImpl implements IPsyXExercisesService 
{
    @Autowired
    private PsyXExercisesMapper psyXExercisesMapper;

    /**
     * 查询习题表
     * 
     * @param id 习题表主键
     * @return 习题表
     */
    @Override
    public PsyXExercises selectPsyXExercisesById(Long id)
    {
        return psyXExercisesMapper.selectPsyXExercisesById(id);
    }

    /**
     * 查询习题表列表
     * 
     * @param psyXExercises 习题表
     * @return 习题表
     */
    @Override
    public List<PsyXExercises> selectPsyXExercisesList(PsyXExercises psyXExercises)
    {
        return psyXExercisesMapper.selectPsyXExercisesList(psyXExercises);
    }

    /**
     * 新增习题表
     * 
     * @param psyXExercises 习题表
     * @return 结果
     */
    @Override
    public int insertPsyXExercises(PsyXExercises psyXExercises)
    {
        return psyXExercisesMapper.insertPsyXExercises(psyXExercises);
    }

    /**
     * 修改习题表
     * 
     * @param psyXExercises 习题表
     * @return 结果
     */
    @Override
    public int updatePsyXExercises(PsyXExercises psyXExercises)
    {
        return psyXExercisesMapper.updatePsyXExercises(psyXExercises);
    }

    /**
     * 批量删除习题表
     * 
     * @param ids 需要删除的习题表主键
     * @return 结果
     */
    @Override
    public int deletePsyXExercisesByIds(String ids)
    {
        return psyXExercisesMapper.deletePsyXExercisesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除习题表信息
     * 
     * @param id 习题表主键
     * @return 结果
     */
    @Override
    public int deletePsyXExercisesById(Long id)
    {
        return psyXExercisesMapper.deletePsyXExercisesById(id);
    }
}
