public class OpSeckillApplication {
    
}
/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-08 11:11:08
 * @LastEditTime: 2020-12-08 11:13:08
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/app/seckill/src/main/java/store/tacomall/appseckill/AppSeckillApplication.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.appseckill;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "store.tacomall.common")
@EnableDistributedTransaction
@MapperScan("store.tacomall.common.db")
@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.appmember" })
public class AppSeckillApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppSeckillApplication.class, args);
    }
}