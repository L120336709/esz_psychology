package com.sundata.edu.controller;

import java.util.List;


import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sundata.edu.domain.Adulttitletable;
import com.sundata.edu.service.IAdulttitletableService;
/**
 * 成人高考标题Controller
 *
 * @author whj
 * @date 2021-10-25
 */
@Controller
@RequestMapping("/adulttitletable")
public class AdulttitletableController extends BaseController
{
    private String prefix = "/adulttitletable";
    @Autowired
    private IAdulttitletableService adulttitletableService;


    @GetMapping()
    public String adulttitletable()
    {
        return prefix + "/list";
    }

    /**
     * 查询成人高考标题列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Adulttitletable adulttitletable)
    {
        startPage();
        List<Adulttitletable> list = adulttitletableService.selectAdulttitletableList(adulttitletable);
        return getDataTable(list);
    }


    /**
     * 查询根据查询成人高考标题
     */
    @RequestMapping("/table/selectEndurance")
    @ResponseBody
    public AjaxResult selectEndurance(Long id){
        Adulttitletable adulttitletable = new Adulttitletable();
        adulttitletable.setId(id);
        List<Adulttitletable> list = adulttitletableService.selectAdulttitletableList(adulttitletable);
        if(list != null){
            return AjaxResult.success("获取成功").put("data",list.get(0));
        }else{
            return AjaxResult.error();
        }
    }

    /**
     * 新增成人高考标题
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存成人高考标题
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Adulttitletable adulttitletable)
    {
        return toAjax(adulttitletableService.insertAdulttitletable(adulttitletable));
    }

    /**
     * 修改成人高考标题
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Adulttitletable adulttitletable = adulttitletableService.selectAdulttitletableById(id);
        mmap.put("adulttitletable", adulttitletable);
        return prefix + "/edit";
    }

    /**
     * 修改保存成人高考标题
     */
 //   @RequiresPermissions("system:adulttitletable:edit")
   // @Log(title = "成人高考标题", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Adulttitletable adulttitletable)
    {
        return toAjax(adulttitletableService.updateAdulttitletable(adulttitletable));
    }

    /**
     * 删除成人高考标题
     */
}
