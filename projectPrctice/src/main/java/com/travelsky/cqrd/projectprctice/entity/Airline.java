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
 * @description
 * @date 2020/6/9 9:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "airline")
public class Airline implements Serializable {
    @Id
    private String id;
    private String code;
    private String name;
    private boolean isEnable;
}
