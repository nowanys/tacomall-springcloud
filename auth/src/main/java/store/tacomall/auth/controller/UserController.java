/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-07 09:35:47
 * @LastEditTime: 2020-12-07 16:05:29
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/auth/src/main/java/store/tacomall/auth/controller/UserController.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.auth.controller;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import store.tacomall.common.dto.AuthDto;
import store.tacomall.auth.service.UserService;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("genAccessToken")
    public AuthDto<String> genAccessToken(@RequestParam(value = "issuer") String issuer, @RequestBody JSONObject json)
            throws Exception {
        return userService.genAccessToken(issuer, json);
    }

    @PostMapping("verifyAccessToken")
    public AuthDto<Map<String, String>> verifyAccessToken(@RequestParam(value = "issuer") String issuer,
            @RequestParam(value = "token") String token) throws Exception {
        return userService.verifyAccessToken(issuer, token);
    }
}
