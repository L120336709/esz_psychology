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

import com.sundata.edu.domain.PsyUserstudy;
import com.sundata.edu.service.IPsyUserstudyService;

import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.util.ExcelUtil;
/**
 * 用户课程学习表Controller
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Controller
@RequestMapping("/psychology/userstudy")
public class PsyUserstudyController extends BaseController
{
    private String prefix = "psychology/userstudy";

    @Autowired
    private IPsyUserstudyService psyUserstudyService;
    @Autowired
    private UserinfoService userinfoService;

    @RequiresPermissions("psychology:userstudy:view")
    @GetMapping()
    public String userstudy()
    {
        return prefix + "/userstudy";
    }

    /**
     * 查询用户课程学习表列表
     */
    @RequiresPermissions("psychology:userstudy:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PsyUserstudy psyUserstudy)
    {
        startPage();
        List<PsyUserstudy> list = psyUserstudyService.selectPsyUserstudyList(psyUserstudy);
        return getDataTable(list);
    }

    @PostMapping("/groupByPeoList")
    @ResponseBody
    public TableDataInfo groupByPeoList(PsyUserstudy psyUserstudy)
    {
        startPage();
        List<PsyUserstudy> list = psyUserstudyService.selectPsyUserstudyListGroupBy(psyUserstudy);
        return getDataTable(list);
    }

    @PostMapping("/getJLbyUserId")
    @ResponseBody
    public  List<PsyUserstudy>  getJLbyUserId(PsyUserstudy psyUserstudy)
    {
        UserinfoVo userInfoVO = (UserinfoVo) SecurityUtils.getSubject().getPrincipal();
        String username=userInfoVO.getRealName();
        if(psyUserstudy.getUserId()==null||psyUserstudy.getUserId().equals("")){
            psyUserstudy.setUserId(userInfoVO.getUserId());
        }else {
           Userinfo userinfo= userinfoService.getUserInfoByUserId(psyUserstudy.getUserId());
           if(userinfo==null){
               return null;
           }
            username=userinfo.getRealName();
        }
        List<PsyUserstudy> list = psyUserstudyService.selectPsyUserstudyList(psyUserstudy);
        for(PsyUserstudy psyUserstudy1:list){
            psyUserstudy1.setUserName(username);
        }
        return list;
    }


    /**
     * 导出用户课程学习表列表
     */
    @RequiresPermissions("psychology:userstudy:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PsyUserstudy psyUserstudy)
    {
        List<PsyUserstudy> list = psyUserstudyService.selectPsyUserstudyListGroupBy(psyUserstudy);
        ExcelUtil<PsyUserstudy> util = new ExcelUtil<PsyUserstudy>(PsyUserstudy.class);
        return util.exportExcel(list, "用户课程学习表数据");
    }

    /**
     * 新增用户课程学习表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户课程学习表
     */
    @RequiresPermissions("psychology:userstudy:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PsyUserstudy psyUserstudy)
    {
        return toAjax(psyUserstudyService.insertPsyUserstudy(psyUserstudy));
    }

    /**
     * 修改用户课程学习表
     */
    @RequiresPermissions("psychology:userstudy:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PsyUserstudy psyUserstudy = psyUserstudyService.selectPsyUserstudyById(id);
        mmap.put("psyUserstudy", psyUserstudy);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户课程学习表
     */
    @RequiresPermissions("psychology:userstudy:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PsyUserstudy psyUserstudy)
    {
        return toAjax(psyUserstudyService.updatePsyUserstudy(psyUserstudy));
    }

    /**
     * 删除用户课程学习表
     */
    @RequiresPermissions("psychology:userstudy:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(psyUserstudyService.deletePsyUserstudyByIds(ids));
    }
}
