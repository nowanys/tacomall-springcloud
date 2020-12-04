/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-11-11 14:32:52
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/api/admin/src/main/java/store/tacomall/apiadmin/ApiAdminApplication.java
 * @Just do what I think it is right
 */
package store.tacomall.apiadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFeignClients
@MapperScan("store.tacomall.common.db.mapper")
@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.apiadmin" })
public class ApiAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiAdminApplication.class, args);
    }
}
