package com.travelsky.cqrd.projectprctice.config;

import com.travelsky.cqrd.projectprctice.filter.AdminHandlerInterceptor;
import com.travelsky.cqrd.projectprctice.filter.LoginHandlerInterceptor;
import com.travelsky.cqrd.projectprctice.filter.UserHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author tjy
 * @ClassName SpringMvcConfigurer
 * @Description TODO
 * @Date 2020/3/24 10:38
 * @Version 1.0
 **/
//@EnableWebMvc
@Configuration
public class SpringMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/success").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
//        registry.addViewController("/main.html").setViewName("/index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                //指定要拦截的请求 /** 表示拦截所有请求
                .addPathPatterns("/**")
                //排除不需要拦截的请求路径
                .excludePathPatterns("/", "/index.html", "/login", "/loginError","/logOut")
                //springboot2+之后需要将静态资源文件的访问路径 也排除
                .excludePathPatterns("/css/*", "/img/*","/js/*");
        registry.addInterceptor(new UserHandlerInterceptor())
                .addPathPatterns("/userInfo/getAll", "/airline/findAll");
        registry.addInterceptor(new AdminHandlerInterceptor())
                .addPathPatterns("/userInfo/adminFindAll");
    }
}
