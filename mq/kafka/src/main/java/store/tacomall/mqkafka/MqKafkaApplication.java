/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-07 17:57:29
 * @LastEditTime: 2020-12-08 11:06:46
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/mq/kafka/src/main/java/store/tacomall/mqKafka/MqKafkaApplication.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.mqkafka;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({ "store.tacomall.common.db.mapper", "store.tacomall.apiportal.mapper" })
@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.mqkafka" })
public class MqKafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqKafkaApplication.class, args);
    }
}