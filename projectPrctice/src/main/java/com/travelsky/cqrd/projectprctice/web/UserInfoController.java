package com.travelsky.cqrd.projectprctice.web;

import com.travelsky.cqrd.projectprctice.entity.RoleInfo;
import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.form.AddForm;
import com.travelsky.cqrd.projectprctice.form.UpdateForm;
import com.travelsky.cqrd.projectprctice.services.LogService;
import com.travelsky.cqrd.projectprctice.services.RoleInfoService;
import com.travelsky.cqrd.projectprctice.services.UserInfoService;
import com.travelsky.cqrd.projectprctice.utils.AESUtil;
import com.travelsky.cqrd.projectprctice.vo.UserInfoPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private RoleInfoService roleInfoService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LogService logService;

    @RequestMapping("/getAll")
    public List<UserInfo> findAll(HttpServletRequest request){
        //获取token
        HttpSession session = request.getSession();
        UUID loginToken = (UUID) session.getAttribute("LoginToken");
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(loginToken);
        System.out.println(userInfo);
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
    public boolean updateUserInfo(UpdateForm updateForm, HttpServletRequest request){
        //判断当前
        //查询当前修改的用户
        UserInfo userInfo = userInfoService.findById(updateForm.getId());
        //修改用户
        userInfo.setUserName(updateForm.getUserName());
        userInfo.setAirlineCode(updateForm.getAirlineCode());

//        return false;
        logService.addLog("更新用户",request);
        return userInfoService.UpdateById(userInfo);
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public boolean deleteUserInfo(String id, HttpServletRequest request){
        logService.addLog("删除用户",request);
        return userInfoService.deleteById(id);
    }

    @RequestMapping("/getByUsername")
    public UserInfo getByUsername(AddForm addForm){
        //查询
        UserInfo userInfo = userInfoService.selectByUsername(addForm.getUserName());
        return userInfo;
    }

    @RequestMapping("/getUsernameLike")
    public List<UserInfo> getUsernameLike(AddForm addForm){
        //查询
        return userInfoService.findLikeUserName(addForm.getUserName());
    }

    @RequestMapping("/addUserInfo")
    public boolean addUserInfo(AddForm addForm,HttpServletRequest request){
        RoleInfo roleInfo = new RoleInfo();

        //查询公司是否已经有管理员了
        if(roleInfoService.selectAdminRole(addForm.getAirlineCode())){
            roleInfo.setLevel(addForm.getRole());
        }else {
            return false;
        }

        UserInfo userInfo = new UserInfo();
        //加密持久化密码
        AESUtil aesUtil = new AESUtil();
        String pass = aesUtil.AESEncode(addForm.getPass());
        userInfo.setUserPsw(pass);
        userInfo.setAirlineCode(addForm.getAirlineCode());
        userInfo.setUserName(addForm.getUserName());
        userInfo.setEnable(true);
        //设置角色
        UserInfo info = userInfoService.addUserInfo(userInfo);
        roleInfo.setAirline(info.getAirlineCode());

        if(addForm.getRole() == 2){
            roleInfo.setRoleName("admin");
        }
        if(addForm.getRole() == 1){
            roleInfo.setRoleName("user");
        }
        roleInfo.setUserId(userInfoService.selectByUsername(info.getUserName()).getKey());
        //保存角色
        RoleInfo addRoleInfo = roleInfoService.addRoleInfo(roleInfo);
        if(addRoleInfo == null){
            return false;
        }
        //记录日志
        logService.addLog("增加用户",request);
        return true;
    }

    @RequestMapping("/adminAdd")
    public boolean adminAdd(AddForm addForm,HttpServletRequest request){
        System.out.println(addForm);
        if(addForm.getAirlineCode() == null){
            HttpSession session = request.getSession();
            UUID loginToken = (UUID) session.getAttribute("LoginToken");
            UserInfo user = (UserInfo) redisTemplate.opsForValue().get(loginToken);
            UserInfo userInfo = new UserInfo();
            //加密持久化密码
            AESUtil aesUtil = new AESUtil();
            String pass = aesUtil.AESEncode(addForm.getPass());
            userInfo.setUserPsw(pass);
            userInfo.setAirlineCode(user.getAirlineCode());
            userInfo.setUserName(addForm.getUserName());
            userInfo.setEnable(true);
            UserInfo userInfo1 = userInfoService.addUserInfo(userInfo);

            //设置角色
            RoleInfo roleInfo = new RoleInfo();
            roleInfo.setLevel(1);
            roleInfo.setAirline(user.getAirlineCode());
            roleInfo.setUserId(userInfoService.selectByUsername(userInfo1.getUserName()).getKey());
            roleInfo.setRoleName("user");
            //保存角色
            RoleInfo addRoleInfo = roleInfoService.addRoleInfo(roleInfo);
            if(addRoleInfo == null){
                return false;
            }
            //记录日志
            logService.addLog("增加用户",request);
            return true;
        }else
            return false;
    }

    @RequestMapping("/getCurrentUserInfo")
    public UserInfo getCurrentUserInfo(HttpServletRequest request){
        UUID loginToken = (UUID) request.getSession().getAttribute("LoginToken");
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(loginToken);
        return userInfo;

    }

    @RequestMapping("/adminFindAll")
    public List<UserInfo> adminFindAll(HttpServletRequest request){
        //获取token
        HttpSession session = request.getSession();
        UUID loginToken = (UUID) session.getAttribute("LoginToken");
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(loginToken);
        return userInfoService.adminUserInfoList(userInfo.getAirlineCode(), "");
    }

    @RequestMapping("/adminFindLike")
    public List<UserInfo> adminFindLike(AddForm addForm, HttpServletRequest request){
        //获取token
        HttpSession session = request.getSession();
        UUID loginToken = (UUID) session.getAttribute("LoginToken");
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(loginToken);
        return userInfoService.findAdminLike(addForm.getUserName(), userInfo.getAirlineCode());
    }
}
