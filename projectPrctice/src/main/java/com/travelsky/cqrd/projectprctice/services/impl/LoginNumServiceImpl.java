package com.travelsky.cqrd.projectprctice.services.impl;

import com.travelsky.cqrd.projectprctice.services.LoginNumService;
import com.travelsky.cqrd.projectprctice.vo.LoginNumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/15 9:16
 */
@Service
public class LoginNumServiceImpl implements LoginNumService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void updateLoginNum(String username) {
        //获取当前用户的登录次数
        LoginNumVo loginNum = (LoginNumVo) redisTemplate.opsForValue().get(username);
        LocalDateTime now = LocalDateTime.now();
        //如果没有则新建一个记录
        if(loginNum == null){
            LoginNumVo loginNumVo = new LoginNumVo();
            //获取当前时间
            loginNumVo.setDay(now.getDayOfMonth());
            loginNumVo.setDayNum(1);
            loginNumVo.setMonth(now.getMonthValue());
            loginNumVo.setMonthNum(1);
            //储存在redis中
            redisTemplate.opsForValue().set(username, loginNumVo);
        }
        //有记录则判断是否为当天；如果是则加一，如果不是则设置为1
        if(loginNum != null){
            //比较天数
            if(loginNum.getDay() == now.getDayOfMonth()){
                Integer dayNum = loginNum.getDayNum();
                loginNum.setDayNum(dayNum + 1);
            }
            if(loginNum.getDay() != now.getDayOfMonth()){
                loginNum.setDay(now.getDayOfMonth());
                loginNum.setDayNum(1);
            }
            //判断是否为当月
            if(loginNum.getMonth() == now.getMonthValue()){
                Integer monthNum = loginNum.getMonthNum();
                loginNum.setMonthNum(monthNum + 1);
            }
            if(loginNum.getMonth() != now.getMonthValue()){
                loginNum.setMonth(now.getMonthValue());
                loginNum.setMonthNum(1);
            }
            //存在redis中
            redisTemplate.opsForValue().set(username, loginNum);
        }

    }

    @Override
    public LoginNumVo getUserLoginNum(String username) {
        //根据username查询 登录次数
        LoginNumVo loginNum = (LoginNumVo) redisTemplate.opsForValue().get(username);
        return loginNum;
    }
}
