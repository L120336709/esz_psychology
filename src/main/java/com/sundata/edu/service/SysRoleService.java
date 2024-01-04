package com.sundata.edu.service;

import com.sundata.edu.domain.SysRole;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.SysRoleVo;

import java.util.List;
import java.util.Set;

/**
 * 角色 服务层
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
public interface SysRoleService extends Service<SysRole> {

    /**
     * 根据条件查询列表
     *
     * @param sysRoleVo
     * @return
     */
    List<SysRole> getRoleList(SysRoleVo sysRoleVo);

    /**
     * 逻辑删除 修改isdelete字段
     *
     * @param ids
     * @return
     */
    int removes(String ids);

    /**
     * 根据角色code查询角色信息
     *
     * @param roleCode
     * @return
     */
    SysRole getSysRoleByRoleCode(String roleCode);

    /**
     * 根据用户id获取角色code列表
     *
     * @param userId
     * @return
     */
    Set<String> getRoleCodeByUserId(String userId);

    /**
     * 根据用户id获取角色列表
     *
     * @param userId
     * @return
     */
    List<SysRole> getRolesByUserId(String userId);

    /**
     * 检查角色编码是否存在
     *
     * @param roleCode
     * @param roleId
     * @return
     */
    boolean checkExistRoleCode(String roleCode, Integer roleId);

    /**
     * 保存角色信息
     *
     * @param sysRoleVo
     * @return
     */
    int addRole(SysRoleVo sysRoleVo);

    /**
     * 编辑角色信息
     *
     * @param sysRoleVo
     * @return
     */
    int editRole(SysRoleVo sysRoleVo);

    List<SysRole> getRoleListByLevel(Integer minLevel);
}
