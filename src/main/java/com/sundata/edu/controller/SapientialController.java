package com.sundata.edu.controller;

import java.util.List;

import com.sundata.edu.domain.Sapiential;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.service.ISapientialService;
import com.sundata.edu.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 成人高考考场管理Controller
 *
 * @author whj
 * @date 2021-10-20
 */
@Controller
@RequestMapping("/sapiential")
public class SapientialController extends BaseController
{
    private String prefix = "/sapiential";
    @Autowired
    private ISapientialService sapientialService;

   // @RequiresPermissions("system:sapiential:view")
    @GetMapping()
    public String sapiential()
    {
        return prefix + "/list";
    }

    /**
     * 查询成人高考考场管理列表
     */
   // @RequiresPermissions("system:sapiential:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Sapiential sapiential)
    {
        startPage();
        List<Sapiential> list = sapientialService.selectSapientialList(sapiential);
        return getDataTable(list);
    }

    /**
     * 导出成人高考考场管理列表
     */
   // @RequiresPermissions("system:sapiential:export")
   // @Log(title = "成人高考考场管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Sapiential sapiential)
    {
        List<Sapiential> list = sapientialService.selectSapientialList(sapiential);
        ExcelUtil<Sapiential> util = new ExcelUtil<Sapiential>(Sapiential.class);
        return util.exportExcel(list, "成人高考考场管理数据");
    }

    /**
     * 导入成人高考考场管理列表
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Sapiential> util = new ExcelUtil<Sapiential>(Sapiential.class);
        List<Sapiential> SapientialList = util.importExcel(file.getInputStream());
        String message = sapientialService.importSapiential(SapientialList, updateSupport);
        return AjaxResult.success(message);
    }
    /**
     * 下载成人高考考场导入模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<Sapiential> util = new ExcelUtil<Sapiential>(Sapiential.class);
        return util.importTemplateExcel("用户数据");
    }
    /**
     * 新增成人高考考场管理
     */
    @GetMapping("/add")
    public String add()
    {

        return prefix + "/add";
    }

    /**
     * 新增保存成人高考考场管理
     */
   // @RequiresPermissions("system:sapiential:add")
   // @Log(title = "成人高考考场管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Sapiential sapiential)
    {

        return toAjax(sapientialService.insertSapiential(sapiential));
    }

    /**
     * 修改成人高考考场管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Sapiential sapiential = sapientialService.selectSapientialById(id);
        mmap.put("sapiential", sapiential);
        return prefix + "/edit";
    }

    /**
     * 修改保存成人高考考场管理
     */
   // @RequiresPermissions("system:sapiential:edit")
  //  @Log(title = "成人高考考场管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Sapiential sapiential)
    {

        return toAjax(sapientialService.updateSapiential(sapiential));
    }

    /**
     * 删除成人高考考场管理
     */
    //@RequiresPermissions("system:sapiential:remove")
   // @Log(title = "成人高考考场管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {

        return toAjax(sapientialService.deleteSapientialByIds(ids));
    }
    /**
     * 根据姓名和准考证查询
     */
    @RequestMapping("/table/selectEndurance")
    @ResponseBody
    public AjaxResult selectEndurance(String examineename,String examregistrationnumber){
        Sapiential sapiential = new Sapiential();
        sapiential.setExamineename(examineename);
        sapiential.setExamregistrationnumber(examregistrationnumber);
        List<Sapiential> list = sapientialService.selectSapientialList(sapiential);
        if(list != null){
            return AjaxResult.success("获取成功").put("data",list.get(0));
        }else{
            return AjaxResult.error();
        }
    }

}
