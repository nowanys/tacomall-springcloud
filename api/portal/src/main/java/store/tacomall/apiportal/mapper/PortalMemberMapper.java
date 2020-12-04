/***
 * @Author: 码上talk|RC
 * @Date: 2020-11-13 17:30:04
 * @LastEditTime: 2020-11-13 17:38:21
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/api/portal/src/main/java/store/tacomall/apiportal/dao/MemberDao.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiportal.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import store.tacomall.apiportal.entity.PortalMember;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

@Repository
public interface PortalMemberMapper extends BaseMapper<PortalMember> {

    PortalMember getWeixinMaMember(@Param(Constants.WRAPPER) Wrapper<PortalMember> wrapper);
}
