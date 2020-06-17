/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-17 15:15:06
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/tacomall-api/tacomall-api-portal/src/main/java/cn/codingtalk/tacomallapiportal/service/MemberService.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallapiportal.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.codingtalk.tacomallcommon.vo.ResponseVo;
import cn.codingtalk.tacomallapiportal.entity.member.Member;

public interface MemberService extends IService<Member> {

    ResponseVo<String> wxMaLogin(String iv, String code, String appid, String rawData, String signature, String encryptedData)  throws Exception;

    ResponseVo<Member> info();
}
