package com.travelsky.cqrd.projectprctice.dao;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/9 13:40
 */
public interface AirlineDao {
    /**
     * 删除平台
     * @param code
     * @return
     */
    public boolean deleteAirline(String code);
}
