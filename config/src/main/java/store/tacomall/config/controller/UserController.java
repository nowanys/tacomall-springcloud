package store.tacomall.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @RequestMapping("/getUseLocalCache")
    public boolean get() {
        return useLocalCache;
    }
}
