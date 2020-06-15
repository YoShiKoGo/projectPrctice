package com.travelsky.cqrd.projectprctice.services;

import com.travelsky.cqrd.projectprctice.vo.LoginNumVo;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/15 9:12
 */
public interface LoginNumService {
    /**
     * 登录时更新用户登录次数
     * @param username
     */
    void updateLoginNum(String username);

    /**
     * 获取当前用户的登录次数
     * @param username
     * @return
     */
    LoginNumVo getUserLoginNum(String username);
}
