package com.sundata.edu.framework.shrio.service;

import com.sundata.common.base.Response;
import com.sundata.common.base.ResponseCode;
import com.sundata.edu.dao.UserinfoDao;
import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.enums.StatusEnum;
import com.sundata.edu.framework.exception.ServiceException;
import com.sundata.edu.service.UserinfoService;
import com.sundata.edu.vo.UserinfoVo;
import com.sundata.sdcloud.user.service.IUserService;
import com.sundata.sdcloud.user.vo.UserDetailVO;
import com.sundata.sdcloud.user.vo.UserInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * @Description TODO
 * @Author 侯鹏
 * @Date 2018-12-21 22:41
 * @Version 1.0
 */
@Component
public class LoginService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private IUserService rpcUserService;


    /**
     * 登录
     */
    public UserinfoVo login(String accessToken) {
        Response<UserInfo> userInfoByTokenResponse = rpcUserService.getUserInfoByToken(accessToken);

        if (ResponseCode.FAIL.equals(userInfoByTokenResponse.getCode())) {
            logger.error("根据平台访问令牌查询用户信息失败：accessToken={}", accessToken);
            throw new AuthenticationException("登录失败：" + userInfoByTokenResponse.getMsg());
        }
        UserInfo userInfo = userInfoByTokenResponse.getData();
        if(userInfo == null) {
            logger.error("根据平台访问令牌查询用户信息为空，accessToken={}", accessToken);
            throw new AuthenticationException("登录失败，" + userInfoByTokenResponse.getMsg());
        }


//        Userinfo userinfo = userinfoDao.selectByPrimaryKey(userInfo.getUserId());

        com.sundata.sdcloud.user.vo.UserInfo vouserinfo =userInfoByTokenResponse.getData();
        Userinfo userinfo = new Userinfo();
        // Userinfo userinfo = userinfoDao.selectByPrimaryKey(userInfo.getUserId());
//        Response<UserSimpleVO> uuuu=rpcUserService.getUserSimpleByUserId(vouserinfo.getUserId());
//
//        System.err.println(uuuu.toString());


        userinfo.setUserId(vouserinfo.getUserId());
        userinfo.setUserNo(vouserinfo.getUserNo());
        userinfo.setRealName(vouserinfo.getPersonName());
        userinfo.setOrgId(vouserinfo.getOrgId());
        userinfo.setIdentity(Integer.parseInt(vouserinfo.getIdentity()));
        //userinfo.setIdCard(vouserinfo.get);
        userinfo.setStatus(Integer.parseInt(vouserinfo.getState()));


        //


        if(userinfo == null) {
            logger.error("登录用户未同步");
            throw new AuthenticationException("登录失败，登录用户未同步");
        }

        UserinfoVo userinfoVo = new UserinfoVo();
        BeanUtils.copyProperties(userinfo, userinfoVo);

        userinfoVo.setToken(accessToken);

        return userinfoVo;
    }

//    public Userinfo login(String userId) {
//        // 用户名或密码为空
//        if (StringUtils.isEmpty(userId)) {
//            throw new IncorrectCredentialsException("用户编码不能为空");
//        }
//        Example example = new Example(Userinfo.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("userId", userId);
//        List<Userinfo> userinfos = userinfoService.selectByExample(example);
//        if (userinfos.size() <= 0) {
//            throw new UnknownAccountException("此用户信息不存在，请核实用户");
//        }
//        // 查询用户信息
//        Userinfo userinfo = userinfos.get(0);
//        if (userinfo.getStatus() != StatusEnum.AVAILABLE.code()) {
//            throw new ServiceException("此用户已被禁用");
//        }
//        return userinfo;
//    }

}
