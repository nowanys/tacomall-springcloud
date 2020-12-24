/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-07 17:02:56
 * @LastEditTime: 2020-12-08 11:03:18
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/mq/rabbitmq/src/main/java/store/tacomall/mqrabbitmq/controller/SeckillController.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.mqrabbitmq.controller;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/buy")
    public Map<String, Object> buy(@RequestBody JSONObject json) {
        rabbitTemplate.convertAndSend("exchange.seckill.to_order", "routing.seckill.to_order", json);
        return new HashMap<>();
    }

}