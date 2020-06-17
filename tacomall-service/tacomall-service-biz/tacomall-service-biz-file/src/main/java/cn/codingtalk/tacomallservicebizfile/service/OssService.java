package cn.codingtalk.tacomallservicebizfile.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import cn.codingtalk.tacomallservicebizfile.entity.oss.OssObject;

public interface OssService {

    Map<String, Object> authorize(String dir);

    OssObject callback(HttpServletRequest request);
}
