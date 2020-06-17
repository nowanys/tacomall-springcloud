/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-16 17:08:10
 * @LastEditTime: 2020-06-16 17:58:05
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\tacomall-api\tacomall-api-portal\src\main\java\cn\codingtalk\tacomallapiportal\config\WxMiniappConfig.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallapiportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import cn.codingtalk.tacomallapiportal.properties.WxMiniappProperties;

@Configuration
@EnableConfigurationProperties(WxMiniappProperties.class)
public class WxMiniappConfig {

    public static String appid;

    @Autowired
    public WxMiniappConfig(WxMiniappProperties properties) {
        appid = properties.getAppid();
    }
}
