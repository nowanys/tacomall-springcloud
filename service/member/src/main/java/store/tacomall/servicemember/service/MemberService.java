/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-12-04 08:40:13
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/app/member/src/main/java/store/tacomall/servicemember/service/MemberService.java
 * @Just do what I think it is right
 */
package store.tacomall.servicemember.service;

import com.alibaba.fastjson.JSONObject;

import store.tacomall.common.dto.AppDto;
import store.tacomall.common.db.entity.member.Member;

public interface MemberService {

    AppDto<Member> get(JSONObject json);

    AppDto<Member> add(JSONObject json) throws RuntimeException;
}