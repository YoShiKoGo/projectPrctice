package com.travelsky.cqrd.projectprctice.filter;

import com.travelsky.cqrd.projectprctice.entity.RoleInfo;
import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.services.RoleInfoService;
import com.travelsky.cqrd.projectprctice.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/15 14:13
 */
public class UserHandlerInterceptor implements HandlerInterceptor {


    @Autowired
    private RoleInfoService roleInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        //获得权限等级
//        RoleInfo role = roleInfoService.findRoleByUsername(userInfo.getKey());
//        System.out.println(role.getLevel());
        HttpSession session = request.getSession();
        Integer role = (Integer) session.getAttribute("role");
        System.out.println(role);
//        roleInfoService.filterGetRole(loginToken);
//        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(loginToken);
//        System.out.println(userInfo);
        System.out.println("超级管理员判断");
        if(role != 3){
            request.getRequestDispatcher("/roleError").forward(request, response);
            return false;
        }

        return true;
    }
}
