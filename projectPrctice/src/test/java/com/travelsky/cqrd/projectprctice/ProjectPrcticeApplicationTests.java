package com.travelsky.cqrd.projectprctice;

import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootTest
class ProjectPrcticeApplicationTests {
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
//        System.out.println(mongoTemplate.findAll(UserInfo.class));
        Query query = Query.query(Criteria.where("userName").is("sfsfs"));
        UserInfo userInfo = mongoTemplate.findOne(query, UserInfo.class);
        System.out.println(userInfo);

    }

}
