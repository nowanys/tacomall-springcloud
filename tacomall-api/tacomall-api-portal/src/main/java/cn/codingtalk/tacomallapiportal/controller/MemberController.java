/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-17 15:15:24
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/tacomall-api/tacomall-api-portal/src/main/java/cn/codingtalk/tacomallapiportal/controller/MemberController.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallapiportal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.codingtalk.tacomallcommon.vo.ResponseVo;
import cn.codingtalk.tacomallapiportal.entity.member.Member;
import cn.codingtalk.tacomallapiportal.service.MemberService;

@Api("用户模块")
@RestController
@RequestMapping("/member/")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /***
     * @description: 微信用户登录
     * @param {type}
     * @return:
     */
    @ApiOperation(value = "小程序用户注册接口", notes = "小程序用户注册接口", httpMethod = "POST")
    @ApiImplicitParams({ @ApiImplicitParam(name = "iv", value = "iv", required = true, paramType = "path"),
            @ApiImplicitParam(name = "code", value = "code", required = true, paramType = "path"),
            @ApiImplicitParam(name = "appid", value = "appid", required = true, paramType = "path"),
            @ApiImplicitParam(name = "rawData", value = "rawData", required = true, paramType = "path"),
            @ApiImplicitParam(name = "signature", value = "signature", required = true, paramType = "path"),
            @ApiImplicitParam(name = "encryptedData", value = "encryptedData", required = true, paramType = "path") })
    @PostMapping("wxMaLogin")
    public ResponseVo<String> miniAppLogin(@RequestParam(value = "iv") String iv,
            @RequestParam(value = "code") String code,
            @RequestParam(value = "appid") String appid,
            @RequestParam(value = "rawData") String rawData,
            @RequestParam(value = "signature") String signature,
            @RequestParam(value = "encryptedData") String encryptedData)  throws Exception {
        return memberService.wxMaLogin(iv, code, appid, rawData, signature, encryptedData);
    }

    /***
     * @description: 获取用户信息
     * @param {type}
     * @return:
     */
    @ApiOperation(value = "用户信息", notes = "用户信息接口", httpMethod = "POST")
    @ApiImplicitParams({})
    @PostMapping("info")
    public ResponseVo<Member> info() {
        return memberService.info();
    }
}
