/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-04 10:09:26
 * @LastEditTime: 2020-12-04 11:33:23
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/auth/src/main/java/store/tacomall/auth/service/AppService.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.authorize.service;

import store.tacomall.common.dto.AuthorizeDto;

public interface ServiceService {

    AuthorizeDto<String> genAccessToken(String secret);

    AuthorizeDto<String> verifyAccessToken(String token);
}
