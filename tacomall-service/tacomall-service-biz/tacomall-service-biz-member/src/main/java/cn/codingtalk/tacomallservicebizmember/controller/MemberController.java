/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-17 14:53:30
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/tacomall-service/tacomall-service-biz/tacomall-service-biz-member/src/main/java/cn/codingtalk/tacomallservicebizmember/controller/MemberController.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallservicebizmember.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import cn.codingtalk.tacomallcommon.dto.ServiceDto;
import cn.codingtalk.tacomallservicebizmember.entity.Member;
import cn.codingtalk.tacomallservicebizmember.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/getMember")
    public ServiceDto<Member> getMember(@RequestBody HashMap<String, String> condMap) {
        return this.memberService.getMember(condMap);
    }

    @PostMapping("/addMember")
    public ServiceDto<Member> addMember(@RequestBody HashMap<String, String> userInfo) {
        return this.memberService.addMember(userInfo);
    }
}
