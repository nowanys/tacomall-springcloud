/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-11-13 17:57:02
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/api/portal/src/main/java/store/tacomall/apiportal/service/MemberService.java
 * @Just do what I think it is right
 */
package store.tacomall.apiportal.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import store.tacomall.common.vo.ResponseVo;
import store.tacomall.apiportal.entity.PortalMember;
import store.tacomall.common.db.entity.member.Member;

public interface MemberService extends IService<Member> {

    ResponseVo<String> wxMaLogin(String appid, JSONObject json) throws Exception;

    ResponseVo<PortalMember> info() throws Exception;
}
