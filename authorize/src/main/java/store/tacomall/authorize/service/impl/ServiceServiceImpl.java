/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-04 10:09:48
 * @LastEditTime: 2020-12-04 15:14:26
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/auth/src/main/java/store/tacomall/auth/service/impl/AppServiceImpl.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.authorize.service.impl;

import org.springframework.stereotype.Service;

import store.tacomall.common.dto.AuthorizeDto;
import store.tacomall.authorize.service.ServiceService;

@Service
public class ServiceServiceImpl implements ServiceService {

    public AuthorizeDto<String> genAccessToken(String secret) {
        AuthorizeDto<String> responseVo = new AuthorizeDto<>();
        responseVo.setStatus(true);
        responseVo.setData("12321");
        return responseVo;
    }

    public AuthorizeDto<String> verifyAccessToken(String token) {
        AuthorizeDto<String> responseVo = new AuthorizeDto<>();
        responseVo.setStatus(true);
        responseVo.setData("验证成功");
        return responseVo;
    }
}