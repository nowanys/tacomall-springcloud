/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-15 15:36:35
 * @LastEditTime: 2020-12-04 09:22:26
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/common/src/main/java/store/tacomall/common/feign/OpFeignClient.java
 * @Just do what I think it is right
 */
package store.tacomall.common.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import store.tacomall.common.dto.AppDto;
import store.tacomall.common.config.FeignConfig;

@Component
@FeignClient(name = "app-op", configuration = FeignConfig.class)
public interface OpFeignClient {

    @RequestMapping(value = "/WxMa/{APPID}/user/login", method = RequestMethod.GET)
    AppDto<Map<String, String>> wxMaUserLogin(@PathVariable String APPID, @RequestParam("code") String code);

    @RequestMapping(value = "/WxMa/{APPID}/user/info", method = RequestMethod.GET)
    AppDto<Map<String, Object>> wxMaUserInfo(@PathVariable String APPID, @RequestParam("sessionKey") String sessionKey,
            @RequestParam("signature") String signature, @RequestParam("rawData") String rawData,
            @RequestParam("encryptedData") String encryptedData, @RequestParam("iv") String iv);
}
