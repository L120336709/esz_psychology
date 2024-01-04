package com.sundata.edu.controller.psychology;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/psychology")
public class PsyLoginController {

    @GetMapping("/login")
    public String login(String accessToken){

        return "/psychology/index";
    }

    @GetMapping("/pxsm")
    public String pxsm(){

        return "/psychology/pxsm";
    }

    @GetMapping("/kclb")
    public String kclb(){
        return "/psychology/kclb";
    }

    @GetMapping("/videoplay")
    public String videoplay(){
        return "/psychology/videoplay";
    }

    @GetMapping("/xxjd")
    public ModelAndView xxjd(String userId,ModelMap mmap){
        mmap.put("userId",userId);
        ModelAndView view = new ModelAndView( "/psychology/xxjd", mmap);
        return  view;
    }

    @GetMapping("/pxjc")
    public String pxjc(){
        return "/psychology/pxjc";
    }
}
