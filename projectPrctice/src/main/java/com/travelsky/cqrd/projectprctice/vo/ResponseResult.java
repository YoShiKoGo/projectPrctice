package com.travelsky.cqrd.projectprctice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tjy
 * @ClassName ResponseResult
 * @Description TODO
 * @Date 2020/4/23 17:02
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    //是否成功
    private boolean stats;
}
