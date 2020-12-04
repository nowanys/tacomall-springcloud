/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-11-11 14:39:32
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/api/open/src/main/java/store/tacomall/apiopen/ApiOpenApplication.java
 * @Just do what I think it is right
 */
package store.tacomall.apiopen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFeignClients
@MapperScan("store.tacomall.common.db.mapper")
@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.apiopen" })
public class ApiOpenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiOpenApplication.class, args);
    }
}
