package ${PACKAGE};

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import store.tacomall.db.entity.${ENTITY_DOMAIN}.${ENTITY_CLASS};

@Repository
public interface ${CLASS} extends BaseMapper<${ENTITY_CLASS}> {

}