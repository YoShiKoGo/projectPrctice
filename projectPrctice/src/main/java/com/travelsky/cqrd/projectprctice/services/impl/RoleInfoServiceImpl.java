package com.travelsky.cqrd.projectprctice.services.impl;

import com.travelsky.cqrd.projectprctice.dao.RoleInfoDao;
import com.travelsky.cqrd.projectprctice.entity.RoleInfo;
import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.services.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author tjy
 * @ClassName RoleInfoServiceImpl
 * @Description TODO
 * @Date 2020/6/14 16:13
 * @Version 1.0
 **/
@Service
public class RoleInfoServiceImpl implements RoleInfoService {
    @Autowired
    private RoleInfoDao roleInfoDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RoleInfo findRoleByUsername(String userId) {
        return roleInfoDao.findRoleByUserId(userId);
    }

    @Override
    public RoleInfo addRoleInfo(RoleInfo roleInfo) {
        return roleInfoDao.addRoleInfo(roleInfo);
    }

    @Override
    public boolean selectAdminRole(String airline) {
        RoleInfo roleInfo = roleInfoDao.selectAdminRole(airline);
        if(roleInfo == null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public RoleInfo filterGetRole(UUID token) {
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(token);
        System.out.println(userInfo);
        return null;
    }
}
