package org.chobit.cm;

import org.chobit.cm.config.ResponseBodyWrapFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author robin
 */
@SpringBootApplication
public class MyApplication {


    public static void main(String[] args) {


        SpringApplication.run(MyApplication.class, args);
    }


    @Bean
    public ResponseBodyWrapFactoryBean getResponseBodyWrap() {
        return new ResponseBodyWrapFactoryBean();
    }
}
