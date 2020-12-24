/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-07 09:36:05
 * @LastEditTime: 2020-12-07 16:04:55
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/auth/src/main/java/store/tacomall/auth/service/impl/UserServiceImpl.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.authorize.service.impl;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.tacomall.common.dto.AuthorizeDto;
import store.tacomall.common.util.JwtUtil;
import store.tacomall.authorize.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtUtil jwtUtil;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AuthorizeDto<String> genAccessToken(String issuer, JSONObject json) throws Exception {
        AuthorizeDto<String> responseVo = new AuthorizeDto<>();
        jwtUtil.setISSUER(issuer);
        responseVo.setStatus(true);
        responseVo.setData(jwtUtil.create(JSONObject.toJavaObject(json, Map.class)));
        return responseVo;
    }

    public AuthorizeDto<Map<String, String>> verifyAccessToken(String issuer, String token) throws Exception {
        AuthorizeDto<Map<String, String>> responseVo = new AuthorizeDto<>();
        jwtUtil.setISSUER(issuer);
        responseVo.setStatus(true);
        responseVo.setData(jwtUtil.verify(token));
        return responseVo;
    }
}
