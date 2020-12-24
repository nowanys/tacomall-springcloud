/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-11-13 10:33:41
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/scheduler/admin/src/main/java/store/tacomall/scheduleradmin/service/JobsPageHelper.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.scheduleradmin.service;

import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletRequest;

public class JobsPageHelper {

    /**
     * 分页信息
     */
    public static Page getPage(HttpServletRequest request) {
        long current = 1;
        long size = 10;
        String page = request.getParameter("page");
        if (StrUtil.isNotEmpty(page)) {
            Long p = Long.valueOf(page);
            if (p > 0) {
                current = p;
            }
        }
        String rows = request.getParameter("rows");
        if (StrUtil.isNotEmpty(page)) {
            Long l = Long.valueOf(rows);
            if (l > 0) {
                size = l;
            }
        }
        return new Page(current, size);
    }
}
