package com.sundata.edu.controller.psychology;

import java.util.List;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.util.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sundata.edu.domain.PsyCLessons;
import com.sundata.edu.service.IPsyCLessonsService;


/**
 * 课程课节列表Controller
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Controller
@RequestMapping("/psychology/lessons")
public class PsyCLessonsController extends BaseController
{
    private String prefix = "psychology/lessons";

    @Autowired
    private IPsyCLessonsService psyCLessonsService;

    @RequiresPermissions("psychology:lessons:view")
    @GetMapping()
    public String lessons()
    {
        return prefix + "/lessons";
    }

    /**
     * 查询课程课节列表列表
     */
    @RequiresPermissions("psychology:lessons:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PsyCLessons psyCLessons)
    {
        startPage();
        List<PsyCLessons> list = psyCLessonsService.selectPsyCLessonsList(psyCLessons);
        return getDataTable(list);
    }

    /**
     * 导出课程课节列表列表
     */
    @RequiresPermissions("psychology:lessons:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PsyCLessons psyCLessons)
    {
        List<PsyCLessons> list = psyCLessonsService.selectPsyCLessonsList(psyCLessons);
        ExcelUtil<PsyCLessons> util = new ExcelUtil<PsyCLessons>(PsyCLessons.class);
        return util.exportExcel(list, "课程课节列表数据");
    }

    /**
     * 新增课程课节列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存课程课节列表
     */
    @RequiresPermissions("psychology:lessons:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PsyCLessons psyCLessons)
    {
        return toAjax(psyCLessonsService.insertPsyCLessons(psyCLessons));
    }

    /**
     * 修改课程课节列表
     */
    @RequiresPermissions("psychology:lessons:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PsyCLessons psyCLessons = psyCLessonsService.selectPsyCLessonsById(id);
        mmap.put("psyCLessons", psyCLessons);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程课节列表
     */
    @RequiresPermissions("psychology:lessons:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PsyCLessons psyCLessons)
    {
        return toAjax(psyCLessonsService.updatePsyCLessons(psyCLessons));
    }

    /**
     * 删除课程课节列表
     */
    @RequiresPermissions("psychology:lessons:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(psyCLessonsService.deletePsyCLessonsByIds(ids));
    }
}
