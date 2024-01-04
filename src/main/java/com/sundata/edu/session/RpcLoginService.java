package com.sundata.edu.session;

import com.sundata.common.base.Response;
import com.sundata.common.base.ResponseCode;
import com.sundata.edu.framework.exception.ServiceException;
import com.sundata.sdcloud.user.service.IUserService;
import com.sundata.sdcloud.user.vo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: rpc登录
 * @Author: jiang chao
 * @Create: 2021-11-12 15:02
 **/
@Component
public class RpcLoginService {

    public static final Logger logger = LoggerFactory.getLogger(RpcLoginService.class);

    @Autowired
    private IUserService rpcUserService;

    /**
     * 登录
     */
    public void login(String accessToken) {
        try {
            Response<UserInfo> userInfoByTokenResponse = rpcUserService.getUserInfoByToken(accessToken);
            if (ResponseCode.FAIL.equals(userInfoByTokenResponse.getCode())) {
                logger.error("根据平台访问令牌查询用户信息失败：accessToken={}", accessToken);
                throw new AuthenticationException("登录失败：" + userInfoByTokenResponse.getMsg());
            }
            UserInfo userInfo = userInfoByTokenResponse.getData();
            //System.err.println("userInfo=="+userInfo.toString());
            if (userInfo == null) {
                logger.error("根据平台访问令牌查询用户信息为空，accessToken={}", accessToken);
                throw new AuthenticationException("登录失败，" + userInfoByTokenResponse.getMsg());
            }

           // UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userInfo.getUserId(), "songdaRoot@!1921", false);
           // System.err.println("usernamePasswordToken="+usernamePasswordToken.toString());
            UsernamePasswordToken token = new UsernamePasswordToken("accessToken", accessToken);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

//            Subject subject = SecurityUtils.getSubject();
//            subject.login(usernamePasswordToken);
        } catch (Exception ex) {
            logger.info("登录发生异常，异常信息:{}", ex);
            throw new ServiceException(ex.getMessage());
        }
    }
}
