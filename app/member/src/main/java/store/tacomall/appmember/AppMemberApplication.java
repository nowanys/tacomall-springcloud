/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-12-04 11:58:26
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/app/member/src/main/java/store/tacomall/appmember/AppMemberApplication.java
 * @Just do what I think it is right
 */
package store.tacomall.appmember;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "store.tacomall.common")
@EnableDistributedTransaction
@MapperScan("store.tacomall.common.db")
@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.appmember" })
public class AppMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppMemberApplication.class, args);
    }
}
