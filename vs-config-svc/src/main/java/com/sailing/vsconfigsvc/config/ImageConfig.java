package com.sailing.vsconfigsvc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Title:  <br>
 * Description: <br>
 *
 * @author gaowei
 * @date 2017-12-20
 */
@Component
@ConfigurationProperties(prefix = "sys.image")
public class ImageConfig {
    private  String path;
    private  String[] type;

    public  String getPath() {
        return path;
    }

    public  void setPath(String path) {
        this.path = path;
    }

    public  String[] getType() {
        return type;
    }

    public  void setType(String[] type) {
        this.type = type;
    }
}
