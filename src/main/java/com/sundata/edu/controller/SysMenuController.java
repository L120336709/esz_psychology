package com.sundata.edu.controller;

import com.sundata.edu.domain.SysMenu;
import com.sundata.edu.service.SysMenuService;
import com.sundata.edu.vo.SysMenuVo;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 菜单 信息操作处理
 *
 * @author whj
 * @date 2018-12-20 20:33:28
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "sysMenu";

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping()
    public String sysMenu() {
        return prefix + "/list";
    }

    /**
     * 查询菜单列表
     */
    @RequiresPermissions("sysMenu:list")
    @GetMapping("/list")
    @ResponseBody
    public List<SysMenu> list(SysMenuVo sysMenuVo) {
        return sysMenuService.selectList(sysMenuVo);
    }

    /**
     * 新增菜单
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Integer parentId, ModelMap modelMap) {
        SysMenu menu = null;
        if (0L != parentId) {
            menu = sysMenuService.selectByPrimaryKey(parentId);
        } else {
            menu = new SysMenu();
            menu.setMenuId(0);
            menu.setMenuName("无");
        }
        modelMap.put("sysMenu", menu);
        return prefix + "/add";
    }

    /**
     * 新增保存菜单
     */
    @RequiresPermissions("sysMenu:add")
    //@Log(title = "菜单", action = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(SysMenu sysMenu) {
        sysMenu.setCreated(new Date());
        sysMenu.setUpdated(new Date());
        return toAjax(sysMenuService.insertSelective(sysMenu));
    }

    /**
     * 修改菜单
     */
    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable("menuId") Integer menuId, ModelMap model) {
        SysMenu sysMenu = sysMenuService.selectByPrimaryKey(menuId);
        SysMenu sysParentMenu = sysMenuService.selectByPrimaryKey(sysMenu.getParentId());
        model.put("sysMenu", sysMenu);
        model.put("sysParentMenu", sysParentMenu);
        return prefix + "/edit";
    }

    /**
     * 修改保存菜单
     */
    @RequiresPermissions("sysMenu:edit")
    //@Log(title = "菜单", action = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(SysMenu sysMenu) {
        sysMenu.setUpdated(new Date());
        return toAjax(sysMenuService.updateByPrimaryKeySelective(sysMenu));
    }

    /**
     * 删除菜单
     */
    @RequiresPermissions("sysMenu:remove")
    //@Log(title = "菜单", action = BusinessType.DELETE)
    @PostMapping("/remove/{menuId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("menuId") Integer menuId) {
        return toAjax(sysMenuService.removes(menuId.toString()));
    }

    /**
     * 选择菜单图标
     */
    @GetMapping("/icon")
    public String icon() {
        return prefix + "/icon";
    }

}
