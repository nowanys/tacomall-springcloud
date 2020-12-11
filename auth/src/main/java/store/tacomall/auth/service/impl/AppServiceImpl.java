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
package store.tacomall.auth.service.impl;

import org.springframework.stereotype.Service;

import store.tacomall.common.dto.AuthDto;
import store.tacomall.auth.service.AppService;

@Service
public class AppServiceImpl implements AppService {

    public AuthDto<String> genAccessToken(String secret) {
        AuthDto<String> responseVo = new AuthDto<>();
        responseVo.setStatus(true);
        responseVo.setData("12321");
        return responseVo;
    }

    public AuthDto<String> verifyAccessToken(String token) {
        AuthDto<String> responseVo = new AuthDto<>();
        responseVo.setStatus(true);
        responseVo.setData("验证成功");
        return responseVo;
    }
}