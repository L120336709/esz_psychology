package com.sundata.edu.controller;


import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.util.PcUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/crgk")
@Controller
public class SearchController extends BaseController {
    @Autowired
    private  HttpServletRequest request;
    @GetMapping("/index")
    public String index(){
        boolean isMoblie= PcUtils.JudgeIsMoblie(request);
        if(isMoblie){
            return "mobile_query";
        }else {

            return "search";
        }
    }

}
