package com.travelsky.cqrd.projectprctice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tjy
 * @ClassName Log
 * @Description TODO
 * @Date 2020/6/14 15:11
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "log")
public class Log implements Serializable {
    @Id
    private String id;
    private LocalDateTime time;
    private String userName;
    private String option;
}
