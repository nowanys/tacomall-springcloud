/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-16 17:36:01
 * @LastEditTime: 2020-06-16 17:36:36
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\tacomall-common\src\main\java\cn\codingtalk\tacomallcommon\exceptionInterceptor\exception\SqlException.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallcommon.exceptionInterceptor.exception;

public class SqlException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public SqlException(String message) {
        super(message);
    }
}

