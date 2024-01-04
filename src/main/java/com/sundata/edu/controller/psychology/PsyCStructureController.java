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

import com.sundata.edu.domain.PsyCStructure;
import com.sundata.edu.service.IPsyCStructureService;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.util.ExcelUtil;

/**
 * 课程结构表Controller
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Controller
@RequestMapping("/psychology/structure")
public class PsyCStructureController extends BaseController
{
    private String prefix = "psychology/structure";

    @Autowired
    private IPsyCStructureService psyCStructureService;

    @RequiresPermissions("psychology:structure:view")
    @GetMapping()
    public String structure()
    {
        return prefix + "/structure";
    }

    /**
     * 查询课程结构表列表
     */
    @RequiresPermissions("psychology:structure:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PsyCStructure psyCStructure)
    {
        startPage();
        List<PsyCStructure> list = psyCStructureService.selectPsyCStructureList(psyCStructure);
        return getDataTable(list);
    }

    /**
     * 导出课程结构表列表
     */
    @RequiresPermissions("psychology:structure:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PsyCStructure psyCStructure)
    {
        List<PsyCStructure> list = psyCStructureService.selectPsyCStructureList(psyCStructure);
        ExcelUtil<PsyCStructure> util = new ExcelUtil<PsyCStructure>(PsyCStructure.class);
        return util.exportExcel(list, "课程结构表数据");
    }

    /**
     * 新增课程结构表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存课程结构表
     */
    @RequiresPermissions("psychology:structure:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PsyCStructure psyCStructure)
    {
        return toAjax(psyCStructureService.insertPsyCStructure(psyCStructure));
    }

    /**
     * 修改课程结构表
     */
    @RequiresPermissions("psychology:structure:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PsyCStructure psyCStructure = psyCStructureService.selectPsyCStructureById(id);
        mmap.put("psyCStructure", psyCStructure);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程结构表
     */
    @RequiresPermissions("psychology:structure:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PsyCStructure psyCStructure)
    {
        return toAjax(psyCStructureService.updatePsyCStructure(psyCStructure));
    }

    /**
     * 删除课程结构表
     */
    @RequiresPermissions("psychology:structure:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(psyCStructureService.deletePsyCStructureByIds(ids));
    }
}
