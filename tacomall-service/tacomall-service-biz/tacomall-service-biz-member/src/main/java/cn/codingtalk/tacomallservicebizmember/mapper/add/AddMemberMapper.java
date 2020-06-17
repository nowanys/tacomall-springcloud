/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-15 14:45:44
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\tacomall-service\tacomall-service-biz\tacomall-service-biz-member\src\main\java\cn\codingtalk\tacomallservicebizmember\mapper\add\AddMemberMapper.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallservicebizmember.mapper.add;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.codingtalk.tacomallservicebizmember.entity.Member;

@Repository
public interface AddMemberMapper extends BaseMapper<Member> {
}
