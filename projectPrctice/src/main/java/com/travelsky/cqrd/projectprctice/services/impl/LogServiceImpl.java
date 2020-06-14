package com.travelsky.cqrd.projectprctice.services.impl;

import com.travelsky.cqrd.projectprctice.dao.LogDao;
import com.travelsky.cqrd.projectprctice.entity.Log;
import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @author tjy
 * @ClassName LogServiceImpl
 * @Description TODO
 * @Date 2020/6/14 15:35
 * @Version 1.0
 **/
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Log> findAllLog() {
        return logDao.findAllLog();
    }

    @Override
    public List<Log> findLogByUsername(String username) {
        return logDao.findLogByUserName(username);
    }

    @Override
    public void addLog(String option, HttpServletRequest request) {
        //获取用户名
        UUID loginToken = (UUID) request.getSession().getAttribute("LoginToken");
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(loginToken);
        //持久化储存
        logDao.addLog(option, userInfo.getUserName());
    }
}
