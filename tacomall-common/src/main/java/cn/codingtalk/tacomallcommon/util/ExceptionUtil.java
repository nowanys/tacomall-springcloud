/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-16 21:28:41
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/tacomall-common/src/main/java/cn/codingtalk/tacomallcommon/util/ExceptionUtil.java
 * @Just do what I think it is right
 */

package cn.codingtalk.tacomallcommon.util;

import cn.codingtalk.tacomallcommon.exceptionInterceptor.exception.*;

public class ExceptionUtil {
    public static void throwBizException(String message) throws BizException {
        throw new BizException(message);
    }

    public static void throwSqlException(String message) throws SqlException {
        throw new SqlException(message);
    }
}
