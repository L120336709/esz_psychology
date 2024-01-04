package com.sundata.edu.dao;

import com.sundata.edu.domain.SysMenu;
import com.sundata.edu.framework.core.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单 数据层
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
public interface SysMenuDao extends Mapper<SysMenu> {


    /**
     * 根据用户id获取菜单列表
     *
     * @param userId
     * @return
     */
    List<SysMenu> getMenusByUserId(@Param("userId") String userId);

    /**
     * 根据用户id获取菜单列表
     *
     * @param userId
     * @return
     */
    List<SysMenu> getPermsByUserId(@Param("userId") String userId);

    /**
     * 获取可用的菜单列表
     *
     * @return
     */
    List<SysMenu> getAvailableMenuList(@Param("menuIdList") List<Integer> menuIdList);

    /**
     * 获取角色的可用菜单列表
     *
     * @param roleId
     * @return
     */
    List<SysMenu> getRoleMenuList(@Param("roleId") Integer roleId);

}
