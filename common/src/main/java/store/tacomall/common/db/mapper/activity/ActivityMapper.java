/***
 * @Author: 码上talk|RC
 * @Date: 2020-07-15 10:03:33
 * @LastEditTime: 2020-11-10 16:34:57
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/common/src/main/java/store/tacomall/common/mapper/activity/ActivityMapper.java
 * @Just do what I think it is right
 */
package store.tacomall.common.db.mapper.activity;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import store.tacomall.common.db.entity.activity.Activity;

@Repository
public interface ActivityMapper extends BaseMapper<Activity> {

    @Select("SELECT * FROM activity")
    IPage<Activity> getActivityPage(@Param("page") Page<?> page);

}