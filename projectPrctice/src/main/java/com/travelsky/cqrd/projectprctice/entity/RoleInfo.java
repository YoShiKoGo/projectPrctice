package com.travelsky.cqrd.projectprctice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

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
public class RoleInfo {
    private ObjectId id;
    private String roleName;
    private Integer level;
}
