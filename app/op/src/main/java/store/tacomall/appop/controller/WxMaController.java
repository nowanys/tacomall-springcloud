package store.tacomall.appop.controller;

import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.google.common.io.Files;
import com.google.common.collect.Lists;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;

import store.tacomall.common.enumeration.BizEnum;
import store.tacomall.common.dto.AppDto;
import store.tacomall.common.util.JsonUtil;
import store.tacomall.appop.config.WxMaConfig;

@RestController
@RequestMapping("/WxMa/{appid}")
public class WxMaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private void route(WxMaMessage message, String appid) {
        try {
            WxMaConfig.getRouter(appid).route(message);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }
    }

    @GetMapping(name = "/verify/", produces = "text/plain;charset=utf-8")
    public String authGet(@PathVariable String appid,
            @RequestParam(name = "signature", required = false) String signature,
            @RequestParam(name = "timestamp", required = false) String timestamp,
            @RequestParam(name = "nonce", required = false) String nonce,
            @RequestParam(name = "echostr", required = false) String echostr) {
        this.logger.info("\n接收到来自微信服务器的认证消息：signature = [{}], timestamp = [{}], nonce = [{}], echostr = [{}]",
                signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        final WxMaService wxService = WxMaConfig.getMaService(appid);

        if (wxService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

    @PostMapping(name = "/verify/", produces = "application/xml; charset=UTF-8")
    public String post(@PathVariable String appid, @RequestBody String requestBody,
            @RequestParam(name = "msg_signature", required = false) String msgSignature,
            @RequestParam(name = "encrypt_type", required = false) String encryptType,
            @RequestParam(name = "signature", required = false) String signature,
            @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce) {
        this.logger.info(
                "\n接收微信请求：[msg_signature=[{}], encrypt_type=[{}], signature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                msgSignature, encryptType, signature, timestamp, nonce, requestBody);

        final WxMaService wxService = WxMaConfig.getMaService(appid);

        final boolean isJson = Objects.equals(wxService.getWxMaConfig().getMsgDataFormat(),
                WxMaConstants.MsgDataFormat.JSON);
        if (StringUtils.isBlank(encryptType)) {
            // 明文传输的消息
            WxMaMessage inMessage;
            if (isJson) {
                inMessage = WxMaMessage.fromJson(requestBody);
            } else {// xml
                inMessage = WxMaMessage.fromXml(requestBody);
            }

            this.route(inMessage, appid);
            return "success";
        }

        if ("aes".equals(encryptType)) {
            // 是aes加密的消息
            WxMaMessage inMessage;
            if (isJson) {
                inMessage = WxMaMessage.fromEncryptedJson(requestBody, wxService.getWxMaConfig());
            } else {// xml
                inMessage = WxMaMessage.fromEncryptedXml(requestBody, wxService.getWxMaConfig(), timestamp, nonce,
                        msgSignature);
            }

            this.route(inMessage, appid);
            return "success";
        }

        throw new RuntimeException("不可识别的加密类型：" + encryptType);
    }

    @GetMapping("/user/login")
    public AppDto<Map<String, String>> login(@PathVariable String appid, @RequestParam(value = "code") String code) {
        AppDto<Map<String, String>> appDto = new AppDto<>();
        if (StringUtils.isBlank(code)) {
            appDto.setMessage("empty jscode");
            return appDto;
        }
        final WxMaService wxService = WxMaConfig.getMaService(appid);
        try {
            Map<String, String> map = new HashMap<>();
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            map.put("sessionKey", session.getSessionKey());
            map.put("openId", session.getOpenid());
            appDto.setStatus(true);
            appDto.setCode(BizEnum.OK.getCode());
            appDto.setData(map);
            return appDto;
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            appDto.setMessage(e.toString());
            return appDto;
        }
    }

    @GetMapping("/user/info")
    public AppDto<Map<String, String>> info(@PathVariable String appid, String sessionKey, String signature,
            String rawData, String encryptedData, String iv) {
        AppDto<Map<String, String>> appDto = new AppDto<>();
        final WxMaService wxService = WxMaConfig.getMaService(appid);
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            appDto.setMessage("user check failed");
            return appDto;
        }
        Map<String, String> map = new HashMap<>();
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        map.put("unionId", userInfo.getUnionId());
        map.put("openId", userInfo.getOpenId());
        map.put("nickname", userInfo.getNickName());
        map.put("avatar", userInfo.getAvatarUrl());
        appDto.setStatus(true);
        appDto.setCode(BizEnum.OK.getCode());
        appDto.setMessage(BizEnum.OK.getMessage());
        appDto.setData(map);
        return appDto;
    }

    @GetMapping("/user/phone")
    public String phone(@PathVariable String appid, String sessionKey, String signature, String rawData,
            String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfig.getMaService(appid);

        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return JsonUtil.toJson(phoneNoInfo);
    }

    @PostMapping("/media/upload")
    public List<String> uploadMedia(@PathVariable String appid, HttpServletRequest request) throws WxErrorException {
        final WxMaService wxService = WxMaConfig.getMaService(appid);

        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        if (!resolver.isMultipart(request)) {
            return Lists.newArrayList();
        }

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        Iterator<String> it = multiRequest.getFileNames();
        List<String> result = Lists.newArrayList();
        while (it.hasNext()) {
            try {
                MultipartFile file = multiRequest.getFile(it.next());
                File newFile = new File(Files.createTempDir(), file.getOriginalFilename());
                this.logger.info("filePath is ：" + newFile.toString());
                file.transferTo(newFile);
                WxMediaUploadResult uploadResult = wxService.getMediaService()
                        .uploadMedia(WxMaConstants.KefuMsgType.IMAGE, newFile);
                this.logger.info("media_id ： " + uploadResult.getMediaId());
                result.add(uploadResult.getMediaId());
            } catch (IOException e) {
                this.logger.error(e.getMessage(), e);
            }
        }

        return result;
    }

    @GetMapping("/media/download/{mediaId}")
    public File getMedia(@PathVariable String appid, @PathVariable String mediaId) throws WxErrorException {
        final WxMaService wxService = WxMaConfig.getMaService(appid);

        return wxService.getMediaService().getMedia(mediaId);
    }
}
