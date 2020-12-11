/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-08 09:32:41
 * @LastEditTime: 2020-12-08 09:37:29
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/common/src/main/java/store/tacomall/common/feign/RabbitmqFeignClient.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import store.tacomall.common.dto.MqDto;
import store.tacomall.common.config.FeignConfig;

@Component
@FeignClient(name = "mq-rabbitmq", configuration = FeignConfig.class)
public interface RabbitmqFeignClient {

    @RequestMapping(value = "/scheduler/seckillStock2Redis", method = RequestMethod.POST)
    MqDto<String> schedulerSeckillStock2Redis();

    @RequestMapping(value = "/seckill/buy", method = RequestMethod.POST)
    MqDto<String> seckillBuy(@RequestBody Map<String, Object> map);
}
