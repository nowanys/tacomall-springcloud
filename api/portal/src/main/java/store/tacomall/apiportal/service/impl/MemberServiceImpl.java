/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-12-04 11:17:44
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/api/portal/src/main/java/store/tacomall/apiportal/service/impl/MemberServiceImpl.java
 * @Just do what I think it is right
 */
package store.tacomall.apiportal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import store.tacomall.common.vo.ResponseVo;
import store.tacomall.common.dto.AppDto;
import store.tacomall.common.util.IntUtil;
import store.tacomall.common.util.JwtUtil;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.common.util.RequestUtil;
import store.tacomall.common.enumeration.BizEnum;
import store.tacomall.common.db.entity.member.Member;
import store.tacomall.common.db.entity.member.MemberWeixin;
import store.tacomall.common.db.entity.member.MemberWeixinMa;
import store.tacomall.common.db.mapper.member.MemberMapper;
import store.tacomall.common.db.mapper.member.MemberWeixinMapper;
import store.tacomall.common.db.mapper.member.MemberWeixinMaMapper;
import store.tacomall.apiportal.service.MemberService;
import store.tacomall.common.feign.MemberFeignClient;
import store.tacomall.common.feign.OpFeignClient;
import store.tacomall.apiportal.config.WxMiniappConfig;
import store.tacomall.apiportal.entity.PortalMember;
import store.tacomall.apiportal.mapper.PortalMemberMapper;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberWeixinMapper memberWeixinMapper;

    @Autowired
    private MemberWeixinMaMapper memberWeixinMaMapper;

    @Autowired
    private PortalMemberMapper memberDao;

    @Autowired
    private MemberFeignClient memberFeignClient;

    @Autowired
    private OpFeignClient opFeignClient;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @LcnTransaction
    public ResponseVo<String> wxMaLogin(String appid, JSONObject json) throws Exception {
        ResponseVo<String> responseVo = new ResponseVo<>();
        String iv = json.getString("iv");
        String code = json.getString("code");
        String rawData = json.getString("rawData");
        String signature = json.getString("signature");
        String encryptedData = json.getString("encryptedData");
        String token = "";

        AppDto<Map<String, String>> wxMaUserLoginDto = this.opFeignClient.wxMaUserLogin(WxMiniappConfig.appid, code);
        AppDto<Map<String, Object>> wxMaUserInfoDto = this.opFeignClient.wxMaUserInfo(WxMiniappConfig.appid,
                wxMaUserLoginDto.getData().get("sessionKey").toString(), signature, rawData, encryptedData, iv);
        Map<String, Object> memberRequestJson = new HashMap<>();
        Map<String, String> memberRequestJsonQuery = new HashMap<>();
        Map<String, Object> wxMaUserInfo = wxMaUserInfoDto.getData();
        memberRequestJsonQuery.put("openId", (String) wxMaUserInfo.get("openId"));
        memberRequestJson.put("query", memberRequestJsonQuery);
        AppDto<Member> memberDto = this.memberFeignClient.get(memberRequestJson);
        Member member = memberDto.getData();
        if (ObjectUtil.isNull(member)) {
            AppDto<Member> memberAddDto = this.memberFeignClient.add(wxMaUserInfo);
            TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            try {
                member = memberAddDto.getData();
                MemberWeixin memberWeixin = new MemberWeixin();
                memberWeixin.setMemberId(member.getId());
                memberWeixin.setUnionId((String) wxMaUserInfo.get("unionId"));
                memberWeixin.setNickname((String) wxMaUserInfo.get("nickname"));
                memberWeixin.setAvatar((String) wxMaUserInfo.get("avatar"));
                memberWeixinMapper.insert(memberWeixin);

                MemberWeixinMa memberWeixinMa = new MemberWeixinMa();
                memberWeixinMa.setMemberId(member.getId());
                memberWeixinMa.setOpenId((String) wxMaUserInfo.get("openId"));
                memberWeixinMaMapper.insert(memberWeixinMa);
            } catch (Exception e) {
                dataSourceTransactionManager.rollback(transactionStatus);
                ExceptionUtil.throwSqlException("sql错误");
            }
        }
        HashMap<String, String> claims = new HashMap<>(1);
        claims.put("id", IntUtil.toString(member.getId()));
        JwtUtil jwtUtil = new JwtUtil();
        jwtUtil.setISSUER("api-portal");
        token = jwtUtil.create(claims);

        this.logger.info("token=====> " + token);

        responseVo.setStatus(true);
        responseVo.setCode(BizEnum.OK.getCode());
        responseVo.setMessage("用户登录成功");
        responseVo.setData(token);
        return responseVo;
    }

    public ResponseVo<PortalMember> info() {
        ResponseVo<PortalMember> responseVo = new ResponseVo<>();
        JSONObject loginUser = RequestUtil.getLoginUser();
        responseVo.setStatus(true);
        responseVo.setData(memberDao.getWeixinMaMember(
                new QueryWrapper<PortalMember>().lambda().eq(PortalMember::getId, loginUser.get("id"))));
        return responseVo;
    }
}
