/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-11-11 14:20:38
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/scheduler/executor/src/main/java/store/tacomall/schedulerexecutor/SchedulerExecutorApplication.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.schedulerexecutor;

import com.baomidou.jobs.starter.EnableJobs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot jobs sample
 *
 * @author jobob
 * @since 2019-06-25
 */
@EnableJobs
@SpringBootApplication
public class SchedulerExecutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerExecutorApplication.class, args);
    }
}