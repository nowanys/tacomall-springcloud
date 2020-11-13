/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-16 17:36:20
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\tacomall-common\src\main\java\cn\codingtalk\tacomallcommon\exceptionInterceptor\exception\BizException.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallcommon.exceptionInterceptor.exception;

public class BizException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public BizException(String message) {
        super(message);
    }
}
