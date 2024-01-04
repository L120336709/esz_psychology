package com.sundata.edu.controller.psychology;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sundata.edu.domain.PsyCUnits;
import com.sundata.edu.service.IPsyCUnitsService;

import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.util.ExcelUtil;
/**
 * 课程课件列表Controller
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Controller
@RequestMapping("/psychology/units")
public class PsyCUnitsController extends BaseController
{
    private String prefix = "psychology/units";

    @Autowired
    private IPsyCUnitsService psyCUnitsService;

    @RequiresPermissions("psychology:units:view")
    @GetMapping()
    public String units()
    {
        return prefix + "/units";
    }

    /**
     * 查询课程课件列表列表
     */
    @RequiresPermissions("psychology:units:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PsyCUnits psyCUnits)
    {
        startPage();
        List<PsyCUnits> list = psyCUnitsService.selectPsyCUnitsList(psyCUnits);
        return getDataTable(list);
    }

    /**
     * 导出课程课件列表列表
     */
    @RequiresPermissions("psychology:units:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PsyCUnits psyCUnits)
    {
        List<PsyCUnits> list = psyCUnitsService.selectPsyCUnitsList(psyCUnits);
        ExcelUtil<PsyCUnits> util = new ExcelUtil<PsyCUnits>(PsyCUnits.class);
        return util.exportExcel(list, "课程课件列表数据");
    }

    /**
     * 新增课程课件列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存课程课件列表
     */
    @RequiresPermissions("psychology:units:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PsyCUnits psyCUnits)
    {
        return toAjax(psyCUnitsService.insertPsyCUnits(psyCUnits));
    }

    /**
     * 修改课程课件列表
     */
    @RequiresPermissions("psychology:units:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PsyCUnits psyCUnits = psyCUnitsService.selectPsyCUnitsById(id);
        mmap.put("psyCUnits", psyCUnits);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程课件列表
     */
    @RequiresPermissions("psychology:units:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PsyCUnits psyCUnits)
    {
        return toAjax(psyCUnitsService.updatePsyCUnits(psyCUnits));
    }

    /**
     * 删除课程课件列表
     */
    @RequiresPermissions("psychology:units:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(psyCUnitsService.deletePsyCUnitsByIds(ids));
    }
}
