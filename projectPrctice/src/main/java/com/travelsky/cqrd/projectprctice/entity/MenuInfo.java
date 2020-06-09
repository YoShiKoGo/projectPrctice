package com.travelsky.cqrd.projectprctice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jytian
 * @version 1.0
 * @description 菜单项
 * @date 2020/6/8 15:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "menuInfo")
public class MenuInfo {
    private ObjectId id;
    private String menuName;
    private String url;
}
