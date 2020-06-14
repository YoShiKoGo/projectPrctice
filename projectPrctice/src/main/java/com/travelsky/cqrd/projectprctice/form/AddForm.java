package com.travelsky.cqrd.projectprctice.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/10 11:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddForm {
    private String pass;
    private String userName;
    private String airlineCode;
    private Integer role;
}
