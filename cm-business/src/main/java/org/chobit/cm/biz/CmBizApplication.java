package org.chobit.cm.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
@SpringBootApplication
@ComponentScan("org.chobit.cm.dao")
public class CmBizApplication {


    public static void main(String[] args) {
        SpringApplication.run(CmBizApplication.class, args);
        System.out.println("应用启动成功...");
    }

}
