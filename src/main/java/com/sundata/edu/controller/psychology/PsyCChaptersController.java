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

import com.sundata.edu.domain.PsyCChapters;
import com.sundata.edu.service.IPsyCChaptersService;


/**
 * 课程章节列表Controller
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Controller
@RequestMapping("/psychology/chapters")
public class PsyCChaptersController extends BaseController
{
    private String prefix = "psychology/chapters";

    @Autowired
    private IPsyCChaptersService psyCChaptersService;

    @RequiresPermissions("psychology:chapters:view")
    @GetMapping()
    public String chapters()
    {
        return prefix + "/chapters";
    }

    /**
     * 查询课程章节列表列表
     */
    @RequiresPermissions("psychology:chapters:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PsyCChapters psyCChapters)
    {
        startPage();
        List<PsyCChapters> list = psyCChaptersService.selectPsyCChaptersList(psyCChapters);
        return getDataTable(list);
    }

    /**
     * 导出课程章节列表列表
     */
    @RequiresPermissions("psychology:chapters:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PsyCChapters psyCChapters)
    {
        List<PsyCChapters> list = psyCChaptersService.selectPsyCChaptersList(psyCChapters);
        ExcelUtil<PsyCChapters> util = new ExcelUtil<PsyCChapters>(PsyCChapters.class);
        return util.exportExcel(list, "课程章节列表数据");
    }

    /**
     * 新增课程章节列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存课程章节列表
     */
    @RequiresPermissions("psychology:chapters:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PsyCChapters psyCChapters)
    {
        return toAjax(psyCChaptersService.insertPsyCChapters(psyCChapters));
    }

    /**
     * 修改课程章节列表
     */
    @RequiresPermissions("psychology:chapters:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PsyCChapters psyCChapters = psyCChaptersService.selectPsyCChaptersById(id);
        mmap.put("psyCChapters", psyCChapters);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程章节列表
     */
    @RequiresPermissions("psychology:chapters:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PsyCChapters psyCChapters)
    {
        return toAjax(psyCChaptersService.updatePsyCChapters(psyCChapters));
    }

    /**
     * 删除课程章节列表
     */
    @RequiresPermissions("psychology:chapters:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(psyCChaptersService.deletePsyCChaptersByIds(ids));
    }
}
