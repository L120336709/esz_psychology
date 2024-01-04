package com.sundata.edu.controller.psychology;

import java.util.List;

import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.util.ExcelUtil;
import com.sundata.edu.vo.UserinfoVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sundata.edu.domain.PsyUserjd;
import com.sundata.edu.service.IPsyUserjdService;

/**
 * 课程章节学习进度表Controller
 * 
 * @author ljg
 * @date 2023-11-14
 */
@Controller
@RequestMapping("/psychology/userjd")
public class PsyUserjdController extends BaseController
{
    private String prefix = "psychology/userjd";

    @Autowired
    private IPsyUserjdService psyUserjdService;

    @RequiresPermissions("psychology:userjd:view")
    @GetMapping()
    public String userjd()
    {
        return prefix + "/userjd";
    }

    /**
     * 查询课程章节学习进度表列表
     */
    @RequiresPermissions("psychology:userjd:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PsyUserjd psyUserjd)
    {
        startPage();
        List<PsyUserjd> list = psyUserjdService.selectPsyUserjdList(psyUserjd);
        return getDataTable(list);
    }


    @PostMapping("/getPsyUserjdbyUserId")
    @ResponseBody
    public  List<PsyUserjd>  getPsyUserjdbyUserId(PsyUserjd psyUserjd)
    {
        UserinfoVo userInfoVO = (UserinfoVo) SecurityUtils.getSubject().getPrincipal();
        psyUserjd.setUserId(userInfoVO.getUserId());
        List<PsyUserjd> list = psyUserjdService.selectPsyUserjdList(psyUserjd);
        return  list;
    }


    /**
     * 导出课程章节学习进度表列表
     */
    @RequiresPermissions("psychology:userjd:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PsyUserjd psyUserjd)
    {
        List<PsyUserjd> list = psyUserjdService.selectPsyUserjdList(psyUserjd);
        ExcelUtil<PsyUserjd> util = new ExcelUtil<PsyUserjd>(PsyUserjd.class);
        return util.exportExcel(list, "课程章节学习进度表数据");
    }

    /**
     * 新增课程章节学习进度表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存课程章节学习进度表
     */
    @RequiresPermissions("psychology:userjd:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PsyUserjd psyUserjd)
    {
        return toAjax(psyUserjdService.insertPsyUserjd(psyUserjd));
    }

    /**
     * 修改课程章节学习进度表
     */
    @RequiresPermissions("psychology:userjd:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PsyUserjd psyUserjd = psyUserjdService.selectPsyUserjdById(id);
        mmap.put("psyUserjd", psyUserjd);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程章节学习进度表
     */
    @RequiresPermissions("psychology:userjd:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PsyUserjd psyUserjd)
    {
        return toAjax(psyUserjdService.updatePsyUserjd(psyUserjd));
    }

    /**
     * 删除课程章节学习进度表
     */
    @RequiresPermissions("psychology:userjd:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(psyUserjdService.deletePsyUserjdByIds(ids));
    }
}
