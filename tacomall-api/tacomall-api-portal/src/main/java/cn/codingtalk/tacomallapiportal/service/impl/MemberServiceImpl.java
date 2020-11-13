/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-17 15:18:35
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/tacomall-api/tacomall-api-portal/src/main/java/cn/codingtalk/tacomallapiportal/service/impl/MemberServiceImpl.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallapiportal.service.impl;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.codingtalk.tacomallcommon.vo.ResponseVo;
import cn.codingtalk.tacomallcommon.dto.ServiceDto;
import cn.codingtalk.tacomallcommon.util.IntUtil;
import cn.codingtalk.tacomallcommon.util.JwtUtil;
import cn.codingtalk.tacomallcommon.util.ObjectUtil;
import cn.codingtalk.tacomallcommon.util.ExceptionUtil;
import cn.codingtalk.tacomallcommon.enumeration.BizEnum;
import cn.codingtalk.tacomallapiportal.entity.member.Member;
import cn.codingtalk.tacomallapiportal.service.MemberService;
import cn.codingtalk.tacomallapiportal.feign.MemberFeignClient;
import cn.codingtalk.tacomallapiportal.feign.OpFeignClient;
import cn.codingtalk.tacomallapiportal.mapper.member.MemberMapper;
import cn.codingtalk.tacomallapiportal.config.WxMiniappConfig;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberFeignClient memberFeignClient;

    @Autowired
    private OpFeignClient opFeignClient;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public ResponseVo<String> wxMaLogin(String iv, String code, String appid, String rawData, String signature,
            String encryptedData) throws Exception{
        ResponseVo<String> responseVo = new ResponseVo<>();
        responseVo.setStatus(false);
        responseVo.setCode(BizEnum.FALSE.getCode());
        responseVo.setMessage(BizEnum.FALSE.getMessage());
        ServiceDto<HashMap<String, Object>> loginDto = this.opFeignClient.wxMaUserlogin(WxMiniappConfig.appid, code);
        if (loginDto.getStatus()) {
            ServiceDto<HashMap<String, Object>> userInfoDto = this.opFeignClient.wxMaUserInfo(WxMiniappConfig.appid, loginDto.getData().get("sessionKey").toString(), signature,
            rawData, encryptedData, iv);
            if (userInfoDto.getStatus()) {
                HashMap<String, Object> getMemberMap = new HashMap<>();
                getMemberMap.put("open_id", userInfoDto.getData().get("openId"));
                this.logger.info(userInfoDto.getData().toString());
                ServiceDto<Member> getMemberDto = this.memberFeignClient.getMember(getMemberMap);
                if (getMemberDto.getStatus()) {
                    String token = "";
                    Member member = new Member();
                    member = getMemberDto.getData();
                    if (ObjectUtil.isNull(member)) {
                        HashMap<String, Object> addMemberMap = new HashMap<>();
                        addMemberMap.put("userInfo", userInfoDto.getData());
                        ServiceDto<Member> addMemberDto = this.memberFeignClient.addMember(addMemberMap);
                        if (addMemberDto.getStatus()) {
                            member = addMemberDto.getData();
                        }
                    }
                    try {
                        HashMap<String, String> claims = new HashMap<>(1);
                        claims.put("id", IntUtil.toString(member.getId()));
                        JwtUtil jwtUtil = new JwtUtil();
                        jwtUtil.setISSUER("api-portal");
                        token = jwtUtil.create(claims);
                    } catch (Exception e) {
                        ExceptionUtil.throwBizException("token生成失败");
                    }
                    responseVo.setStatus(true);
                    responseVo.setCode(BizEnum.OK.getCode());
                    responseVo.setMessage("用户登录成功");
                    responseVo.setData(token);
                }
            }
        }
        return responseVo;
    }

    public ResponseVo<Member> info() {
        ResponseVo<Member> responseVo = new ResponseVo<>();
        return responseVo;
    }
}
