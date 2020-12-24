/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-08 09:01:26
 * @LastEditTime: 2020-12-08 09:09:45
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/mq/rabbitmq/src/main/java/store/tacomall/mqrabbitmq/config/Seckill2OrderDirectConfig.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.mqrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Seckill2OrderDirectConfig {

    @Bean
    public Queue Seckill2OrderDirectQueue() {
        return new Queue("queue.seckill.to_order", true);
    }

    @Bean
    DirectExchange Seckill2OrderDirectExchange() {
        return new DirectExchange("exchange.seckill.to_order");
    }

    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(Seckill2OrderDirectQueue()).to(Seckill2OrderDirectExchange())
                .with("routing.seckill.to_order");
    }
}
