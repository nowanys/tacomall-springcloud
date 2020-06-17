package cn.codingtalk.tacomallservicebizfile.entity.oss;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

@Data
public class OssObject {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String filename;

    private String endpoint;

    private String bucket;

    private String url;
}

