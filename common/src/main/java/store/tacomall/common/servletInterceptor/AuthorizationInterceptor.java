/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2020-12-07 16:12:56
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/common/src/main/java/store/tacomall/common/servletInterceptor/AuthorizationInterceptor.java
 * @Just do what I think it is right
 */
package store.tacomall.common.servletInterceptor;

import java.util.Map;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;

import store.tacomall.common.annotation.LoginUser;
import store.tacomall.common.feign.AuthFeignClient;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.common.util.JwtUtil;
import store.tacomall.common.util.StringUtil;

public class AuthorizationInterceptor implements HandlerInterceptor {
    private static final String APP_TOKEN_KEY = "app-access-token";
    private static final String USER_TOKEN_KEY = "user-access-token";
    public static final String USER_DECODED_KEY = "LOGINED_USER";

    @Autowired
    private AuthFeignClient authFeignClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        String appAccessToken = request.getHeader(APP_TOKEN_KEY);
        String userAccessToken = request.getHeader(USER_TOKEN_KEY);
        LoginUser loginUser = method.getAnnotation(LoginUser.class);
        if (StringUtil.isBlank(appAccessToken)) {
            ExceptionUtil.throwUnauthorizedException("AppAccessToken is require");
        }

        if (method.isAnnotationPresent(LoginUser.class) && loginUser.required()
                && StringUtil.isBlank(userAccessToken)) {
            ExceptionUtil.throwUnauthorizedException("UserAccessToken is require");
        }

        if (!authFeignClient.verifyAppAccessToken(appAccessToken).getStatus()) {
            ExceptionUtil.throwUnauthorizedException("AppAccessToken verify fail");
        }

        if (!StringUtil.isBlank(userAccessToken)) {
            JwtUtil jwtUtil = new JwtUtil();
            jwtUtil.setISSUER("api-portal");
            Map<String, String> res = jwtUtil.verify(userAccessToken);
            request.setAttribute(USER_DECODED_KEY, res);
        }

        return true;
    }
}
