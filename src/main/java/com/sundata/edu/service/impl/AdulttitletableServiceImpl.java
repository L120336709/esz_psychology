package com.sundata.edu.service.impl;

import java.util.List;

import com.sundata.edu.dao.AdulttitletableMapper;
import com.sundata.edu.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sundata.edu.domain.Adulttitletable;
import com.sundata.edu.service.IAdulttitletableService;


/**
 * 成人高考标题Service业务层处理
 *
 * @author whj
 * @date 2021-10-25
 */
@Service
public class AdulttitletableServiceImpl implements IAdulttitletableService
{
    @Autowired
    private AdulttitletableMapper adulttitletableMapper;

    /**
     * 查询成人高考标题
     *
     * @param id 成人高考标题主键
     * @return 成人高考标题
     */
    @Override
    public Adulttitletable selectAdulttitletableById(Long id)
    {
        return adulttitletableMapper.selectAdulttitletableById(id);
    }

    /**
     * 查询成人高考标题列表
     *
     * @param adulttitletable 成人高考标题
     * @return 成人高考标题
     */
    @Override
    public List<Adulttitletable> selectAdulttitletableList(Adulttitletable adulttitletable)
    {
        return adulttitletableMapper.selectAdulttitletableList(adulttitletable);
    }

    /**
     * 新增成人高考标题
     *
     * @param adulttitletable 成人高考标题
     * @return 结果
     */
    @Override
    public int insertAdulttitletable(Adulttitletable adulttitletable)
    {
        return adulttitletableMapper.insertAdulttitletable(adulttitletable);
    }

    /**
     * 修改成人高考标题
     *
     * @param adulttitletable 成人高考标题
     * @return 结果
     */
    @Override
    public int updateAdulttitletable(Adulttitletable adulttitletable)
    {
        return adulttitletableMapper.updateAdulttitletable(adulttitletable);
    }

    /**
     * 批量删除成人高考标题
     *
     * @param ids 需要删除的成人高考标题主键
     * @return 结果
     */
    @Override
    public int deleteAdulttitletableByIds(String ids)
    {
        return adulttitletableMapper.deleteAdulttitletableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除成人高考标题信息
     *
     * @param id 成人高考标题主键
     * @return 结果
     */
    @Override
    public int deleteAdulttitletableById(Long id)
    {
        return adulttitletableMapper.deleteAdulttitletableById(id);
    }
}
