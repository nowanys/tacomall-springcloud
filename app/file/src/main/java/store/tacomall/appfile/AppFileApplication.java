/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-11-13 09:10:45
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/app/file/src/main/java/store/tacomall/appfile/AppFileApplication.java
 * @Just do what I think it is right
 */
package store.tacomall.appfile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("store.tacomall.appfile.mapper")
public class AppFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppFileApplication.class, args);
    }
}
