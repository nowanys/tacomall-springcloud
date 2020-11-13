/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-16 17:34:46
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\tacomall-service\tacomall-service-biz\tacomall-service-biz-member\src\main\java\cn\codingtalk\tacomallservicebizmember\service\MemberService.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallservicebizmember.service;

import java.util.HashMap;

import cn.codingtalk.tacomallcommon.dto.ServiceDto;
import cn.codingtalk.tacomallservicebizmember.entity.Member;

public interface MemberService {

    ServiceDto<Member> getMember(HashMap<String, String> condMap);

    ServiceDto<Member> addMember(HashMap<String, String> userInfo) throws RuntimeException;
}