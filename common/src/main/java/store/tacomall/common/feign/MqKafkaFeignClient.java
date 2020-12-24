package store.tacomall.common.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import store.tacomall.common.dto.MqDto;
import store.tacomall.common.config.FeignConfig;

@Component
@FeignClient(name = "mq-kafka", configuration = FeignConfig.class)
public interface MqKafkaFeignClient {

    @RequestMapping(value = "/log/send", method = RequestMethod.POST)
    MqDto<String> logSend(@RequestBody Map<String, Object> map);
}
