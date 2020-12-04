/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-04 10:09:12
 * @LastEditTime: 2020-12-04 11:45:57
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/auth/src/main/java/store/tacomall/auth/controller/AppController.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import store.tacomall.common.dto.AuthDto;
import store.tacomall.auth.service.AppService;

@RestController
@RequestMapping("/app/")
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("genAccessToken")
    public AuthDto<String> genAccessToken(@RequestParam(value = "secret") String secret) {
        return appService.genAccessToken(secret);
    }

    @PostMapping("verifyAccessToken")
    public AuthDto<String> verifyAccessToken(@RequestParam(value = "token") String token) {
        return appService.verifyAccessToken(token);
    }
}
