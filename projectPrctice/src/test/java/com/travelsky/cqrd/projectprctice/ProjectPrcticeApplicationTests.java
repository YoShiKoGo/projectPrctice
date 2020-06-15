package com.travelsky.cqrd.projectprctice;

import com.travelsky.cqrd.projectprctice.dao.LogDao;
import com.travelsky.cqrd.projectprctice.dao.RoleInfoDao;
import com.travelsky.cqrd.projectprctice.entity.UserInfo;
import com.travelsky.cqrd.projectprctice.services.UserInfoService;
import com.travelsky.cqrd.projectprctice.utils.AESUtil;
import com.travelsky.cqrd.projectprctice.vo.LoginNumVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
class ProjectPrcticeApplicationTests {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    RedisTemplate jsonRedisTemplate;
    @Autowired
    UserInfoService userInfoService;

    @Test
    void contextLoads() {
//        System.out.println(mongoTemplate.findAll(UserInfo.class));
//        Query query = Query.query(Criteria.where("userName").is("admin"));
        Query query = Query.query(Criteria.where("_id").is("5edf0c64260e0000cd0024e2"));
        UserInfo userInfo = mongoTemplate.findOne(query, UserInfo.class);
//        System.out.println(userInfo);
//        System.out.println(mongoTemplate.findById("5edda21dda5a0000b7007083", UserInfo.class));
        Query query1 = Query.query(Criteria.where("_id").is(userInfo.getKey()));
        Update update =Update.update("userName","aaaaa");

        mongoTemplate.updateFirst(query1, update, userInfo.getClass());


    }
    @Test
    void testLike(){
        System.out.println(mongoTemplate.find(Query.query(Criteria.where("userName").regex("ad")),
                UserInfo.class));
    }

    @Test
    void testSave(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("aaaaa");
        userInfo.setUserPsw("123456");
        userInfo.setAirlineCode("AC");
        mongoTemplate.save(userInfo);
    }

    @Test
    void testAes() throws Exception {
        AESUtil aesUtil = new AESUtil();
        SecretKey key = aesUtil.getKeyByPass();
        //根据指定算法AES自成密码器
        Cipher cipher=Cipher.getInstance("AES");
        //初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
        byte [] byte_encode="content".getBytes("utf-8");
        //9.根据密码器的初始化方式--加密：将数据加密
        byte [] byte_AES=cipher.doFinal(byte_encode);
        //10.将加密后的数据转换为字符串
        String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
        System.out.println(AES_encode);

    }
    @Test
    void AESDncode()  throws  Exception{
        AESUtil aesUtil = new AESUtil();
        SecretKey key = aesUtil.getKeyByPass();
        //根据指定算法AES自成密码器
        Cipher cipher=Cipher.getInstance("AES");
        //解密
        cipher.init(Cipher.DECRYPT_MODE, key);
        //8.将加密并编码后的内容解码成字节数组
        byte [] byte_content= new BASE64Decoder().decodeBuffer("ln3RYoYuORJalSMeWD686w==");
        //解密
        byte [] byte_decode=cipher.doFinal(byte_content);
        String AES_decode=new String(byte_decode,"utf-8");
        System.out.println(AES_decode);
    }

    @Test
    void testAES(){
        AESUtil aesUtil = new AESUtil();
        String s = aesUtil.AESEncode("123456");
        System.out.println("加密内容："+s);
        String dncode = aesUtil.AESDncode(s);
        System.out.println("解密后为："+dncode);
    }

    @Test
    void testRedis(){
        UserInfo info = mongoTemplate.findOne(Query.query(Criteria.where("_id").is("5edf0c64260e0000cd0024e2")), UserInfo.class);
        System.out.println(info);
        jsonRedisTemplate.opsForValue().set("userInfo",info);

    }

    @Autowired
    LogDao logDao;

    @Test
    void testLogDao(){
        System.out.println(logDao.findAllLog());
        System.out.println(logDao.findLogByUserName("admin"));
    }

    @Autowired
    RoleInfoDao roleInfoDao;
    @Test
    void testRoleDao(){
//        System.out.println(roleInfoDao.findRoleByUserId("5edda21dda5a0000b7007083").getLevel());

//        Criteria criteria = new Criteria().andOperator(
//                Criteria.where("isEnable").is(true),
//                Criteria.where("airlineCode").is("CA"));
//        Criteria username1 = new Criteria().where("userName").is("admin");
//        Criteria c = new Criteria().andOperator(criteria, username1);
//        System.out.println(mongoTemplate.find(Query.query(c), UserInfo.class));
        System.out.println(userInfoService.adminUserInfoList("CA", "admin"));

    }

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void testDate(){
//        LocalDateTime now = LocalDateTime.now();
//        int dayOfMonth = now.getDayOfMonth();
//        System.out.println(dayOfMonth);
//        int month = now.getMonthValue();
//        System.out.println(month);
//        LoginNumVo admin = (LoginNumVo) redisTemplate.opsForValue().get("admin");
//        System.out.println(admin);
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get("cf458b05-f262-4761-8e6e-62ac695abf54");
        System.out.println(userInfo);

    }


}
