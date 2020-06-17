/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-17 11:36:54
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/tacomall-service/tacomall-service-biz/tacomall-service-biz-member/src/main/java/cn/codingtalk/tacomallservicebizmember/service/impl/MemberServiceImpl.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallservicebizmember.service.impl;

import java.util.HashMap;

import com.baomidou.lock.annotation.Lock4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import cn.codingtalk.tacomallcommon.dto.ServiceDto;
import cn.codingtalk.tacomallcommon.util.ExceptionUtil;
import cn.codingtalk.tacomallservicebizmember.entity.Member;
import cn.codingtalk.tacomallservicebizmember.entity.MemberWeixin;
import cn.codingtalk.tacomallservicebizmember.entity.MemberWeixinMa;
import cn.codingtalk.tacomallservicebizmember.mapper.get.GetMemberMapper;
import cn.codingtalk.tacomallservicebizmember.mapper.add.AddMemberMapper;
import cn.codingtalk.tacomallservicebizmember.mapper.add.AddMemberWeixinMapper;
import cn.codingtalk.tacomallservicebizmember.mapper.add.AddMemberWeixinMaMapper;
import cn.codingtalk.tacomallservicebizmember.service.MemberService;

@Service
public class MemberServiceImpl extends ServiceImpl<GetMemberMapper, Member> implements MemberService {

    @Autowired
    private AddMemberMapper addMemberMapper;

    @Autowired
    private AddMemberWeixinMapper addMemberWeixinMapper;

    @Autowired
    private AddMemberWeixinMaMapper addMemberWeixinMaMapper;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    public ServiceDto<Member> getMember(HashMap<String, String> condMap) {
        ServiceDto<Member> serviceDto = new ServiceDto<>();
        serviceDto.setData(baseMapper.getMember(condMap.get("key"), condMap.get("value")));
        return serviceDto;
    }

    @Lock4j
    public ServiceDto<Member> addMember(HashMap<String, String> userInfo) throws RuntimeException{
        ServiceDto<Member> serviceDto = new ServiceDto<>();
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {

            Member member = new Member();
            member.setNickname(userInfo.get("nickname"));
            member.setAvatar(userInfo.get("avatar"));
            addMemberMapper.insert(member);

            MemberWeixin memberWeixin = new MemberWeixin();
            memberWeixin.setMemberId(member.getId());
            memberWeixin.setUnionId(userInfo.get("unionId"));
            memberWeixin.setNickname(userInfo.get("nickname"));
            memberWeixin.setAvatar(userInfo.get("avatar"));
            addMemberWeixinMapper.insert(memberWeixin);

            MemberWeixinMa memberWeixinMa = new MemberWeixinMa();
            memberWeixinMa.setMemberId(member.getId());
            memberWeixinMa.setOpenId(userInfo.get("openId"));
            addMemberWeixinMaMapper.insert(memberWeixinMa);

            serviceDto.setData(member);
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(transactionStatus);
            ExceptionUtil.throwSqlException("sql错误");
        }
        return serviceDto;
    }
}
