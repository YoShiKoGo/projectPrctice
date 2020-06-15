package com.travelsky.cqrd.projectprctice.form;

import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.vo.LoginNumVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/15 10:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoForm {
    private UserInfo userInfo;
    private LoginNumVo loginNumVo;
}
