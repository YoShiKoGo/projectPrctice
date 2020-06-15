package com.travelsky.cqrd.projectprctice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 记录用户登录次数
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/15 8:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginNumVo  implements Serializable {
    //当前月
    private Integer month;
    //当前日
    private Integer day;
    //当前月登录次数
    private Integer monthNum;
    //当前日登录次数
    private Integer dayNum;

}
