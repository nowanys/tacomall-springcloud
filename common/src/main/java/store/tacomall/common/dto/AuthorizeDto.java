/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-04 10:43:35
 * @LastEditTime: 2020-12-04 10:43:54
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/common/src/main/java/store/tacomall/common/dto/AuthorizeDto.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.dto;

import lombok.Data;

import store.tacomall.common.enumeration.BizEnum;

@Data
public class AuthorizeDto<T> {

    private Boolean status = true;

    private Integer code = BizEnum.FALSE.getCode();

    private String message = BizEnum.FALSE.getMessage();

    private T data;

}
