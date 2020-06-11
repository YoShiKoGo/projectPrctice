package com.travelsky.cqrd.projectprctice.services;

import com.travelsky.cqrd.projectprctice.entity.Airline;

import java.util.List;

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

    /**
     * 查询所有平台
     * @return
     */
    public List<Airline> findAllAirline();
}
