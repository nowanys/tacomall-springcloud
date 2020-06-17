package cn.codingtalk.tacomallzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication(scanBasePackages = {
        "cn.codingtalk.tacomallcommon",
        "cn.codingtalk.tacomallzuul"
})
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
