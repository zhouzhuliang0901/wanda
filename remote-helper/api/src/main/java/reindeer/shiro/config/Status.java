package reindeer.shiro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "status")
public class Status {
    /**
     * 权限列表
     */
    private Map<Integer, String> statusMap = new HashMap<>();

    public Map<Integer, String> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<Integer, String> statusMap) {
        this.statusMap = statusMap;
    }

    /**
     * 返回权限信息
     *
     * @param code 权限码
     * @return 有返回对应权限，没有返回封禁ban
     */
    public String getStatus(Integer code) {
        return statusMap.getOrDefault(code, statusMap.get(-1));
    }


}
