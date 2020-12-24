/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-07 17:00:59
 * @LastEditTime: 2020-12-08 09:40:46
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/scheduler/executor/src/main/java/store/tacomall/schedulerexecutor/handler/SeckillStock2RedisJobHandler.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.schedulerexecutor.handler;

import com.baomidou.jobs.api.JobsResponse;
import com.baomidou.jobs.handler.IJobsHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.tacomall.common.feign.MqRabbitmqFeignClient;

/**
 * 处理器 SeckillSku2Redis
 *
 * @author jobob
 * @since 2019-07-16
 */
@Component
public class SeckillStock2RedisJobHandler implements IJobsHandler {

    @Autowired
    private MqRabbitmqFeignClient rabbitmqFeignClient;

    @Override
    public JobsResponse execute(String tenantId, String param) {
        rabbitmqFeignClient.schedulerSeckillStock2Redis();
        return JobsResponse.ok();
    }
}
