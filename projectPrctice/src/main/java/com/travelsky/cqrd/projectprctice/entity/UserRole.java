package com.travelsky.cqrd.projectprctice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author jytian
 * @version 1.0
 * @description 用户角色
 * @date 2020/6/8 16:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userRole")
public class UserRole implements Serializable {
    private ObjectId id;
    private String userId;
    private String roleId;
    private String airline;
}
