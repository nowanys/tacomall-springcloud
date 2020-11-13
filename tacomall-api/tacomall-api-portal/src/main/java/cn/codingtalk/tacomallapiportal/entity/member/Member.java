/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2020-06-15 14:48:09
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\tacomall-api\tacomall-api-portal\src\main\java\cn\codingtalk\tacomallapiportal\entity\member\Member.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallapiportal.entity.member;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

@Data
public class Member {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String username;

    private String passwd;

    private String nickname;

    private String avatar;

    private Date birthday;

    private String signature;

    private int integral;

    private int growth;

    private int is_delete;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    @TableField(exist = false)
    private MemberWeixin userWeixin;
}
