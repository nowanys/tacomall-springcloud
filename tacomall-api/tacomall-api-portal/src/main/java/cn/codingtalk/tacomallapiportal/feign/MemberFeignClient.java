/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-16 16:53:01
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\tacomall-api\tacomall-api-portal\src\main\java\cn\codingtalk\tacomallapiportal\feign\MemberFeignClient.java
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
import cn.codingtalk.tacomallapiportal.entity.member.Member;

@FeignClient(name = "service-biz-member", fallbackFactory = MemberFeignClientFallbackFactory.class)
public interface MemberFeignClient {
    @RequestMapping(value = "/member/getMember", method = RequestMethod.POST)
    ServiceDto<Member> getMember(@RequestBody HashMap<String, Object> map);

    @RequestMapping(value = "/member/addMember", method = RequestMethod.POST)
    ServiceDto<Member> addMember(@RequestBody HashMap<String, Object> map);
}

@Component
class MemberFeignClientFallbackFactory implements FallbackFactory<MemberFeignClient> {
    @Override
    public MemberFeignClient create(Throwable throwable) {
        return new MemberFeignClient() {
            @Override
            public ServiceDto<Member> getMember(HashMap<String, Object> map) {
                ServiceDto<Member> serviceDto = new ServiceDto<>();
                Member member = new Member();
                member.setId(0);
                serviceDto.setStatus(false);
                serviceDto.setCode(ExceptionEnum.RPC_MEMBER_ERROR.getCode());
                serviceDto.setMessage(ExceptionEnum.RPC_MEMBER_ERROR.getMessage());
                serviceDto.setData(member);
                return serviceDto;
            }

            @Override
            public ServiceDto<Member> addMember(HashMap<String, Object> map) {
                ServiceDto<Member> serviceDto = new ServiceDto<>();
                Member member = new Member();
                member.setId(0);
                serviceDto.setStatus(false);
                serviceDto.setCode(ExceptionEnum.RPC_MEMBER_ERROR.getCode());
                serviceDto.setMessage(ExceptionEnum.RPC_MEMBER_ERROR.getMessage());
                serviceDto.setData(member);
                return serviceDto;
            }
        };
    }
}
