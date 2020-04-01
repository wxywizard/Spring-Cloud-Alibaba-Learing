package com.sailing.vsconfigsvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.sailing.vsconfigsvc.*.mapper")
public class VsConfigSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(VsConfigSvcApplication.class, args);
    }

}
