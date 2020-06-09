package com.travelsky.cqrd.projectprctice.vo;

import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jytian
 * @version 1.0
 * @description 用户信息page
 * @date 2020/6/9 9:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoPageVo {
   private List<UserInfo> userInfoList;
   private Integer pageNum;
   private Integer currentPage;
}
