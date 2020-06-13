package com.travelsky.cqrd.projectprctice.web;

import com.travelsky.cqrd.projectprctice.entity.Airline;
import com.travelsky.cqrd.projectprctice.services.AirlineService;
import com.travelsky.cqrd.projectprctice.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/9 13:47
 */
@RestController
@RequestMapping("/airline")
@CrossOrigin
public class AirlineController {
    @Autowired
    private AirlineService airlineService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/findAll")
    public List<Airline> findAllAirline(){
        return airlineService.findAllAirline();
    }

    /**
     * 删除平台
     * @param airline
     * @return
     */
    @RequestMapping("/delete")
    public boolean deleteAirline(Airline airline){
        System.out.println(airline);
        //删除平台下的用户
        boolean b = userInfoService.deleteByAirlineCode(airline.getCode());
        return airlineService.deleteAirline(airline.getCode());
    }
}
