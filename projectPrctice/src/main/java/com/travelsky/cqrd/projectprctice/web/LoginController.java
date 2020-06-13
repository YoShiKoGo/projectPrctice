package com.travelsky.cqrd.projectprctice.web;

import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.form.LoginForm;
import com.travelsky.cqrd.projectprctice.services.UserInfoService;
import com.travelsky.cqrd.projectprctice.utils.IpUtil;
import com.travelsky.cqrd.projectprctice.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/9 15:03
 */
@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/login")
    public ResponseResult testSend(LoginForm user, HttpServletRequest request){
        //封装信息返回
        ResponseResult responseResult = new ResponseResult();
        //验证密码是否正确
        UserInfo userInfo = userInfoService.selectByUsername(user.getUserName());
        if(user.getPassword().equals(userInfo.getUserPsw())){
            responseResult.setStats(true);
            //用uuid生成token
            UUID uuid = UUID.randomUUID();
            request.setAttribute("LoginToken", uuid);
            //将token存放在redis中
            //获取用户Ip
            String ipAddr = IpUtil.getIpAddr(request);
            System.out.println(ipAddr);
        }else {
            responseResult.setStats(false);
        }

        responseResult.setData(user);

        return responseResult;
    }

}
