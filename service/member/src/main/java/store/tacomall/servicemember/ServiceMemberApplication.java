/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-12-04 11:58:26
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/app/member/src/main/java/store/tacomall/servicemember/servicememberApplication.java
 * @Just do what I think it is right
 */
package store.tacomall.servicemember;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "store.tacomall.common")
@MapperScan("store.tacomall.common.db")
@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.servicemember" })
@EnableDiscoveryClient
public class ServiceMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMemberApplication.class, args);
    }
}
