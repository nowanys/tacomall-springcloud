/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-12-04 14:18:30
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/common/src/main/java/store/tacomall/common/util/JwtUtil.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.util;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import store.tacomall.common.exceptionInterceptor.exception.BizException;

public class JwtUtil {

    private String SECRET = "tacomall";
    private String ISSUER = "";

    public JwtUtil setISSUER(String ISSUER) {
        this.ISSUER = ISSUER;
        return this;
    }

    public String create(Map<String, String> claims) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.SECRET);
            JWTCreator.Builder builder = JWT.create().withIssuer(this.ISSUER)
                    .withExpiresAt(DateUtil.offsetDay(new Date(), 2));
            claims.forEach(builder::withClaim);
            return builder.sign(algorithm);
        } catch (IllegalArgumentException | JWTCreationException e) {
            throw new BizException("Genarate token fail");
        }
    }

    public Map<String, String> verify(String token) throws Exception {
        Algorithm algorithm;
        Map<String, Claim> map;
        try {
            algorithm = Algorithm.HMAC256(this.SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(this.ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            map = jwt.getClaims();
        } catch (JWTVerificationException e) {
            throw new BizException("Token verify failed");
        }
        Map<String, String> resultMap = new HashMap<>(map.size());
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }
}
