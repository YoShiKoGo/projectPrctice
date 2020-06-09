package com.travelsky.cqrd.projectprctice.services;

/**
 * @author jytian
 * @version 1.0
 * @description 平台service
 * @date 2020/6/9 13:48
 */
public interface AirlineService {
    /**
     * 删除平台
     * @param code
     * @return
     */
    public boolean deleteAirline(String code);
}
