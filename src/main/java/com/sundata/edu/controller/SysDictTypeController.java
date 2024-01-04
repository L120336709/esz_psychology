package com.sundata.edu.controller;

import com.sundata.edu.domain.SysDictType;
import com.sundata.edu.service.SysDictTypeService;
import com.sundata.edu.vo.SysDictTypeVo;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 字典类型 信息操作处理
 *
 * @author whj
 * @date 2018-12-22 17:52:49
 */
@Controller
@RequestMapping("/sysDictType")
public class SysDictTypeController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "sysDictType";

    @Autowired
    private SysDictTypeService sysDictTypeService;

    //@RequiresPermissions("sysDictType:view")
    @GetMapping()
    public String sysDictType() {
        return prefix + "/list";
    }

    /**
     * 查询字典类型列表
     */
    @RequiresPermissions("sysDictType:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysDictTypeVo sysDictTypeVo) {
        startPage();
        return getDataTable(sysDictTypeService.selectList(sysDictTypeVo));
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存字典类型
     */
    //@RequiresPermissions("sysDictType:add")
    //@Log(title = "字典类型", action = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(SysDictType sysDictType) {
        sysDictType.setCreated(new Date());
        sysDictType.setUpdated(new Date());
        return toAjax(sysDictTypeService.insertSelective(sysDictType));
    }

    /**
     * 修改字典类型
     */
    @GetMapping("/edit/{dictId}")
    public String edit(@PathVariable("dictId") Integer dictId, ModelMap model) {
        model.put("sysDictType", sysDictTypeService.selectByPrimaryKey(dictId));
        return prefix + "/edit";
    }

    /**
     * 修改保存字典类型
     */
    //@RequiresPermissions("sysDictType:edit")
    //@Log(title = "字典类型", action = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(SysDictType sysDictType) {
        sysDictType.setUpdated(new Date());
        return toAjax(sysDictTypeService.updateByPrimaryKeySelective(sysDictType));
    }

    /**
     * 删除字典类型
     */
    //@RequiresPermissions("sysDictType:remove")
    //@Log(title = "字典类型", action = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sysDictTypeService.removes(ids));
    }

    /**
     * 校验字典类型
     */
    @PostMapping("/checkExistSysDictType")
    @ResponseBody
    public boolean checkExistSysDictType(String typeCode, Integer typeId) {
        boolean flag = sysDictTypeService.checkExistSysDictType(typeCode, typeId);
        return flag;
    }

}
