package com.sundata.edu.dao;

import java.util.List;
import com.sundata.edu.domain.Adulttitletable;

/**
 * 成人高考标题Mapper接口
 *
 * @author whj
 * @date 2021-10-25
 */
public interface AdulttitletableMapper
{
    /**
     * 查询成人高考标题
     *
     * @param id 成人高考标题主键
     * @return 成人高考标题
     */
    public Adulttitletable selectAdulttitletableById(Long id);

    /**
     * 查询成人高考标题列表
     *
     * @param adulttitletable 成人高考标题
     * @return 成人高考标题集合
     */
    public List<Adulttitletable> selectAdulttitletableList(Adulttitletable adulttitletable);

    /**
     * 新增成人高考标题
     *
     * @param adulttitletable 成人高考标题
     * @return 结果
     */
    public int insertAdulttitletable(Adulttitletable adulttitletable);

    /**
     * 修改成人高考标题
     *
     * @param adulttitletable 成人高考标题
     * @return 结果
     */
    public int updateAdulttitletable(Adulttitletable adulttitletable);

    /**
     * 删除成人高考标题
     *
     * @param id 成人高考标题主键
     * @return 结果
     */
    public int deleteAdulttitletableById(Long id);

    /**
     * 批量删除成人高考标题
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdulttitletableByIds(String[] ids);
}
