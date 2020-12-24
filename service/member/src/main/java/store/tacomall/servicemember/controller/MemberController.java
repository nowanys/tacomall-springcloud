/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-12-04 08:40:05
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/app/member/src/main/java/store/tacomall/servicemember/controller/MemberController.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-17 14:53:30
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/tacomall-service/app/service-member/src/main/java/cn/codingtalk/tacomallservicemember/controller/MemberController.java
 * @Just do what I think it is right
 */
package store.tacomall.servicemember.controller;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import store.tacomall.common.dto.AppDto;
import store.tacomall.common.db.entity.member.Member;
import store.tacomall.servicemember.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/get")
    public AppDto<Member> get(@RequestBody JSONObject json) {
        return this.memberService.get(json);
    }

    @PostMapping("/add")
    public AppDto<Member> add(@RequestBody JSONObject json) {
        return this.memberService.add(json);
    }
}
