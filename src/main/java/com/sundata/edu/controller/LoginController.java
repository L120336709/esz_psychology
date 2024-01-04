package com.sundata.edu.controller;
import com.sundata.edu.service.UserinfoService;
import com.sundata.edu.framework.core.Resource;
import com.sundata.edu.framework.exception.ServiceException;
import com.sundata.edu.framework.util.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Resource resource;

    @Autowired
    private UserinfoService userinfoService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

//    @RequestMapping(value = "/sso/login/{userId}/{sign}", method = RequestMethod.GET)
//    public void login(@PathVariable("userId") String userId, @PathVariable("sign") String sign, HttpServletResponse response) {
//        logger.info(userId + " " + sign);
//        // 在session中保存用户信息
//        try {
//            if (!Md5Utils.verify(userId + resource.USERKEY, sign) && true) {
//                throw new ServiceException("非法请求，数据被篡改");
//            }
//            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userId, "songdaRoot@!1921", false);
//            Subject subject = SecurityUtils.getSubject();
//
//            subject.login(usernamePasswordToken);
//            response.sendRedirect("/");
//        } catch (Exception ex) {
//            throw new ServiceException(ex.getMessage());
//        }
//    }

    @GetMapping("/sso/login")
    public void ssoLogin(String accessToken, Model model, HttpServletResponse response) {
        System.err.println("============accessToken=========="+accessToken);
        if (StringUtils.isBlank(accessToken)) {
            throw new ServiceException("token不能为空");
        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken("accessToken", accessToken);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            //response.sendRedirect("/");
            response.sendRedirect("/psychology/login?accessToken="+accessToken);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @GetMapping("/sso/index")
    public void ssoindex(String accessToken, Model model, HttpServletResponse response) {
        if (StringUtils.isBlank(accessToken)) {
            throw new ServiceException("token不能为空");
        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken("accessToken", accessToken);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            response.sendRedirect("/");

        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @RequestMapping("/loginOut")
    public String loginOut(){

        return "error/loginOut";
    }

}
