/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-04 10:41:27
 * @LastEditTime: 2020-12-04 11:48:02
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/common/src/main/java/store/tacomall/common/feign/AuthFeignClient.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import store.tacomall.common.dto.AuthDto;

@FeignClient(name = "auth")
public interface AuthFeignClient {
    @RequestMapping(value = "/app/genAccessToken", method = RequestMethod.POST)
    AuthDto<String> genAppAccessToken(@RequestParam(value = "secret") String secret);

    @RequestMapping(value = "/app/verifyAccessToken", method = RequestMethod.POST)
    AuthDto<String> virifyAppAccessToken(@RequestParam(value = "token") String token);
}