/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-11-13 10:38:44
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/gateway/src/main/java/store/tacomall/gateway/properties/GatewayAuthProperties.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.gateway.properties;

import java.util.List;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Configuration
@ConfigurationProperties(prefix = "zuul.auth")
public class GatewayAuthProperties {

    private String tokenKey;

    private String userKey;

    private List<Config> configs;

    @Data
    public static class Config {

        private String ISSUER;

        private List<String> require;

        private List<String> ignore;

    }

}