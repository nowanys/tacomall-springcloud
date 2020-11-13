/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-16 17:08:35
 * @LastEditTime: 2020-06-16 18:00:07
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\api\api-portal\src\main\java\cn\codingtalk\tacomallapiportal\properties\WxMiniappProperties.java
 * @Just do what I think it is right
 */
package store.tacomall.apiportal.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMiniappProperties {

    private String appid;

}
