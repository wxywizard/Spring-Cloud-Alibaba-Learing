package com.sailing.vsconfigsvc.sys.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Title:  <br>
 * Description: <br>
 *
 * @author gaowei
 * @date 2017-12-21
 */
@Component
@ConfigurationProperties(prefix = "sys.file")
@Data
public class FileConfig {
    private static String path;
    public static String getPath() {
        return path;
    }

}