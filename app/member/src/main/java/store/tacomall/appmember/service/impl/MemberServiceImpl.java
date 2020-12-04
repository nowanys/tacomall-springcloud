/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-12-04 08:40:30
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/app/member/src/main/java/store/tacomall/appmember/service/impl/MemberServiceImpl.java
 * @Just do what I think it is right
 */
package store.tacomall.appmember.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import cn.hutool.core.util.ObjectUtil;

import com.baomidou.lock.annotation.Lock4j;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import store.tacomall.common.dto.AppDto;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.common.db.entity.member.Member;
import store.tacomall.common.db.entity.member.MemberStatisticsInfo;
import store.tacomall.common.db.mapper.member.MemberMapper;
import store.tacomall.common.db.mapper.member.MemberStatisticsInfoMapper;
import store.tacomall.appmember.service.MemberService;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    MemberStatisticsInfoMapper memberStatisticsInfoMapper;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    public AppDto<Member> get(JSONObject json) {
        AppDto<Member> appDto = new AppDto<>();
        LambdaQueryWrapper<Member> q = new QueryWrapper<Member>().lambda();
        if (ObjectUtil.isNotEmpty(json.getJSONObject("query"))
                && ObjectUtil.isNotEmpty(json.getJSONObject("query").get("openId"))) {
            q.inSql(Member::getId, String.format("select member_id from member_weixin_ma where open_id = '%s'",
                    json.getJSONObject("query").get("openId")));
        }
        appDto.setData(baseMapper.getMember(q));
        return appDto;
    }

    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    @Lock4j
    public AppDto<Member> add(JSONObject json) throws RuntimeException {
        AppDto<Member> appDto = new AppDto<>();
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {

            Member member = new Member();
            member.setNickname(json.getString("nickname"));
            member.setAvatar(json.getString("avatar"));
            baseMapper.insert(member);

            MemberStatisticsInfo memberStatisticsInfo = new MemberStatisticsInfo();
            memberStatisticsInfo.setMemberId(member.getId());
            memberStatisticsInfo.setConsumeAmount(BigDecimal.ZERO);
            memberStatisticsInfo.setOrderNonPaymentCount(0);
            memberStatisticsInfo.setOrderPaidCount(0);
            memberStatisticsInfo.setOrderDoneCount(0);
            memberStatisticsInfo.setOrderReturnCount(0);
            memberStatisticsInfo.setIntegralCount(0);
            memberStatisticsInfo.setGrowthCount(0);
            memberStatisticsInfoMapper.insert(memberStatisticsInfo);
            appDto.setData(member);
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(transactionStatus);
            ExceptionUtil.throwSqlException("sql错误");
        }
        return appDto;
    }
}
