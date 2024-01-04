package com.sundata.edu.service.impl;

import java.util.Date;
import java.util.List;

import com.sundata.edu.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sundata.edu.dao.PsyUserstudyMapper;
import com.sundata.edu.domain.PsyUserstudy;
import com.sundata.edu.service.IPsyUserstudyService;

/**
 * 用户课程学习表Service业务层处理
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Service
public class PsyUserstudyServiceImpl implements IPsyUserstudyService 
{
    @Autowired
    private PsyUserstudyMapper psyUserstudyMapper;

    /**
     * 查询用户课程学习表
     * 
     * @param id 用户课程学习表主键
     * @return 用户课程学习表
     */
    @Override
    public PsyUserstudy selectPsyUserstudyById(Long id)
    {
        return psyUserstudyMapper.selectPsyUserstudyById(id);
    }

    /**
     * 查询用户课程学习表列表
     * 
     * @param psyUserstudy 用户课程学习表
     * @return 用户课程学习表
     */
    @Override
    public List<PsyUserstudy> selectPsyUserstudyList(PsyUserstudy psyUserstudy)
    {
        return psyUserstudyMapper.selectPsyUserstudyList(psyUserstudy);
    }

    @Override
    public List<PsyUserstudy> selectPsyUserstudyListGroupBy(PsyUserstudy psyUserstudy)
    {
        return psyUserstudyMapper.selectPsyUserstudyListGroupBy(psyUserstudy);
    }

    /**
     * 新增用户课程学习表
     * 
     * @param psyUserstudy 用户课程学习表
     * @return 结果
     */
    @Override
    public int insertPsyUserstudy(PsyUserstudy psyUserstudy)
    {
        psyUserstudy.setCreateTime(new Date());
        return psyUserstudyMapper.insertPsyUserstudy(psyUserstudy);
    }

    /**
     * 修改用户课程学习表
     * 
     * @param psyUserstudy 用户课程学习表
     * @return 结果
     */
    @Override
    public int updatePsyUserstudy(PsyUserstudy psyUserstudy)
    {
        psyUserstudy.setUpdateTime(new Date());
        return psyUserstudyMapper.updatePsyUserstudy(psyUserstudy);
    }

    /**
     * 批量删除用户课程学习表
     * 
     * @param ids 需要删除的用户课程学习表主键
     * @return 结果
     */
    @Override
    public int deletePsyUserstudyByIds(String ids)
    {
        return psyUserstudyMapper.deletePsyUserstudyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户课程学习表信息
     * 
     * @param id 用户课程学习表主键
     * @return 结果
     */
    @Override
    public int deletePsyUserstudyById(Long id)
    {
        return psyUserstudyMapper.deletePsyUserstudyById(id);
    }
}
