package org.chobit.cm.biz;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.chobit.cm.biz.tools.BeanKit;
import org.chobit.common.json.LocalDateTimeModule;
import org.chobit.common.utils.JsonKit;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author robin
 */
@EnableEurekaClient
@EnableCaching
@SpringBootApplication
@ComponentScan({"org.chobit.cm"})
@MapperScan({"org.chobit.cm"})
public class CmBizApplication {


    public static void main(String[] args) {
        prepare();
        ApplicationContext ctx = SpringApplication.run(CmBizApplication.class, args);
        BeanKit.init(ctx);
        System.out.println("应用启动成功...");
    }



    private static void prepare() {
        JsonKit.mapper().registerModule(new LocalDateTimeModule());
    }


    @Bean
    public ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        mapper.registerModule(new LocalDateTimeModule());

        return mapper;
    }
}
