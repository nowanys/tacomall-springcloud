/***
 * @Author: 码上talk|RC
 * @Date: 2020-11-13 17:29:44
 * @LastEditTime: 2020-11-13 18:48:24
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/api/portal/src/main/java/store/tacomall/apiportal/pojo/MemberPojo.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiportal.pojo;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import store.tacomall.db.entity.member.Member;
import store.tacomall.db.entity.member.MemberWeixin;
import store.tacomall.db.entity.member.MemberWeixinMa;

@Data
public class MemberPojo extends Member {

    @TableField(exist = false)
    private MemberWeixin memberWeixin;

    @TableField(exist = false)
    private MemberWeixinMa memberWeixinMa;

}