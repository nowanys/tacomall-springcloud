/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-08 09:36:56
 * @LastEditTime: 2020-12-08 09:37:12
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/common/src/main/java/store/tacomall/common/dto/MqDto.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.dto;

import lombok.Data;

import store.tacomall.common.enumeration.BizEnum;

@Data
public class MqDto<T> {

    private Boolean status = true;

    private Integer code = BizEnum.FALSE.getCode();

    private String message = BizEnum.FALSE.getMessage();

    private T data;

}
