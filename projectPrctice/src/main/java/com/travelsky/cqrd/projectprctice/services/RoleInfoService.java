package com.travelsky.cqrd.projectprctice.services;

import com.travelsky.cqrd.projectprctice.entity.RoleInfo;

public interface RoleInfoService {
    RoleInfo findRoleByUsername(String userId);
    RoleInfo addRoleInfo(RoleInfo roleInfo);
    boolean selectAdminRole(String airline);
}
