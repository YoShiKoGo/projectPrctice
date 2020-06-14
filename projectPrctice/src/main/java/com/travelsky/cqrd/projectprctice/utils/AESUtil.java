package com.travelsky.cqrd.projectprctice.utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author jytian
 * @version 1.0
 * @description
 * @date 2020/6/11 13:59
 */



public class AESUtil{


        /**
         * 随机生成秘钥
         */
        public void getKey() {
            try {
                KeyGenerator kg = KeyGenerator.getInstance("AES");
                kg.init(128);
                //要生成多少位，只需要修改这里即可128, 192或256
                SecretKey sk = kg.generateKey();
                byte[] b = sk.getEncoded();
                String s = byteToHexString(b);
                System.out.println(s);
                System.out.println("十六进制密钥长度为"+s.length());
                System.out.println("二进制密钥的长度为"+s.length()*4);
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                System.out.println("没有此算法。");
            }
        }
        /**
         * 使用指定的字符串生成秘钥
         */
        public SecretKey getKeyByPass() {
            //生成秘钥
            String password="passwordkey";
            try {
                KeyGenerator kg = KeyGenerator.getInstance("AES");
                // kg.init(128);//要生成多少位，只需要修改这里即可128, 192或256
                //SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以生成的秘钥就一样。
                kg.init(128, new SecureRandom(password.getBytes()));
                //产生秘钥
                SecretKey sk = kg.generateKey();
                //获得原始对称密钥的字节数组
                byte[] b = sk.getEncoded();
                //根据字节数组生成AES密钥
                SecretKey key=new SecretKeySpec(b, "AES");
                return key;
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                System.out.println("没有此算法。");
                return null;
            }
        }
        /**
         * byte数组转化为16进制字符串
         * @param bytes
         * @return
         */
        public static String byteToHexString(byte[] bytes) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String strHex=Integer.toHexString(bytes[i]);
                if(strHex.length() > 3) {
                    sb.append(strHex.substring(6));
                } else {
                    if(strHex.length() < 2) {
                        sb.append("0" + strHex);
                    } else {
                        sb.append(strHex);
                    }
                }
            }
            return sb.toString();
        }
    /**
     * 加密
     */
    public String AESEncode(String content){
        SecretKey key = getKeyByPass();
        try {
            //根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            //初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte [] byte_encode=content.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte [] byte_AES=cipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
            return AES_encode;
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;

    }
    /**
     * 解密
     */
    public String AESDncode(String content){
        SecretKey key = getKeyByPass();
        try {
            //根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            //解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
            //解密
            byte [] byte_decode=cipher.doFinal(byte_content);
            String AES_decode=new String(byte_decode,"utf-8");
            return AES_decode;
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


}