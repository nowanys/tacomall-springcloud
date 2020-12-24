/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-07 17:02:22
 * @LastEditTime: 2020-12-08 11:03:06
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/mq/rabbitmq/src/main/java/store/tacomall/mqrabbitmq/controller/SchedulerController.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.mqrabbitmq.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/seckillStock2Redis")
    public Map<String, Object> seckillStock2Redis() {
        rabbitTemplate.convertAndSend("exchange.scheduler.seckill_stock_2_redis",
                "routing.scheduler.seckill_stock_2_redis");
        return new HashMap<>();
    }

}
