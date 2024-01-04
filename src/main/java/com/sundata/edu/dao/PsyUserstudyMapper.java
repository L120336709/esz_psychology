package com.sundata.edu.dao;

import java.util.List;
import com.sundata.edu.domain.PsyUserstudy;

/**
 * 用户课程学习表Mapper接口
 * 
 * @author ljg
 * @date 2023-11-09
 */
public interface PsyUserstudyMapper 
{
    /**
     * 查询用户课程学习表
     * 
     * @param id 用户课程学习表主键
     * @return 用户课程学习表
     */
    public PsyUserstudy selectPsyUserstudyById(Long id);

    /**
     * 查询用户课程学习表列表
     * 
     * @param psyUserstudy 用户课程学习表
     * @return 用户课程学习表集合
     */
    public List<PsyUserstudy> selectPsyUserstudyList(PsyUserstudy psyUserstudy);

    public List<PsyUserstudy> selectPsyUserstudyListGroupBy(PsyUserstudy psyUserstudy);



    /**
     * 新增用户课程学习表
     * 
     * @param psyUserstudy 用户课程学习表
     * @return 结果
     */
    public int insertPsyUserstudy(PsyUserstudy psyUserstudy);

    /**
     * 修改用户课程学习表
     * 
     * @param psyUserstudy 用户课程学习表
     * @return 结果
     */
    public int updatePsyUserstudy(PsyUserstudy psyUserstudy);

    /**
     * 删除用户课程学习表
     * 
     * @param id 用户课程学习表主键
     * @return 结果
     */
    public int deletePsyUserstudyById(Long id);

    /**
     * 批量删除用户课程学习表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyUserstudyByIds(String[] ids);
}
