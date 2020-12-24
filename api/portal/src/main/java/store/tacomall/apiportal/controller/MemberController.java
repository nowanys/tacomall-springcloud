/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-12-04 10:35:52
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/api/portal/src/main/java/store/tacomall/apiportal/controller/MemberController.java
 * @Just do what I think it is right
 */
package store.tacomall.apiportal.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import store.tacomall.common.annotation.LoginUser;
import store.tacomall.common.vo.ResponseVo;
import store.tacomall.common.annotation.UserLogger;
import store.tacomall.apiportal.entity.PortalMember;
import store.tacomall.apiportal.service.MemberService;

@Api("会员模块")
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
    @ApiImplicitParams({ @ApiImplicitParam(name = "appid", value = "微信小程序appid", required = true, paramType = "query"),
            @ApiImplicitParam(name = "json", value = "微信授权回调数据", required = true, paramType = "body") })
    @PostMapping("wxMaLogin")
    @UserLogger
    public ResponseVo<String> miniAppLogin(@RequestParam(value = "appid") String appid, @RequestBody JSONObject json)
            throws Exception {
        return memberService.wxMaLogin(appid, json);
    }

    /***
     * @description: 获取用户信息
     * @param {type}
     * @return:
     */
    @ApiOperation(value = "用户信息", notes = "用户信息接口", httpMethod = "POST")
    @ApiImplicitParams({})
    @LoginUser
    @PostMapping("info")
    @UserLogger
    public ResponseVo<PortalMember> info() throws Exception {
        return memberService.info();
    }
}
