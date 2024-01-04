package com.sundata.edu.controller.psychology;

import java.util.List;

import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.service.UserinfoService;
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

import com.sundata.edu.domain.PsyRecord;
import com.sundata.edu.service.IPsyRecordService;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.util.ExcelUtil;

/**
 * 学习记录表Controller
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Controller
@RequestMapping("/psychology/record")
public class PsyRecordController extends BaseController
{
    private String prefix = "psychology/record";

    @Autowired
    private IPsyRecordService psyRecordService;

    @RequiresPermissions("psychology:record:view")
    @GetMapping()
    public String record()
    {
        return prefix + "/record";
    }

    /**
     * 查询学习记录表列表
     */
    @RequiresPermissions("psychology:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PsyRecord psyRecord)
    {
        startPage();
        List<PsyRecord> list = psyRecordService.selectPsyRecordList(psyRecord);
        return getDataTable(list);
    }

    @Autowired
    private UserinfoService userinfoService;

    @PostMapping("/getRecordbyUserId")
    @ResponseBody
    public  List<PsyRecord>  getRecordbyUserId(PsyRecord psyRecord)
    {
        UserinfoVo userInfoVO = (UserinfoVo) SecurityUtils.getSubject().getPrincipal();
        if(psyRecord.getUserId()==null||psyRecord.getUserId().equals("")){
            psyRecord.setUserId(userInfoVO.getUserId());
        }
        List<PsyRecord> list = psyRecordService.selectPsyRecordList(psyRecord);
        return list;
    }
    /**
     * 导出学习记录表列表
     */
    @RequiresPermissions("psychology:record:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PsyRecord psyRecord)
    {
        List<PsyRecord> list = psyRecordService.selectPsyRecordList(psyRecord);
        ExcelUtil<PsyRecord> util = new ExcelUtil<PsyRecord>(PsyRecord.class);
        return util.exportExcel(list, "学习记录表数据");
    }

    /**
     * 新增学习记录表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学习记录表
     */
    @RequiresPermissions("psychology:record:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PsyRecord psyRecord)
    {
        return toAjax(psyRecordService.insertPsyRecord(psyRecord));
    }

    /**
     * 修改学习记录表
     */
    @RequiresPermissions("psychology:record:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PsyRecord psyRecord = psyRecordService.selectPsyRecordById(id);
        mmap.put("psyRecord", psyRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存学习记录表
     */
    @RequiresPermissions("psychology:record:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PsyRecord psyRecord)
    {
        return toAjax(psyRecordService.updatePsyRecord(psyRecord));
    }

    /**
     * 删除学习记录表
     */
    @RequiresPermissions("psychology:record:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(psyRecordService.deletePsyRecordByIds(ids));
    }
}
