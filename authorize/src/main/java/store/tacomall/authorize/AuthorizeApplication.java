/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-12-04 10:28:47
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/auth/src/main/java/store/tacomall/auth/AuthApplication.java
 * @Just do what I think it is right
 */
package store.tacomall.authorize;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("store.tacomall.db.mapper")
@EnableFeignClients(basePackages = "store.tacomall.common")
@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.authorize" })
@EnableDiscoveryClient
public class AuthorizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizeApplication.class, args);
    }
}
