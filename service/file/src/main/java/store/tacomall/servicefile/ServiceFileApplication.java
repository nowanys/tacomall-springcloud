/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-12-04 11:59:18
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/app/file/src/main/java/store/tacomall/servicefile/servicefileApplication.java
 * @Just do what I think it is right
 */
package store.tacomall.servicefile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableFeignClients(basePackages = "store.tacomall.common")
@SpringBootApplication
@MapperScan("store.tacomall.common.db.mapper")
@EnableDiscoveryClient
public class ServiceFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceFileApplication.class, args);
    }
}
