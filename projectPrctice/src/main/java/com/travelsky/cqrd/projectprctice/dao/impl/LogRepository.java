package com.travelsky.cqrd.projectprctice.dao.impl;

import com.travelsky.cqrd.projectprctice.dao.LogDao;
import com.travelsky.cqrd.projectprctice.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tjy
 * @ClassName LogRepository
 * @Description TODO
 * @Date 2020/6/14 15:27
 * @Version 1.0
 **/
@Repository
public class LogRepository implements LogDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Log> findAllLog() {
        return mongoTemplate.findAll(Log.class);
    }

    @Override
    public List<Log> findLogByUserName(String username) {
        Query query = Query.query(Criteria.where("userName").is(username));
        return mongoTemplate.find(query, Log.class);
    }

    @Override
    public void addLog(String option, String username) {
        Log log = new Log();
        log.setOption(option);
        log.setTime(LocalDateTime.now());
        log.setUserName(username);
        mongoTemplate.save(log);
    }
}
