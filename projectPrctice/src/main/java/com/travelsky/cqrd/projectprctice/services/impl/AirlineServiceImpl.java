package com.travelsky.cqrd.projectprctice.services.impl;


import com.travelsky.cqrd.projectprctice.dao.AirlineDao;
import com.travelsky.cqrd.projectprctice.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/9 13:49
 */
@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineDao airlineDao;

    /**
     * 删除平台
     * @param code
     * @return
     */
    @Override
    public boolean deleteAirline(String code) {
        return airlineDao.deleteAirline(code);
    }
}
