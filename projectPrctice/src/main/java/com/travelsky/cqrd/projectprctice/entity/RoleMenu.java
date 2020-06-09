package com.travelsky.cqrd.projectprctice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jytian
 * @version 1.0
 * @description 角色菜单表
 * @date 2020/6/8 16:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roleMenu")
public class RoleMenu {
    private ObjectId id;
    private String roleId;
    private String menuId;
}
