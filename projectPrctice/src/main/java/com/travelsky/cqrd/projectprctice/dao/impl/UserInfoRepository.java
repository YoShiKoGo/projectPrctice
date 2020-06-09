package com.travelsky.cqrd.projectprctice.dao.impl;

import com.mongodb.client.result.UpdateResult;
import com.travelsky.cqrd.projectprctice.dao.UserInfoDao;
import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.vo.UserInfoPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserInfoRepository implements UserInfoDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询所有的
     * @return List<UserInfo>
     */
    @Override
    public UserInfoPageVo getUserInfoList(UserInfoPageVo page) {
        //查询可用的用户
        Criteria criteria = new Criteria().where("isEnable").is(true);
        //判断页数是否为空
        if(page.getCurrentPage() == null || page.getCurrentPage() <= 0){
            page.setCurrentPage(1);
        }
        //计算总页数
        long count = mongoTemplate.count(Query.query(criteria), UserInfo.class);
        Long pageNum = (count+5-1)/5;
        page.setPageNum(pageNum.intValue());
        //判断是否超出总页数
        if(page.getCurrentPage()>page.getPageNum()){
            page.setCurrentPage(page.getPageNum());
        }
        //创建分页条件
        PageRequest pageRequest = PageRequest.of(page.getCurrentPage() - 1,5, Sort.by(Sort.Direction.ASC, "id"));
        //设置查询条件、设置分页
        Query query = Query.query(criteria).with(pageRequest);
        //查询
        List<UserInfo> list = mongoTemplate.find(query, UserInfo.class);
        //封装到page对象中
        page.setUserInfoList(list);

        return page;
    }

    /**
     * 根据平台进行查询用户信息
     * @param airlineCode
     * @return List<UserInfo>
     */
    @Override
    public UserInfoPageVo findUserInfoByAirline(String airlineCode, UserInfoPageVo page) {
        //根据平台查询可用的用户
        Criteria criteria = new Criteria().andOperator(
                Criteria.where("isEnable").is(true),
                Criteria.where("airlineCode").is(airlineCode));
        //判断页数是否为空
        if(page.getCurrentPage() == null || page.getCurrentPage() <= 0){
            page.setCurrentPage(1);
        }
        //计算总页数
        long count = mongoTemplate.count(Query.query(criteria), UserInfo.class);
        Long pageNum = (count+5-1)/5;
        page.setPageNum(pageNum.intValue());
        //判断是否超出总页数
        if(page.getCurrentPage()>page.getPageNum()){
            page.setCurrentPage(page.getPageNum());
        }
        //创建分页条件
        PageRequest pageRequest = PageRequest.of(page.getCurrentPage() - 1,5, Sort.by(Sort.Direction.DESC, "sendDate"));
        //设置查询条件、设置分页
        Query query = Query.query(criteria).with(pageRequest);
        //查询
        List<UserInfo> list = mongoTemplate.find(query, UserInfo.class);
        //封装到page对象中
        page.setUserInfoList(list);
        return page;
    }

    /**
     * 删除用户 - enable = false
     * @param id
     * @return boolean 是否成功删除
     */
    @Override
    public boolean deleteUserInfo(String id) {
        //根据_id删除
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = new Update().set("isEnable", false);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, UserInfo.class);
        //是否删除
        if(updateResult.getModifiedCount() <= 0){
            return false;
        }
        return true;
    }
    /**
     * 根据_id查询
     */
    @Override
    public UserInfo findUserInfoById(String id) {
        return mongoTemplate.findById(id, UserInfo.class);
    }

    /**
     * 修改用户
     * @param userInfo
     * @return boolean 是否成功修改
     */
    @Override
    public boolean updateUserInfo(UserInfo userInfo) {
        mongoTemplate.save(userInfo);
        return true;
    }

    /**
     * 平台删除(平台下用户删除)
     * @param airlineCode
     * @return
     */
    @Override
    public boolean deleteUserInfoByAirlineCode(String airlineCode) {
        Query query = Query.query(Criteria.where("airlineCode").is(airlineCode));
        Update update = new Update().set("isEnable", false);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, UserInfo.class);
        //是否删除
        if(updateResult.getModifiedCount() <= 0){
            return false;
        }
        return true;
    }

    /**
     * 根据名字查询
     * @param username
     * @return
     */
    @Override
    public UserInfo selectByUsername(String username) {
        Query query = Query.query(Criteria.where("userName").is(username));
        UserInfo userInfo = mongoTemplate.findOne(query, UserInfo.class);
        return userInfo;
    }

    @Override
    public List<UserInfo> findAll() {
        return mongoTemplate.findAll(UserInfo.class);
    }

}
