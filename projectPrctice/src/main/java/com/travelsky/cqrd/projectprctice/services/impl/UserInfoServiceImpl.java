package com.travelsky.cqrd.projectprctice.services.impl;


import com.travelsky.cqrd.projectprctice.dao.UserInfoDao;
import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.services.UserInfoService;
import com.travelsky.cqrd.projectprctice.vo.UserInfoPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jytian
 * @version 1.0
 * @description 用户信息service
 * @date 2020/6/8 16:29
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfoPageVo findAllUserInfo(UserInfoPageVo page) {
        return userInfoDao.getUserInfoList(page);
    }

    @Override
    public UserInfoPageVo findAllUserInfoByAirLineCode(String airLineCode, UserInfoPageVo page) {
        return userInfoDao.findUserInfoByAirline(airLineCode, page);
    }

    @Override
    public boolean UpdateById(UserInfo userInfo) {
        return userInfoDao.updateUserInfo(userInfo);
    }

    @Override
    public boolean deleteById(String id) {
        return userInfoDao.deleteUserInfo(id);
    }

    @Override
    public boolean deleteByAirlineCode(String airlineCode) {
        return userInfoDao.deleteUserInfoByAirlineCode(airlineCode);
    }

    @Override
    public UserInfo selectByUsername(String username) {
        return userInfoDao.selectByUsername(username);
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

    @Override
    public UserInfo findById(String id) {
        return userInfoDao.findUserInfoById(id);
    }

    @Override
    public List<UserInfo> findLikeUserName(String userName) {
        return userInfoDao.findLikeUserName(userName);
    }

    @Override
    public boolean addUserInfo(UserInfo userInfo) {
        return userInfoDao.addUserInfo(userInfo);
    }
}
