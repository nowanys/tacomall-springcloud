/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-03 17:40:53
 * @LastEditTime: 2020-12-03 17:44:18
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/gateway/src/main/java/store/tacomall/gateway/filter/AppAccessFilter.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

@Component
public class AppAccessFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        logger.info("Request app access_token ================>>>>>>");
        return null;
    }
}