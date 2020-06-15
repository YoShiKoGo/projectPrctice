package com.travelsky.cqrd.projectprctice.filter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/15 15:20
 */
public class AdminHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Integer role = (Integer) session.getAttribute("role");
        System.out.println(role);
        System.out.println("公司管理员验证");
        if(role != 2){
            request.getRequestDispatcher("/roleError").forward(request, response);
            return false;
        }
        return true;
    }
}
