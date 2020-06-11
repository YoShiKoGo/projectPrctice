package com.travelsky.cqrd.projectprctice.dao;

import com.travelsky.cqrd.projectprctice.entity.Airline;

import java.util.List;

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

    /**
     * 查询所有平台
     * @return
     */
    public List<Airline> findAllAirline();
}
