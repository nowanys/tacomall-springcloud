/***
 * @Author: 码上talk|RC
 * @Date: 2020-07-02 14:26:25
 * @LastEditTime: 2020-07-02 14:27:35
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springboot/tacomall-mapper/src/main/java/store/tacomall/mapper/admin/AdminAuthRuleViewMapper.java
 * @Just do what I think it is right
 */
package store.tacomall.db.mapper.admin;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import store.tacomall.db.entity.admin.AdminAuthRuleView;

@Repository
public interface AdminAuthRuleViewMapper extends BaseMapper<AdminAuthRuleView> {
    @Select("SELECT * FROM admin_auth_rule_view WHERE rule_id = #{id}")
    AdminAuthRuleView getAuthRuleView(int id);
}
