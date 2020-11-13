/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-11-13 10:05:46
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/api/portal/src/main/java/store/tacomall/apiportal/feign/MemberFeignClient.java
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
import store.tacomall.db.entity.member.Member;

@FeignClient(name = "app-member", fallbackFactory = MemberFeignClientFallbackFactory.class)
public interface MemberFeignClient {
    @RequestMapping(value = "/member/get", method = RequestMethod.POST)
    AppDto<Member> get(@RequestBody Map<String, Object> map);

    @RequestMapping(value = "/member/add", method = RequestMethod.POST)
    AppDto<Member> add(@RequestBody Map<String, Object> map);
}

@Component
class MemberFeignClientFallbackFactory implements FallbackFactory<MemberFeignClient> {
    @Override
    public MemberFeignClient create(Throwable throwable) {
        return new MemberFeignClient() {
            @Override
            public AppDto<Member> get(Map<String, Object> map) {
                ExceptionUtil.throwServerException("业务繁忙，请稍后重试~~");
                return new AppDto<Member>();
            }

            @Override
            public AppDto<Member> add(Map<String, Object> map) {
                ExceptionUtil.throwServerException("业务繁忙，请稍后重试~~");
                return new AppDto<Member>();
            }
        };
    }
}
