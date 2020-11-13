/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-16 17:14:35
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\tacomall-service\tacomall-service-biz\tacomall-service-biz-member\src\main\java\cn\codingtalk\tacomallservicebizmember\ServiceBizMemberApplication.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallservicebizmember;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.codingtalk.tacomallservicebizmember.mapper")
@SpringBootApplication(scanBasePackages = {
    "cn.codingtalk.tacomallcommon",
    "cn.codingtalk.tacomallservicebizmember"
})
public class ServiceBizMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceBizMemberApplication.class, args);
    }
}
