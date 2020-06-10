package com.travelsky.cqrd.projectprctice.web;

import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.form.AddForm;
import com.travelsky.cqrd.projectprctice.form.UpdateForm;
import com.travelsky.cqrd.projectprctice.services.UserInfoService;
import com.travelsky.cqrd.projectprctice.vo.UserInfoPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/8 16:21
 */
@RestController
@RequestMapping(value = "/userInfo")
@CrossOrigin
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/getAll")
    public List<UserInfo> findAll(){
        return userInfoService.findAll();
    }

    /**
     * 普通分页
     * @param page
     * @return UserInfoPageVo
     */
    @ResponseBody
    @RequestMapping("/getList")
    public UserInfoPageVo findUserInfos(Integer page){
        UserInfoPageVo userInfoPageVo = new UserInfoPageVo();
        userInfoPageVo.setCurrentPage(page);
        return userInfoService.findAllUserInfo(userInfoPageVo);
    }

    /**
     * code分页
     * @param page
     * @param airlineCode
     * @return
     */
    @RequestMapping("/getListByCode")
    public UserInfoPageVo findUserInfosByAirlineCode(Integer page, String airlineCode){
        UserInfoPageVo userInfoPageVo = new UserInfoPageVo();
        userInfoPageVo.setCurrentPage(page);
        return userInfoService.findAllUserInfoByAirLineCode(airlineCode,userInfoPageVo);
    }

    /**
     * 更新
     * @param updateForm
     * @return
     */
    @RequestMapping("/update")
    public boolean updateUserInfo(UpdateForm updateForm){
        //判断当前
        System.out.println(updateForm);
        //查询当前修改的用户
        UserInfo userInfo = userInfoService.findById(updateForm.getId());
        //修改用户
        userInfo.setUserName(updateForm.getUserName());
        userInfo.setAirlineCode(updateForm.getAirlineCode());

//        return false;
        return userInfoService.UpdateById(userInfo);
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public boolean deleteUserInfo(String id){
        return userInfoService.deleteById(id);
    }

    @RequestMapping("/getByUsername")
    public UserInfo getByUsername(AddForm addForm){
        System.out.println(addForm);
        //查询
        return null;
    }

}
