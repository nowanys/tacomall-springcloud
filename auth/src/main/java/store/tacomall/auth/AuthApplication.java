/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-12-04 10:28:47
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/auth/src/main/java/store/tacomall/auth/AuthApplication.java
 * @Just do what I think it is right
 */
package store.tacomall.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("store.tacomall.db.mapper")
@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.auth" })
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
