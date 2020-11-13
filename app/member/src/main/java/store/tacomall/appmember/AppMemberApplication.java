/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-11-13 17:49:12
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

@EnableDistributedTransaction
@MapperScan("store.tacomall.db")
@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.appmember" })
public class AppMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppMemberApplication.class, args);
    }
}
