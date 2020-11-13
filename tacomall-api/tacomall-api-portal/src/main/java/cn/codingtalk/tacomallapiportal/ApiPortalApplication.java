/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-16 17:13:51
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\tacomall-api\tacomall-api-portal\src\main\java\cn\codingtalk\tacomallapiportal\ApiPortalApplication.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallapiportal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFeignClients
@MapperScan("cn.codingtalk.tacomallapiportal.mapper")
@SpringBootApplication(scanBasePackages = {
        "cn.codingtalk.tacomallcommon",
        "cn.codingtalk.tacomallapiportal"
})
public class ApiPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPortalApplication.class, args);
    }
}
