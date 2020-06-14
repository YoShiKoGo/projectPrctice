package com.travelsky.cqrd.projectprctice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author jytian
 * @version 1.0
 * @description 角色权限信息
 * @date 2020/6/8 15:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roleInfo")
public class RoleInfo implements Serializable {
    @Id
    private String id;
    private String roleName;
    private Integer level;
    private String userId;
    private String airline;
}
