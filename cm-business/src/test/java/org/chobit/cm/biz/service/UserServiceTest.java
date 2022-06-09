package org.chobit.cm.biz.service;

import org.chobit.cm.biz.TestBase;
import org.chobit.cm.common.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceTest extends TestBase {


    @Autowired
    private UserService userService;


    @Test
    public void get() {
        User user = userService.get(1L);
        Assert.assertNotNull(user);
    }

}
