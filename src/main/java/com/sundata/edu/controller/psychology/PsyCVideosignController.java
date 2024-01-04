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

import com.sundata.edu.domain.PsyCVideosign;
import com.sundata.edu.service.IPsyCVideosignService;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.util.ExcelUtil;

/**
 * 课程视频信息表Controller
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Controller
@RequestMapping("/psychology/videosign")
public class PsyCVideosignController extends BaseController
{
    private String prefix = "psychology/videosign";

    @Autowired
    private IPsyCVideosignService psyCVideosignService;

    @RequiresPermissions("psychology:videosign:view")
    @GetMapping()
    public String videosign()
    {
        return prefix + "/videosign";
    }

    /**
     * 查询课程视频信息表列表
     */
    @RequiresPermissions("psychology:videosign:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PsyCVideosign psyCVideosign)
    {
        startPage();
        List<PsyCVideosign> list = psyCVideosignService.selectPsyCVideosignList(psyCVideosign);
        return getDataTable(list);
    }

    /**
     * 导出课程视频信息表列表
     */
    @RequiresPermissions("psychology:videosign:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PsyCVideosign psyCVideosign)
    {
        List<PsyCVideosign> list = psyCVideosignService.selectPsyCVideosignList(psyCVideosign);
        ExcelUtil<PsyCVideosign> util = new ExcelUtil<PsyCVideosign>(PsyCVideosign.class);
        return util.exportExcel(list, "课程视频信息表数据");
    }

    /**
     * 新增课程视频信息表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存课程视频信息表
     */
    @RequiresPermissions("psychology:videosign:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PsyCVideosign psyCVideosign)
    {
        return toAjax(psyCVideosignService.insertPsyCVideosign(psyCVideosign));
    }

    /**
     * 修改课程视频信息表
     */
    @RequiresPermissions("psychology:videosign:edit")
    @GetMapping("/edit/{videoId}")
    public String edit(@PathVariable("videoId") Long videoId, ModelMap mmap)
    {
        PsyCVideosign psyCVideosign = psyCVideosignService.selectPsyCVideosignByVideoId(videoId);
        mmap.put("psyCVideosign", psyCVideosign);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程视频信息表
     */
    @RequiresPermissions("psychology:videosign:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PsyCVideosign psyCVideosign)
    {
        return toAjax(psyCVideosignService.updatePsyCVideosign(psyCVideosign));
    }

    /**
     * 删除课程视频信息表
     */
    @RequiresPermissions("psychology:videosign:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(psyCVideosignService.deletePsyCVideosignByVideoIds(ids));
    }
}
