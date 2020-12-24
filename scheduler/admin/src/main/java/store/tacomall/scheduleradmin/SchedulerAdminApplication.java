/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-11-11 14:20:14
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/scheduler/admin/src/main/java/store/tacomall/scheduleradmin/SchedulerAdminApplication.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.scheduleradmin;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Job Admin
 *
 * @author jobob
 * @since 2019-05-31
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableDiscoveryClient
public class SchedulerAdminApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SchedulerAdminApplication.class);
		application.setBannerMode(Banner.Mode.CONSOLE);
		application.run(args);
	}
}