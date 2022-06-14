package org.chobit.cm.service;

import org.chobit.cm.TestBase;
import org.chobit.cm.common.entity.User;
import org.chobit.cm.service.feign.UserFeignApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserFeignApiTest extends TestBase {


    @Autowired
    private UserFeignApi userFeignApi;


    @Test
    public void get(){
        User user = userFeignApi.get(10010L);
        System.out.println(user);
        Assertions.assertNotNull(user);
    }



}
