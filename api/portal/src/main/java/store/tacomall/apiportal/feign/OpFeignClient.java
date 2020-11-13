/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-15 15:36:35
 * @LastEditTime: 2020-11-13 10:44:51
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/api/portal/src/main/java/store/tacomall/apiportal/feign/OpFeignClient.java
 * @Just do what I think it is right
 */
package store.tacomall.apiportal.feign;

import java.util.Map;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import store.tacomall.common.dto.AppDto;
import store.tacomall.common.util.ExceptionUtil;

@FeignClient(name = "app-op", fallbackFactory = OpFeignClientFallbackFactory.class)
public interface OpFeignClient {

    @RequestMapping(value = "/WxMa/{APPID}/user/login", method = RequestMethod.GET)
    AppDto<Map<String, String>> wxMaUserLogin(@PathVariable String APPID, @RequestParam("code") String code);

    @RequestMapping(value = "/WxMa/{APPID}/user/info", method = RequestMethod.GET)
    AppDto<Map<String, Object>> wxMaUserInfo(@PathVariable String APPID, @RequestParam("sessionKey") String sessionKey,
            @RequestParam("signature") String signature, @RequestParam("rawData") String rawData,
            @RequestParam("encryptedData") String encryptedData, @RequestParam("iv") String iv);
}

@Component
class OpFeignClientFallbackFactory implements FallbackFactory<OpFeignClient> {
    @Override
    public OpFeignClient create(Throwable throwable) {
        return new OpFeignClient() {

            @Override
            public AppDto<Map<String, String>> wxMaUserLogin(@PathVariable String APPID,
                    @RequestParam("code") String code) {
                ExceptionUtil.throwServerException("业务繁忙，请稍后重试~~");
                return new AppDto<Map<String, String>>();
            }

            @Override
            public AppDto<Map<String, Object>> wxMaUserInfo(@PathVariable String APPID,
                    @RequestParam("sessionKey") String sessionKey, @RequestParam("signature") String signature,
                    @RequestParam("rawData") String rawData, @RequestParam("encryptedData") String encryptedData,
                    @RequestParam("iv") String iv) {
                ExceptionUtil.throwServerException("业务繁忙，请稍后重试~~");
                return new AppDto<Map<String, Object>>();
            }
        };
    }
}
