package com.travelsky.cqrd.projectprctice;

import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
class ProjectPrcticeApplicationTests {
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        System.out.println(mongoTemplate.findAll(UserInfo.class));

    }

}
