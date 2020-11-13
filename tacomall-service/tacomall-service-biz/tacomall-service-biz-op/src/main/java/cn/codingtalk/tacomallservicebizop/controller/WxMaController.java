package cn.codingtalk.tacomallservicebizop.controller;

import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
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

import cn.codingtalk.tacomallcommon.enumeration.BizEnum;
import cn.codingtalk.tacomallcommon.dto.ServiceDto;
import cn.codingtalk.tacomallcommon.util.JsonUtil;
import cn.codingtalk.tacomallservicebizop.config.WxMaConfig;

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
    public ServiceDto<HashMap<String, Object>> login(@PathVariable String appid, String code) {
        ServiceDto<HashMap<String, Object>> serviceDto = new ServiceDto<>();
        serviceDto.setStatus(false);
        serviceDto.setCode(BizEnum.FALSE.getCode());
        serviceDto.setMessage(BizEnum.FALSE.getMessage());
        if (StringUtils.isBlank(code)) {
            serviceDto.setMessage("empty jscode");
            return serviceDto;
        }

        final WxMaService wxService = WxMaConfig.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("sessionKey", session.getSessionKey());
            hashMap.put("openId", session.getOpenid());
            serviceDto.setStatus(true);
            serviceDto.setCode(BizEnum.OK.getCode());
            serviceDto.setMessage(BizEnum.OK.getMessage());
            serviceDto.setData(hashMap);
            return serviceDto;
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            serviceDto.setMessage(e.toString());
            return serviceDto;
        }
    }

    @GetMapping("/user/info")
    public ServiceDto<HashMap<String, Object>> info(@PathVariable String appid, String sessionKey, String signature,
            String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfig.getMaService(appid);
        ServiceDto<HashMap<String, Object>> serviceDto = new ServiceDto<>();
        serviceDto.setStatus(false);
        serviceDto.setCode(BizEnum.FALSE.getCode());
        serviceDto.setMessage(BizEnum.FALSE.getMessage());

        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            serviceDto.setMessage("user check failed");
            return serviceDto;
        }

        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("unionId", userInfo.getUnionId());
        hashMap.put("openId", userInfo.getOpenId());
        hashMap.put("nickname", userInfo.getNickName());
        hashMap.put("avatar", userInfo.getAvatarUrl());
        serviceDto.setStatus(true);
        serviceDto.setCode(BizEnum.OK.getCode());
        serviceDto.setMessage(BizEnum.OK.getMessage());
        serviceDto.setData(hashMap);
        return serviceDto;
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
