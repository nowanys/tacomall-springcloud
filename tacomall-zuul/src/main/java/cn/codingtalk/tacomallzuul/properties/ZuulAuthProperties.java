package cn.codingtalk.tacomallzuul.properties;

import java.util.List;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@Configuration
@ConfigurationProperties(prefix = "zuul.auth")
public class ZuulAuthProperties {

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