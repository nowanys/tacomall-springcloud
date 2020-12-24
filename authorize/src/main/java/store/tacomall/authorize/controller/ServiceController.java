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
package store.tacomall.authorize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import store.tacomall.common.dto.AuthorizeDto;
import store.tacomall.authorize.service.ServiceService;

@RestController
@RequestMapping("/service/")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping("genAccessToken")
    public AuthorizeDto<String> genAccessToken(@RequestParam(value = "secret") String secret) {
        return serviceService.genAccessToken(secret);
    }

    @PostMapping("verifyAccessToken")
    public AuthorizeDto<String> verifyAccessToken(@RequestParam(value = "token") String token) {
        return serviceService.verifyAccessToken(token);
    }
}
