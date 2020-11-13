/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-16 21:08:07
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\tacomall-zuul\src\main\java\cn\codingtalk\tacomallzuul\filter\JwtAuthFilter.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallzuul.filter;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.util.AntPathMatcher;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import cn.codingtalk.tacomallcommon.vo.ResponseVo;
import cn.codingtalk.tacomallcommon.util.JwtUtil;
import cn.codingtalk.tacomallcommon.util.ResponseUtil;
import cn.codingtalk.tacomallzuul.properties.ZuulAuthProperties;

@Component
public class JwtAuthFilter extends ZuulFilter {

    @Autowired
    ZuulAuthProperties zuulAuthProperties;

    private JwtUtil jwtUtil = new JwtUtil();

    private static AntPathMatcher matcher = new AntPathMatcher();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        for (ZuulAuthProperties.Config auth : zuulAuthProperties.getConfigs()) {
            for (String ignore : auth.getIgnore()) {
                if (matcher.match(ignore, ctx.getRequest().getRequestURI())) {
                    return false;
                }
            }
            for (String require : auth.getRequire()) {
                if (matcher.match(require, ctx.getRequest().getRequestURI())) {
                    this.jwtUtil.setISSUER(auth.getISSUER());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        String token = request.getHeader(zuulAuthProperties.getTokenKey());
        try {
            Map<String, String> claims = jwtUtil.verify(token);
            ctx.setSendZuulResponse(true);
            ctx.addZuulRequestHeader(zuulAuthProperties.getUserKey(), claims.get("id"));
        } catch (Exception e) {
            logger.info("token : {} 验证失败", token);
            ResponseVo<String> vo = new ResponseVo<>();
            vo.setMessage("token验证失败");
            ctx.setSendZuulResponse(false);
            ResponseUtil.returnJson(response, vo);
        }
        return null;
    }
}
