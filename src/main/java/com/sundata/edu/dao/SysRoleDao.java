package com.sundata.edu.dao;

import com.sundata.edu.domain.SysRole;
import com.sundata.edu.framework.core.Mapper;
import com.sundata.edu.vo.SysRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色 数据层
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
public interface SysRoleDao extends Mapper<SysRole> {

    /**
     * 根据用户id获取角色列表
     *
     * @param userId
     * @return
     */
    List<SysRole> getRolesByUserId(@Param("userId") String userId);

    /**
     * 根据条件查询列表
     *
     * @param sysRoleVo
     * @return
     */
    List<SysRole> getRoleList(SysRoleVo sysRoleVo);

    List<SysRole> getRoleListByLevel(Integer minLevel);
}
