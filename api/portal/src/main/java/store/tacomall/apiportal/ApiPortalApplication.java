/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-12-04 10:35:30
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/api/portal/src/main/java/store/tacomall/apiportal/ApiPortalApplication.java
 * @Just do what I think it is right
 */
package store.tacomall.apiportal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;

@EnableFeignClients(basePackages = "store.tacomall.common")
@EnableDistributedTransaction
@MapperScan({ "store.tacomall.common.db.mapper", "store.tacomall.apiportal.mapper" })
@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.apiportal" })
public class ApiPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPortalApplication.class, args);
    }
}
