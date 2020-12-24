/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-04 10:41:27
 * @LastEditTime: 2020-12-07 16:22:09
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/common/src/main/java/store/tacomall/common/feign/AuthFeignClient.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.feign;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import store.tacomall.common.dto.AuthorizeDto;

@FeignClient(name = "authorize")
public interface AuthorizeFeignClient {
    @RequestMapping(value = "/service/genAccessToken", method = RequestMethod.POST)
    AuthorizeDto<String> genServiceAccessToken(@RequestParam(value = "secret") String secret);

    @RequestMapping(value = "/service/verifyAccessToken", method = RequestMethod.POST)
    AuthorizeDto<String> verifyServiceAccessToken(@RequestParam(value = "token") String token);

    @RequestMapping(value = "/user/genAccessToken", method = RequestMethod.POST)
    AuthorizeDto<String> genUserAccessToken(@RequestParam(value = "issuer") String issuer, @RequestBody JSONObject json);

    @RequestMapping(value = "/user/verifyAccessToken", method = RequestMethod.POST)
    AuthorizeDto<Map<String, String>> verifyUserAccessToken(@RequestParam(value = "issuer") String issuer,
            @RequestParam(value = "token") String token);
}