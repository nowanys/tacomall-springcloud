/***
 * @Author: 码上talk|RC
 * @Date: 2020-11-13 09:10:06
 * @LastEditTime: 2020-11-13 09:10:29
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/db/src/main/java/store/tacomall/db/entity/oss/OssObject.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.db.entity.oss;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

@Data
public class OssObject {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private int isDelete;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

}