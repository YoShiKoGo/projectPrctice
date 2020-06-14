package com.travelsky.cqrd.projectprctice.web;

import com.travelsky.cqrd.projectprctice.entity.Log;
import com.travelsky.cqrd.projectprctice.entity.RoleInfo;
import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.services.LogService;
import com.travelsky.cqrd.projectprctice.services.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @author tjy
 * @ClassName LogController
 * @Description TODO
 * @Date 2020/6/14 15:14
 * @Version 1.0
 **/
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LogService logService;

    @Autowired
    private RoleInfoService roleInfoService;

    @RequestMapping("/history")
    public List<Log> findLogHistory(HttpServletRequest request){
        //从session中获得Token
        UUID loginToken = (UUID) request.getSession().getAttribute("LoginToken");
        System.out.println(loginToken);
        //用Token在redis中获得用户信息userInfo
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(loginToken);
        System.out.println(userInfo);
        //获取用户的权限级别
        RoleInfo roleInfo = roleInfoService.findRoleByUsername(userInfo.getKey());
        System.out.println(roleInfo);
        //再通过userName从数据库中获得对应的操作历史(超级管理员能够看到所有的历史)
        if(roleInfo.getLevel() == 3){
            //查询所有
            List<Log> allLog = logService.findAllLog();
            return allLog;
        }
        if(roleInfo.getLevel() == 2){
            List<Log> logByUsername = logService.findLogByUsername(userInfo.getUserName());
            System.out.println(logByUsername);
            return logByUsername;
        }else {
            return null;
        }
        //返回list

    }

}
