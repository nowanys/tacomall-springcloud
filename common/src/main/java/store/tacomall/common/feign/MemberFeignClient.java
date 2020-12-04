/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-12-04 10:03:30
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/common/src/main/java/store/tacomall/common/feign/MemberFeignClient.java
 * @Just do what I think it is right
 */
package store.tacomall.common.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import store.tacomall.common.dto.AppDto;
import store.tacomall.common.db.entity.member.Member;
import store.tacomall.common.config.FeignConfig;

@FeignClient(name = "app-member", configuration = FeignConfig.class)
public interface MemberFeignClient {
    @RequestMapping(value = "/member/get", method = RequestMethod.POST)
    AppDto<Member> get(@RequestBody Map<String, Object> map);

    @RequestMapping(value = "/member/add", method = RequestMethod.POST)
    AppDto<Member> add(@RequestBody Map<String, Object> map);
}