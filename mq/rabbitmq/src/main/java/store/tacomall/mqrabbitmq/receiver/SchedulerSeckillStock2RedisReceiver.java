/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-07 17:54:58
 * @LastEditTime: 2020-12-08 11:06:17
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/mq/rabbitmq/src/main/java/store/tacomall/mqrabbitmq/receiver/SchedulerSeckillStock2RedisReceiver.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.mqrabbitmq.receiver;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import store.tacomall.common.util.StringUtil;
import store.tacomall.common.db.entity.seckill.Seckill;
import store.tacomall.common.db.entity.seckill.SeckillGoodsItemApply;
import store.tacomall.common.db.mapper.seckill.SeckillMapper;
import store.tacomall.common.db.mapper.seckill.SeckillGoodsItemApplyMapper;
import store.tacomall.mqrabbitmq.utils.RedisUtil;

@Component
@RabbitListener(queues = "queue.scheduler.seckill_stock_2_redis")
public class SchedulerSeckillStock2RedisReceiver {

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SeckillGoodsItemApplyMapper seckillGoodsItemApplyMapper;

    @Autowired
    private RedisUtil redisUtil;

    @RabbitHandler
    public void process(Map<String, Object> message) {
        Date date = DateUtil.date();
        Page<Seckill> page = new Page<>(1, 2);
        String redisPreFix = "seckill:stock:";
        redisUtil.select(redisPreFix + "*").forEach((key, value) -> {
            if (date.getTime() > Long.parseLong(Arrays.asList(value.split("/")).get(0))) {
                redisUtil.delete(key);
            }
        });
        IPage<Seckill> result = seckillMapper.selectPage(page,
                new QueryWrapper<Seckill>().lambda().ge(Seckill::getEndTime, date));
        if (!ArrayUtil.isEmpty(result.getRecords())) {
            for (int i = 0; i < result.getRecords().size(); i++) {
                Seckill seckill = result.getRecords().get(i);
                seckillGoodsItemApplyMapper
                        .getSeckillGoodsItemApplys(new QueryWrapper<SeckillGoodsItemApply>().lambda()
                                .ge(SeckillGoodsItemApply::getSeckillId, seckill.getId()))
                        .forEach((SeckillGoodsItemApply seckillGoodsItemApply) -> {
                            String redisKey = redisPreFix + seckillGoodsItemApply.getGoodsItem().getId();
                            String redisValue = seckill.getEndTime().getTime() + "/"
                                    + seckillGoodsItemApply.getTotalCount();
                            if (!StringUtil.isEmpty(redisUtil.get(redisKey))) {
                                return;
                            }
                            redisUtil.set(redisKey, redisValue);
                        });
            }
        }
    }

}
