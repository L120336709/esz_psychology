package com.sundata.edu.controller.psychology;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sundata.edu.domain.PsyXExercises;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.service.IPsyXExercisesService;
import com.sundata.edu.util.DateUttils;
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
import com.sundata.edu.domain.PsyXExamination;
import com.sundata.edu.service.IPsyXExaminationService;

/**
 * 考试表Controller
 * 
 * @author ljg
 * @date 2023-11-24
 */
@Controller
@RequestMapping("/psychology/examination")
public class PsyXExaminationController extends BaseController
{
    private String prefix = "psychology/examination";

    @Autowired
    private IPsyXExaminationService psyXExaminationService;

    @RequiresPermissions("psychology:examination:view")
    @GetMapping()
    public String examination()
    {
        return prefix + "/examination";
    }

    @Autowired
    private IPsyXExercisesService psyXExercisesService;




    @PostMapping("/getExam")
    @ResponseBody
    public AjaxResult getExam(PsyXExamination psyXExamination)
    {
        //查询考试信息，
        PsyXExercises psyXExercises0=new PsyXExercises();
        psyXExercises0.setKcId(psyXExamination.getKcId());
        List<PsyXExercises> psyXExercisesList= psyXExercisesService.selectPsyXExercisesList(psyXExercises0);

        AjaxResult ajaxResult=new AjaxResult();
        if(psyXExercisesList.size()>0){
            psyXExamination.setKcName(psyXExercisesList.get(0).getKcName());
            psyXExamination.setStartTime(DateUttils.getNowDate());
            ajaxResult.put("psyXExamination",psyXExamination);

            if(psyXExercisesList.size()<25){
                psyXExamination.setQuesTotalnum(psyXExercisesList.size());
                ajaxResult.put("psyXExercisesList",psyXExercisesList);
                String timu=null;
                for(PsyXExercises psyXExercises:psyXExercisesList){
                    if(timu==null){
                        timu=psyXExercises.getId()+"";
                    }else {
                        timu=timu+","+psyXExercises.getId();
                    }
                }
                psyXExamination.setQuesList(timu);

                return ajaxResult;
            }

            List<PsyXExercises> psyXExercisesLists=new ArrayList<>();

            HashSet h=new HashSet();
            boolean boo=false;
            while (boo==false){
                int num1 = (int)(Math.random() * (psyXExercisesList.size())) ;
                h.add(num1);
                if(h.size()==25){
                    boo=true;
                }
            }
            for(Object a:h){
                psyXExercisesLists.add(psyXExercisesList.get((int)a));
            }



            String timu=null;
            for(PsyXExercises psyXExercises:psyXExercisesLists){
                if(timu==null){
                    timu=psyXExercises.getId()+"";
                }else {
                    timu=timu+","+psyXExercises.getId();
                }
            }
            psyXExamination.setQuesList(timu);
            psyXExamination.setQuesTotalnum(psyXExercisesLists.size());
            ajaxResult.put("psyXExercisesList",psyXExercisesLists);
        }else {

        }
        return ajaxResult;
    }


    @PostMapping("/getlist")
    @ResponseBody
    public  List<PsyXExamination> getlist(PsyXExamination psyXExamination)
    {
        if(psyXExamination.getUserId()==null||psyXExamination.getUserId().equals("")){
            UserinfoVo userInfoVO = (UserinfoVo) SecurityUtils.getSubject().getPrincipal();
            psyXExamination.setUserId(userInfoVO.getUserId());
        }
        List<PsyXExamination> list = psyXExaminationService.selectPsyXExaminationList(psyXExamination);
        return list;
    }


    /**
     * 查询考试表列表
     */
    @RequiresPermissions("psychology:examination:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PsyXExamination psyXExamination)
    {
        startPage();
        List<PsyXExamination> list = psyXExaminationService.selectPsyXExaminationList(psyXExamination);
        return getDataTable(list);
    }

    /**
     * 导出考试表列表
     */
    @RequiresPermissions("psychology:examination:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PsyXExamination psyXExamination)
    {
        List<PsyXExamination> list = psyXExaminationService.selectPsyXExaminationList(psyXExamination);
        ExcelUtil<PsyXExamination> util = new ExcelUtil<PsyXExamination>(PsyXExamination.class);
        return util.exportExcel(list, "考试表数据");
    }

    /**
     * 新增考试表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }


    @PostMapping("/saveExam")
    @ResponseBody
    public AjaxResult saveExam( String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PsyXExamination psyXExamination = objectMapper.readValue(jsonString, PsyXExamination.class);
        UserinfoVo userInfoVO = (UserinfoVo) SecurityUtils.getSubject().getPrincipal();
        psyXExamination.setUserId(userInfoVO.getUserId());
        psyXExamination.setEndTime(DateUttils.getNowDate());
        return toAjax(psyXExaminationService.insertPsyXExamination(psyXExamination));
    }

    /**
     * 新增保存考试表
     */
    @RequiresPermissions("psychology:examination:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PsyXExamination psyXExamination)
    {
        return toAjax(psyXExaminationService.insertPsyXExamination(psyXExamination));
    }

    /**
     * 修改考试表
     */
    @RequiresPermissions("psychology:examination:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PsyXExamination psyXExamination = psyXExaminationService.selectPsyXExaminationById(id);
        mmap.put("psyXExamination", psyXExamination);
        return prefix + "/edit";
    }

    /**
     * 修改保存考试表
     */
    @RequiresPermissions("psychology:examination:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PsyXExamination psyXExamination)
    {
        return toAjax(psyXExaminationService.updatePsyXExamination(psyXExamination));
    }

    /**
     * 删除考试表
     */
    @RequiresPermissions("psychology:examination:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(psyXExaminationService.deletePsyXExaminationByIds(ids));
    }
}
