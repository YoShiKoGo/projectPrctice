package com.travelsky.cqrd.projectprctice.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/9 16:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    private String userName;
    private String password;
}
