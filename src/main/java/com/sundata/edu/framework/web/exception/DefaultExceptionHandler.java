package com.sundata.edu.framework.web.exception;

import com.sundata.edu.framework.exception.ServiceException;
import com.sundata.edu.framework.util.ServletUtils;
import com.sundata.edu.framework.web.result.AjaxResult;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常处理器
 */
@ControllerAdvice
public class DefaultExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(value = {ServiceException.class, AuthenticationException.class})
    public ModelAndView handleServiceException(ServiceException ex, HttpServletRequest request, HttpServletResponse response) {
        logger.error(ex.getMessage(), ex);
        if (ServletUtils.isAjaxRequest(request)) {
            ServletUtils.responseResult(response, AjaxResult.error(ex.getMessage()));
            return null;
        }
        ModelAndView modelMap = new ModelAndView();
        modelMap.setViewName("errors");
        modelMap.addObject("msg", ex.getMessage());
        return modelMap;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        logger.error(ex.getMessage(), ex);
        if (ServletUtils.isAjaxRequest(request)) {
            ServletUtils.responseResult(response, AjaxResult.error("系统异常，请稍后重试"));
            return null;
        }
        ModelAndView modelMap = new ModelAndView();
        modelMap.setViewName("errors");
        modelMap.addObject("msg", "系统异常，请稍后重试");
        return modelMap;
    }

//    /**
//     * 权限校验失败
//     */
//    @ExceptionHandler(AuthorizationException.class)
////    @ResponseBodyody
//    public String handleAuthorizationException(AuthorizationException e, HttpServletRequest request, HttpServletResponse response) {
//        logger.error(e.getMessage(), e);
//        if (ServletUtils.isAjaxRequest(request)) {
//            ServletUtils.responseResult(response, AjaxResult.error(e.getMessage()));
//            return null;
//        }
//        //modelMap.addAttribute("msg", e.getMessage());
//        return "unauth";
//    }

//    /**
//     * 请求方式不支持
//     */
//    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
//    public AjaxResult handleException(HttpRequestMethodNotSupportedException e) {
//        logger.error(e.getMessage(), e);
//        return AjaxResult.error("不支持' " + e.getMethod() + "'请求");
//    }
//
//    /**
//     * 拦截未知的运行时异常
//     */
//    @ExceptionHandler(RuntimeException.class)
//    public AjaxResult notFount(RuntimeException e) {
//        logger.error("运行时异常:", e);
//        return AjaxResult.error("运行时异常:" + e.getMessage());
//    }
//
//    /**
//     * 系统异常
//     */
//    @ExceptionHandler(Exception.class)
//    public AjaxResult handleException(Exception e) {
//        logger.error(e.getMessage(), e);
//        return AjaxResult.error("服务器错误，请联系管理员");
//    }
}
