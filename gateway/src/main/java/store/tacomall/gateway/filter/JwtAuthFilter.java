/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-11-13 10:39:23
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/gateway/src/main/java/store/tacomall/gateway/filter/JwtAuthFilter.java
 * @Just do what I think it is right
 */
package store.tacomall.gateway.filter;

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

import store.tacomall.common.vo.ResponseVo;
import store.tacomall.common.util.JwtUtil;
import store.tacomall.common.util.ResponseUtil;
import store.tacomall.gateway.properties.GatewayAuthProperties;

@Component
public class JwtAuthFilter extends ZuulFilter {

    @Autowired
    GatewayAuthProperties gatewayAuthProperties;

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
        for (GatewayAuthProperties.Config auth : gatewayAuthProperties.getConfigs()) {
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
        String token = request.getHeader(gatewayAuthProperties.getTokenKey());
        try {
            Map<String, String> claims = jwtUtil.verify(token);
            ctx.setSendZuulResponse(true);
            ctx.addZuulRequestHeader(gatewayAuthProperties.getUserKey(), claims.get("id"));
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
