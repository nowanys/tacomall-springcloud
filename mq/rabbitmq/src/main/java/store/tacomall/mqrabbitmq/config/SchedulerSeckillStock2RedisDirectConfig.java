/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-08 10:28:21
 * @LastEditTime: 2020-12-08 11:01:44
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/mq/rabbitmq/src/main/java/store/tacomall/mqrabbitmq/config/SchedulerSeckillStock2RedisDirectConfig.java
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
public class SchedulerSeckillStock2RedisDirectConfig {

    @Bean
    public Queue SchedulerSeckillStock2RedisDirectQueue() {
        return new Queue("queue.scheduler.seckill_stock_2_redis", true);
    }

    @Bean
    DirectExchange SchedulerSeckillStock2RedisDirectExchange() {
        return new DirectExchange("exchange.scheduler.seckill_stock_2_redis");
    }

    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(SchedulerSeckillStock2RedisDirectQueue())
                .to(SchedulerSeckillStock2RedisDirectExchange()).with("routing.scheduler.seckill_stock_2_redis");
    }
}
