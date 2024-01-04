package com.sundata.edu.service.impl;


import com.sundata.edu.dao.UserinfoDao;
import com.sundata.edu.domain.SysMenu;
import com.sundata.edu.domain.SysRole;
import com.sundata.edu.enums.StatusEnum;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.SysMenuDao;
import com.sundata.edu.service.SysMenuService;
import com.sundata.edu.util.MenuUtils;
import com.sundata.edu.util.ShiroUtils;
import com.sundata.edu.vo.SysMenuVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * 菜单 服务层实现
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
@Service("sysMenuService")
//@Transactional
public class SysMenuServiceImpl extends AbstractService<SysMenu> implements SysMenuService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private SysMenuDao sysMenuDao;
    @Autowired
    private UserinfoDao userinfoDao;

    @Override
    public List<SysMenu> selectList(SysMenuVo sysMenuVo) {
        Example example = new Example(SysMenu.class);
        Example.Criteria criteria = example.createCriteria();
        if (sysMenuVo.getStatus() != null) {
            criteria.andEqualTo("status", sysMenuVo.getStatus());
        }
        if (sysMenuVo.getMenuName() != null) {
            criteria.andLike("menuName", "%" + sysMenuVo.getMenuName() + "%");
        }
        example.orderBy("menuType").orderBy("sort");
        return sysMenuDao.selectByExample(example);
    }

    @Override
    public int removes(String ids) {
        Example example = new Example(SysMenu.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("menuId", idArray);
        SysMenu sysMenu = new SysMenu();
        sysMenu.setStatus(StatusEnum.DISABLED.code());
        sysMenu.setUpdated(new Date());
        return sysMenuDao.updateByExampleSelective(sysMenu, example);
    }

    /**
     * 根据用户id获取权限标识列表
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getPermsByUserId(String userId) {
        List<SysMenu> sysMenus = sysMenuDao.getPermsByUserId(userId);
        Set<String> perms = new TreeSet<>();
        for (SysMenu sysMenu : sysMenus) {
            if (!StringUtils.isBlank(sysMenu.getPerms()) && !perms.contains(sysMenu.getPerms())) {
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }

    /**
     * 根据用户id获取菜单列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysMenuVo> getMenusByUserId(String userId) {
        List<SysMenu> sysMenus = sysMenuDao.getMenusByUserId(userId);
        List<SysMenuVo> sysMenuVos = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            BeanUtils.copyProperties(sysMenu, sysMenuVo);
            sysMenuVos.add(sysMenuVo);
        }
        return MenuUtils.getChildPerms(sysMenuVos, 0);
    }

    /**
     * 加载角色菜单列表树
     *
     * @param role
     * @return
     */
    @Override
    public List<Map<String, Object>> getRoleMenuTreeData(SysRole role) {
        //获取当前用户所在机构的机构管理员的角色，所有的菜单id
        List<Integer> menuOrgAdmin = new ArrayList<>();
        if(!ShiroUtils.getSubjct().hasRole("admin")) {
            menuOrgAdmin = userinfoDao.getMenuOrgAdmin(ShiroUtils.getOrgId());
        }

        Integer roleId = role.getRoleId();
        List<Map<String, Object>> trees = new ArrayList<>();
        List<SysMenu> menuList = sysMenuDao.getAvailableMenuList(menuOrgAdmin);
        if (roleId != null) {
            List<SysMenu> roleMenuList = sysMenuDao.getRoleMenuList(roleId);
            trees = MenuUtils.getTrees(menuList, true, roleMenuList, true);
        } else {
            trees = MenuUtils.getTrees(menuList, false, null, true);
        }
        return trees;
    }


}
