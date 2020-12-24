/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2020-11-13 17:32:20
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/db/src/main/java/store/tacomall/db/entity/member/Member.java
 * @Just do what I think it is right
 */
package store.tacomall.common.db.entity.member;

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

    private int isDelete;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    @TableField(exist = false)
    private MemberStatisticsInfo memberStatisticsInfo;
}
