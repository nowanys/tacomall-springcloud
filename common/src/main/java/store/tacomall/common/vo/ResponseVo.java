/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-11-12 17:09:59
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/common/src/main/java/store/tacomall/common/vo/ResponseVo.java
 * @Just do what I think it is right
 */
package store.tacomall.common.vo;

import lombok.Data;

import store.tacomall.common.enumeration.BizEnum;

@Data
public class ResponseVo<T> {

    private Boolean status = true;

    private Integer code = BizEnum.FALSE.getCode();

    private String message = BizEnum.FALSE.getMessage();

    private T data;

}
