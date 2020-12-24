package ${PACKAGE};

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

@Data
public class ${CLASS} {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    ${FIELDS}
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private int isDelete;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;
}