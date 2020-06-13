package com.travelsky.cqrd.projectprctice.dao.impl;

import com.mongodb.client.result.UpdateResult;
import com.travelsky.cqrd.projectprctice.dao.AirlineDao;
import com.travelsky.cqrd.projectprctice.entity.Airline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/9 13:41
 */
@Repository
public class AirlineRepository implements AirlineDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 删除平台
     * @param code
     * @return
     */
    @Override
    public boolean deleteAirline(String code) {
        Query query = Query.query(Criteria.where("code").is(code));
        Update update = new Update().set("isEnable", false);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Airline.class);
        //是否删除
        if(updateResult.getModifiedCount() <= 0){
            return false;
        }
        return true;
    }

    /**
     * 查询所有平台
     * @return
     */
    @Override
    public List<Airline> findAllAirline() {

        return mongoTemplate.find(Query.query(Criteria.where("isEnable").is(true)), Airline.class);
    }
}
