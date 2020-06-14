package com.travelsky.cqrd.projectprctice.dao;


import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.vo.UserInfoPageVo;

import java.time.LocalDateTime;
import java.util.List;

public interface UserInfoDao {
    /**
     * 查询所有用户信息
     */
    public UserInfoPageVo getUserInfoList(UserInfoPageVo page);
    /**
     * 根据平台进行查询用户信息
     */
    public UserInfoPageVo findUserInfoByAirline(String airline, UserInfoPageVo page);
    /**
     * 根据_id查询
     */
    public UserInfo findUserInfoById(String id);
    /**
     * 删除用户 - enable = false
     */
    public boolean deleteUserInfo(String id);
    /**
     * 修改用户
     */
    public boolean updateUserInfo(UserInfo userInfo);
    /**
     * 平台删除(平台下用户删除)
     */
    public boolean deleteUserInfoByAirlineCode(String airlineCode);
    /**
     * 根据用户名查询
     */
    public UserInfo selectByUsername(String username);
    /**
     * 查询所有
     */
    public List<UserInfo> findAll();
    /**
     * 模糊查询
     */
    public List<UserInfo> findLikeUserName(String userName);
    /**
     * 新增用户
     */
    public UserInfo addUserInfo(UserInfo userInfo);
    /**
     * 更新用户ip
     */
    public boolean updateUserInfoIp(String id, String ip);
    /**
     * admin查询
     */
    public List<UserInfo> findByAdmin(String airline, String username);
    /**
     * admin模糊查询
     */
    public List<UserInfo> findByAdminLike(String username, String airline);
}
