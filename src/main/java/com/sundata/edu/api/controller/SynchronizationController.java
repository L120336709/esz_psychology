package com.sundata.edu.api.controller;

import com.alibaba.fastjson.JSON;
import com.sundata.edu.api.bean.ClassRequest;
import com.sundata.edu.api.bean.GradeRequest;
import com.sundata.edu.domain.Classinfo;
import com.sundata.edu.domain.Gradeinfo;
import com.sundata.edu.domain.Orginfo;
import com.sundata.edu.enums.OperationEnum;
import com.sundata.edu.service.ClassinfoService;
import com.sundata.edu.service.GradeinfoService;
import com.sundata.edu.service.OrginfoService;
import com.sundata.edu.service.UserinfoService;
import com.sundata.edu.api.bean.OrgRequest;
import com.sundata.edu.api.bean.UserRequest;
import com.sundata.edu.framework.web.result.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 同步数据
 */
@Controller
@RequestMapping("api/syn")
public class SynchronizationController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private OrginfoService orginfoService;
    @Autowired
    private GradeinfoService gradeinfoService;
    @Autowired
    private ClassinfoService classinfoService;

    @PostMapping("/user")
    @ResponseBody
    private AjaxResult synUserInfo(@RequestBody UserRequest request) {
        logger.info(JSON.toJSONString(request));
        userinfoService.synUserInfo(request);
        return AjaxResult.success("用户同步成功");
    }

    @PostMapping("/org")
    @ResponseBody
    public AjaxResult synOrgInfo(@RequestBody OrgRequest request) {
        logger.info(JSON.toJSONString(request));
        Orginfo orginfo = orginfoService.getOrgInfoByOrgId(request.getOrgId());
        if (orginfo == null) {
            orginfo = new Orginfo();
            BeanUtils.copyProperties(request, orginfo);
            orginfoService.saveOrgInfo(orginfo, OperationEnum.INSERT);
        } else {
            orginfo.setOrgName(request.getOrgName());
            orginfo.setStatus(request.getStatus());
            orginfo.setParentOrgId(request.getParentOrgId());
            orginfoService.saveOrgInfo(orginfo, OperationEnum.UPDATE);
        }
        return AjaxResult.success("机构同步成功");
    }

    //同步年级
    @PostMapping("/gradeinfo")
    @ResponseBody
    public AjaxResult synGradeInfo(@RequestBody GradeRequest request) {
        logger.info(JSON.toJSONString(request));
        Gradeinfo gradeinfo = gradeinfoService.getGradeinfoByGradeId(request.getGradeId());
        if (gradeinfo == null) {
            gradeinfo = new Gradeinfo();
            BeanUtils.copyProperties(request, gradeinfo);
            //因为数据库中schoolId为vachar类型，但是request中的schoolId为int类型，上面一句代码赋值不上
            gradeinfo.setSchoolId(request.getSchoolId() + "");
            //gradeinfoService.saveGradeinfo(gradeinfo, OperationEnum.INSERT);
        } else {
            BeanUtils.copyProperties(request, gradeinfo);
            gradeinfo.setSchoolId(request.getSchoolId() + "");
            //gradeinfoService.saveGradeinfo(gradeinfo, OperationEnum.UPDATE);
        }
        return AjaxResult.success("年级同步成功");
    }

    //同步班级
    @PostMapping("/classinfo")
    @ResponseBody
    public AjaxResult synClassInfo(@RequestBody ClassRequest request) {
        logger.info(JSON.toJSONString(request));
        Classinfo classinfo = classinfoService.getClassinfoByClassId(request.getClassId());
        if (classinfo == null) {
            classinfo = new Classinfo();
            BeanUtils.copyProperties(request, classinfo);
            //因为数据库中schoolId为vachar类型，但是request中的schoolId为int类型，上面一句代码赋值不上
            classinfo.setSchoolId(request.getSchoolId() + "");
            String newGradeId = request.getClassLevel();
            newGradeId = newGradeId.replace("级","");
            classinfo.setGradeId(newGradeId);
            classinfo.setGradeName(request.getClassLevel());
            classinfoService.saveClassinfo(classinfo, OperationEnum.INSERT);
        } else {
            BeanUtils.copyProperties(request, classinfo);
            classinfo.setSchoolId(request.getSchoolId() + "");
            String newGradeId = request.getClassLevel();
            newGradeId = newGradeId.replace("级","");
            classinfo.setGradeId(newGradeId);
            classinfo.setGradeName(request.getClassLevel());
            classinfoService.saveClassinfo(classinfo, OperationEnum.UPDATE);
        }
        return AjaxResult.success("班级同步成功");
    }

}
