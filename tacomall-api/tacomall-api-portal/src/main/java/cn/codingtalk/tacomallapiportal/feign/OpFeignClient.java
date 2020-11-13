/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-15 15:36:35
 * @LastEditTime: 2020-06-17 14:16:47
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/tacomall-api/tacomall-api-portal/src/main/java/cn/codingtalk/tacomallapiportal/feign/OpFeignClient.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallapiportal.feign;

import java.util.HashMap;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import cn.codingtalk.tacomallcommon.dto.ServiceDto;
import cn.codingtalk.tacomallcommon.enumeration.ExceptionEnum;

@FeignClient(name = "service-biz-op", fallbackFactory = OpFeignClientFallbackFactory.class)
public interface OpFeignClient {
    @RequestMapping(value = "/WxMa/{APPID}/user/login", method = RequestMethod.GET)
    ServiceDto<HashMap<String, Object>> wxMaUserlogin(@PathVariable String APPID, @RequestParam("code") String code);

    @RequestMapping(value = "/WxMa/{APPID}/user/info", method = RequestMethod.GET)
    ServiceDto<HashMap<String, Object>> wxMaUserInfo(@PathVariable String APPID,
            @RequestParam("sessionKey") String sessionKey, @RequestParam("signature") String signature,
            @RequestParam("rawData") String rawData, @RequestParam("encryptedData") String encryptedData,
            @RequestParam("iv") String iv);
}

@Component
class OpFeignClientFallbackFactory implements FallbackFactory<OpFeignClient> {
    @Override
    public OpFeignClient create(Throwable throwable) {
        return new OpFeignClient() {
            @Override
            public ServiceDto<HashMap<String, Object>> wxMaUserlogin(@PathVariable String APPID,
                    @RequestParam("code") String code) {
                ServiceDto<HashMap<String, Object>> serviceDto = new ServiceDto<>();
                HashMap<String, Object> hashMap = new HashMap<>();
                serviceDto.setStatus(false);
                serviceDto.setCode(ExceptionEnum.RPC_OP_ERROR.getCode());
                serviceDto.setMessage(ExceptionEnum.RPC_OP_ERROR.getMessage());
                serviceDto.setData(hashMap);
                return serviceDto;
            }

            @Override
            public ServiceDto<HashMap<String, Object>> wxMaUserInfo(@PathVariable String APPID,
                    @RequestParam("sessionKey") String sessionKey, @RequestParam("signature") String signature,
                    @RequestParam("rawData") String rawData, @RequestParam("encryptedData") String encryptedData,
                    @RequestParam("iv") String iv) {
                ServiceDto<HashMap<String, Object>> serviceDto = new ServiceDto<>();
                HashMap<String, Object> hashMap = new HashMap<>();
                serviceDto.setStatus(false);
                serviceDto.setCode(ExceptionEnum.RPC_OP_ERROR.getCode());
                serviceDto.setMessage(ExceptionEnum.RPC_OP_ERROR.getMessage());
                serviceDto.setData(hashMap);
                return serviceDto;
            }
        };
    }
}
