package com.sundata.edu.controller.h5;

import com.sundata.common.base.Response;
import com.sundata.edu.domain.Schedules;
import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.service.SchedulesService;
import com.sundata.edu.session.RpcLoginService;
import com.sundata.edu.vo.SchedulesVo;
import com.sundata.sdcloud.sso.service.ITokenService;
import com.sundata.sdcloud.user.service.IUserService;
import com.sundata.sdcloud.user.vo.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Description: App入口
 * @Author: jiang chao
 * @Create: 2021-11-12 13:04
 **/
@Controller
public class H5IndexController extends BaseController{

    @Autowired
    private RpcLoginService rpcLoginService;

    @Autowired
    private ITokenService iTokenService;

    @Autowired
    private SchedulesService schedulesService;

    @Autowired
    private IUserService rpcUserService;

    private String prefix = "h5";


    @GetMapping(value = "/h5/index")
    public ModelAndView index(String accessToken, ModelMap map) {
        System.err.println("accessToken00="+accessToken);
        accessToken=iTokenService.getWebTokenByAppToken(accessToken).getData();
        System.err.println("accessToken01="+accessToken);
        if(StringUtils.isBlank(accessToken)) {
            return new ModelAndView(prefix + "/error");
        }

        Response<UserInfo> userInfoByTokenResponse = rpcUserService.getUserInfoByToken(accessToken);
        UserInfo userInfo = userInfoByTokenResponse.getData();
        userInfo.getUserId();
        //System.err.println(userInfo.getUserId());
        rpcLoginService.login(accessToken);

//        Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
//        String userid = userinfo.getUserId();
//        SchedulesVo schedulesVo=new SchedulesVo();
//        schedulesVo.setUserid(userid);
//        List<Schedules> schedules=schedulesService.getSchedulesstablelist(schedulesVo);


        ModelAndView view = new ModelAndView(prefix+"/index", map);
//        if(schedules.size()==0){
//            view = new ModelAndView(prefix+"/error", map);
//        }
        return view;
    }

    @GetMapping(value = "/psy/videoplay")
    public ModelAndView dzgzzhindex() {
        ModelAndView view = new ModelAndView(  "/psy/videoplay");
        return view;
    }

    @GetMapping(value = "/psy/videoplayold")
    public ModelAndView videoplayold() {
        ModelAndView view = new ModelAndView(  "/psychology/videoplayold");
        return view;
    }

    @GetMapping(value = "/psy/jdtj")
    public ModelAndView jdtj() {
        ModelAndView view = new ModelAndView(  "/psychology/jdtj");
        return view;
    }
}
