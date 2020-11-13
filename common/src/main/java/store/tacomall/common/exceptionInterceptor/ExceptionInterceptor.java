/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-16 17:37:56
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\common\src\main\java\cn\codingtalk\tacomallcommon\exceptionInterceptor\ExceptionInterceptor.java
 * @Just do what I think it is right
 */
package store.tacomall.common.exceptionInterceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import store.tacomall.common.vo.ResponseVo;
import store.tacomall.common.enumeration.ExceptionEnum;
import store.tacomall.common.exceptionInterceptor.exception.*;

@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseVo<String> bizErrorHandler(HttpServletRequest req, BizException e) throws Exception {
        ResponseVo<String> bizError = new ResponseVo<>();
        bizError.setStatus(false);
        bizError.setCode(ExceptionEnum.SERVER_ERROR.getCode());
        bizError.setMessage(e.getMessage());
        return bizError;
    }

    @ExceptionHandler(value = SqlException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseVo<String> sqlErrorHandler(HttpServletRequest req, SqlException e) throws Exception {
        ResponseVo<String> sqlError = new ResponseVo<>();
        sqlError.setStatus(false);
        sqlError.setCode(ExceptionEnum.SERVER_ERROR.getCode());
        sqlError.setMessage(e.getMessage());
        return sqlError;
    }
}
