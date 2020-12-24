/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-11-13 09:09:23
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/app/file/src/main/java/store/tacomall/servicefile/service/OssService.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.servicefile.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import store.tacomall.common.db.entity.oss.OssObject;

public interface OssService {

    Map<String, Object> authorize(String dir);

    OssObject callback(HttpServletRequest request);
}
