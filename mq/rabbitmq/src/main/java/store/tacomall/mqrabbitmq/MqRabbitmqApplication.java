/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-07 16:59:14
 * @LastEditTime: 2020-12-08 11:06:29
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/mq/rabbitmq/src/main/java/store/tacomall/mqrabbitmq/MqRabbitmqApplication.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.mqrabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan({ "store.tacomall.common.db.mapper", "store.tacomall.apiportal.mapper" })
@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.mqmqrabbitmq" })
@EnableDiscoveryClient
public class MqRabbitmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqRabbitmqApplication.class, args);
    }
}
