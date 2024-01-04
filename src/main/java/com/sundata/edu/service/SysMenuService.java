package com.sundata.edu.service;

import com.sundata.edu.domain.SysMenu;
import com.sundata.edu.domain.SysRole;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.SysMenuVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单 服务层
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
public interface SysMenuService extends Service<SysMenu> {

    /**
    * 根据条件查询列表
    * @param sysMenuVo
    * @return
    */
    List<SysMenu> selectList(SysMenuVo sysMenuVo);

    /**
    * 逻辑删除 修改isdelete字段
    *
    * @param ids
    * @return
    */
    int removes(String ids);


    /**
     * 根据用户id获取权限标识
     * @param userId
     * @return
     */
    Set<String> getPermsByUserId(String userId);

    /**
     * 根据用户id获取菜单列表
     * @param userId
     * @return
     */
    List<SysMenuVo> getMenusByUserId(String userId);

    /**
     * 加载角色菜单列表树
     * @param role
     * @return
     */
    List<Map<String, Object>> getRoleMenuTreeData(SysRole role);

}
