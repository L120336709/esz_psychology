package com.sundata.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sundata.edu.dao.SapientialMapper;
import com.sundata.edu.domain.Sapiential;
import com.sundata.edu.framework.exception.ServiceException;
import com.sundata.edu.service.ISapientialService;
import com.sundata.edu.util.Convert;
import com.sundata.edu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


/**
 * 成人高考考场管理Service业务层处理
 *
 * @author 小王
 * @date 2021-10-20
 */
@Service
public class SapientialServiceImpl implements ISapientialService
{
   @Autowired
   private SapientialMapper sapientialMapper;
    /**
     * 查询成人高考考场管理
     *
     * @param id 成人高考考场管理主键
     * @return 成人高考考场管理
     */
    @Override
    public Sapiential selectSapientialById(Long id)
    {
        return sapientialMapper.selectSapientialById(id);
    }

    /**
     * 查询成人高考考场管理列表
     *
     * @param sapiential 成人高考考场管理
     * @return 成人高考考场管理
     */
    @Override
    public List<Sapiential> selectSapientialList(Sapiential sapiential)
    {
        return sapientialMapper.selectSapientialList(sapiential);
    }

    /**
     * 新增成人高考考场管理
     *
     * @param sapiential 成人高考考场管理
     * @return 结果
     */
    @Override
    public int insertSapiential(Sapiential sapiential)
    {
        return sapientialMapper.insertSapiential(sapiential);
    }

    /**
     * 修改成人高考考场管理
     *
     * @param sapiential 成人高考考场管理
     * @return 结果
     */
    @Override
    public int updateSapiential(Sapiential sapiential)
    {
        return sapientialMapper.updateSapiential(sapiential);
    }

    /**
     * 批量删除成人高考考场管理
     *
     * @param ids 需要删除的成人高考考场管理主键
     * @return 结果
     */
    @Override
    public int deleteSapientialByIds(String ids)
    {
        return sapientialMapper.deleteSapientialByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除成人高考考场管理信息
     *
     * @param id 成人高考考场管理主键
     * @return 结果
     */
    @Override
    public int deleteSapientialById(Long id)
    {

        return sapientialMapper.deleteSapientialById(id);
    }

    @Override
    public String importSapiential(List<Sapiential> sapientialList, boolean updateSupport) {
        if (StringUtils.isNull(sapientialList) || sapientialList.size() == 0)
        {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        Map<String,Object> params = new HashMap<>();
        //String password = configService.selectConfigByKey("sys.user.initPassword");
        for (Sapiential sapiential : sapientialList)

        {
            try
            {
                // 验证是否存在这个条数据
                List<Sapiential> selectSapientialList  =this.sapientialMapper.selectSapientialList(sapiential);
                //  SysUser u = userMapper.selectUserByLoginName(user.getLoginName());
                if (CollectionUtils.isEmpty(selectSapientialList))
                {
                    this.insertSapiential(sapiential);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "考生考场信息 "  + " 导入成功");
                }
                else if (updateSupport)
                {

                    this.insertSapiential(sapiential);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "考生考场信息 " + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "考生考场信息 "  + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "考生考场信息 " + " 导入失败：";
                failureMsg.append(msg + e.getMessage());

            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

}




