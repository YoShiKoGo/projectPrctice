package com.travelsky.cqrd.projectprctice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author jytian
 * @version 1.0
 * @description 用户信息
 * @date 2020/6/8 15:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userInfo")
public class UserInfo {
    @Id
    private String id;
    private String userName;
    private String userPsw;
    private LocalDateTime loginTime;
    private String ip;
    private String airlineCode;
    private boolean isEnable;

}
