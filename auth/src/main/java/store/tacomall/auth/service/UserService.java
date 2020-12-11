/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-07 09:35:56
 * @LastEditTime: 2020-12-07 16:05:02
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/auth/src/main/java/store/tacomall/auth/service/UserService.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.auth.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import store.tacomall.common.dto.AuthDto;

public interface UserService {

    AuthDto<String> genAccessToken(String issuer, JSONObject json) throws Exception;

    AuthDto<Map<String, String>> verifyAccessToken(String issuer, String token) throws Exception;
}
