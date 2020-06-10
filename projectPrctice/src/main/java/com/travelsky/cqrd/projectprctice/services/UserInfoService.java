package com.travelsky.cqrd.projectprctice.services;

import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.vo.UserInfoPageVo;

import java.util.List;

public interface UserInfoService {
    /**
     * 普通分页查询
     * @param page
     * @return UserInfoPageVo
     */
    public UserInfoPageVo findAllUserInfo(UserInfoPageVo page);

    /**
     * 根据航司
     * @param airLineCode,page
     * @return UserInfoPageVo
     */
    public UserInfoPageVo findAllUserInfoByAirLineCode(String airLineCode, UserInfoPageVo page);

    /**
     * 修改
     * @param userInfo
     * @return
     */
    public boolean UpdateById(UserInfo userInfo);

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteById(String id);

    /**
     *删除平台下用户
     * @param airlineCode
     * @return
     */
    public boolean deleteByAirlineCode(String airlineCode);

    /**
     * 根据名字查询
     * @param username
     * @return
     */
    public UserInfo selectByUsername(String username);

    /**
     * 查询所有
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    UserInfo findById(String id);
}
