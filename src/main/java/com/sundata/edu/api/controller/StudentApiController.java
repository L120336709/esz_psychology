package com.sundata.edu.api.controller;

import com.sundata.edu.api.bean.StudentRequest;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wanghj
 * @date 2020/10/20 14:25
 */
@Controller
@RequestMapping("api/student")
public class StudentApiController {

    @Autowired
    private UserinfoService userinfoService;

    @PostMapping("/scan")
    @ResponseBody
    public AjaxResult scanStudentInfo(StudentRequest studentRequest) {
        return userinfoService.scanStudentInfo(studentRequest);
    }

}
