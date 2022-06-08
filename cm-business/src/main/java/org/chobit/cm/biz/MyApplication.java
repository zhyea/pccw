package org.chobit.cm.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MyApplication {


    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
        System.out.println("应用启动成功...");
    }

}
