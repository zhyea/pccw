package org.chobit.cm.biz;


import org.chobit.cm.biz.tools.BeanKit;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestBase implements ApplicationContextAware {


    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        BeanKit.init(context);
    }
}
