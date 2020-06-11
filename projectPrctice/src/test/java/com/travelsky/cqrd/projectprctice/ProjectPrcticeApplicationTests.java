package com.travelsky.cqrd.projectprctice;

import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
class ProjectPrcticeApplicationTests {
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
//        System.out.println(mongoTemplate.findAll(UserInfo.class));
//        Query query = Query.query(Criteria.where("userName").is("admin"));
        Query query = Query.query(Criteria.where("_id").is("5edf0c64260e0000cd0024e2"));
        UserInfo userInfo = mongoTemplate.findOne(query, UserInfo.class);
//        System.out.println(userInfo);
//        System.out.println(mongoTemplate.findById("5edda21dda5a0000b7007083", UserInfo.class));
        Query query1 = Query.query(Criteria.where("_id").is(userInfo.getKey()));
        Update update =Update.update("userName","aaaaa");

        mongoTemplate.updateFirst(query1, update, userInfo.getClass());


    }

}
