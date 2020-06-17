/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-11 20:54:11
 * @LastEditTime: 2020-06-16 22:15:15
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/tacomall-service/tacomall-service-biz/tacomall-service-biz-member/src/main/java/cn/codingtalk/tacomallservicebizmember/entity/MemberWeixinMa.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallservicebizmember.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

@Data
public class MemberWeixinMa {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private int memberId;

    private String openId;

    private int isDelete;

    private Date createtime;

    private Date updateTime;

    private Date deleteTime;
}
