/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-16 17:43:10
 * @LastEditTime: 2020-06-16 17:43:53
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\tacomall-api\tacomall-api-portal\src\main\java\cn\codingtalk\tacomallapiportal\mapper\member\MemberMapper.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallapiportal.mapper.member;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.codingtalk.tacomallapiportal.entity.member.Member;

@Repository
public interface MemberMapper extends BaseMapper<Member> {

    Member getMember(String key, String value);
}
