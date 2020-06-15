package com.travelsky.cqrd.projectprctice.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.travelsky.cqrd.projectprctice.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * @author tjy
 * @ClassName LoginHandlerInterceptor
 * @Description TODO
 * @Date 2020/3/24 12:39
 * @Version 1.0
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("判断");
        UUID loginToken = (UUID) request.getSession().getAttribute("LoginToken");
//        System.out.println(loginToken);
        if(loginToken == null){
            System.out.println("未登录");
            request.getRequestDispatcher("/loginError").forward(request, response);
            return false;
        }
        return true;
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
