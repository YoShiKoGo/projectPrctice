package com.travelsky.cqrd.projectprctice.dao.impl;

import com.travelsky.cqrd.projectprctice.dao.RoleInfoDao;
import com.travelsky.cqrd.projectprctice.entity.RoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author tjy
 * @ClassName RoleInfoRepository
 * @Description TODO
 * @Date 2020/6/14 15:58
 * @Version 1.0
 **/
@Repository
public class RoleInfoRepository implements RoleInfoDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public RoleInfo findRoleByUserId(String userId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, RoleInfo.class);
    }

    @Override
    public RoleInfo addRoleInfo(RoleInfo roleInfo) {
        return mongoTemplate.save(roleInfo);
    }

    @Override
    public RoleInfo selectAdminRole(String airline) {
        Criteria criteria = new Criteria().andOperator(Criteria.where("airline").is(airline),
                                                        Criteria.where("level").is(2));
        Query query = Query.query(criteria);
        return mongoTemplate.findOne(query, RoleInfo.class);
    }
}
