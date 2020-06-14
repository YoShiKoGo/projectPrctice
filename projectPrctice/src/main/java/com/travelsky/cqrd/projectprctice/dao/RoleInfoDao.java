package com.travelsky.cqrd.projectprctice.dao;

import com.travelsky.cqrd.projectprctice.entity.RoleInfo;

public interface RoleInfoDao {
    RoleInfo findRoleByUserId(String userId);
    /**
     * 新增roleInfo
     */
    RoleInfo addRoleInfo(RoleInfo roleInfo);
    /**
     * 查询airline是否已经有管理员
     */
    RoleInfo selectAdminRole(String airline);
}
