/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-15 14:59:42
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\tacomall-service\tacomall-service-biz\tacomall-service-biz-file\src\main\java\cn\codingtalk\tacomallservicebizfile\ServiceBizFile.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallservicebizfile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.bomeiyi.tacomallservicebizfile.mapper")
public class ServiceBizFile {
    public static void main(String[] args) {
        SpringApplication.run(ServiceBizFile.class, args);
    }
}
