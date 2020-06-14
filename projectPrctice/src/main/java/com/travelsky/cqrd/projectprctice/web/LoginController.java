package com.travelsky.cqrd.projectprctice.web;

import com.travelsky.cqrd.projectprctice.entity.RoleInfo;
import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.form.LoginForm;
import com.travelsky.cqrd.projectprctice.services.RoleInfoService;
import com.travelsky.cqrd.projectprctice.services.UserInfoService;
import com.travelsky.cqrd.projectprctice.utils.AESUtil;
import com.travelsky.cqrd.projectprctice.utils.IpUtil;
import com.travelsky.cqrd.projectprctice.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private RoleInfoService roleInfoService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    public ResponseResult testSend(LoginForm user, HttpServletRequest request){
        //封装信息返回
        ResponseResult responseResult = new ResponseResult();
        //验证密码是否正确
        UserInfo userInfo = userInfoService.selectByUsername(user.getUserName());
        //加密密码
        AESUtil aesUtil = new AESUtil();
        String aesEncode = aesUtil.AESEncode(user.getPassword());
        if(aesEncode.equals(userInfo.getUserPsw()) && userInfo.isEnable() == true){
            responseResult.setStats(true);
            //用uuid生成token
            UUID uuid = UUID.randomUUID();
            HttpSession session = request.getSession();
            session.setAttribute("LoginToken", uuid);
            //将token存放在redis中
            redisTemplate.opsForValue().set(uuid,userInfo);
            //获取用户Ip
            String ipAddr = IpUtil.getIpAddr(request);
            //更新数据库用户ip，更新登录时间
            userInfoService.updateUserInfoIp(userInfo.getKey(), ipAddr);


        }else {
            responseResult.setStats(false);
        }
        //查询用户角色
        RoleInfo roleByUsername = roleInfoService.findRoleByUsername(userInfo.getKey());
        responseResult.setData(roleByUsername.getLevel());

        return responseResult;
    }

}
