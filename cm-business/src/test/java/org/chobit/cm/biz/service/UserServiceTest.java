package org.chobit.cm.biz.service;

import org.chobit.cm.biz.TestBase;
import org.chobit.cm.common.entity.User;
import org.chobit.common.tools.ShortCode;
import org.chobit.common.utils.StrKit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;


public class UserServiceTest extends TestBase {


    @Autowired
    private UserService userService;


    @Test
    @Order(0)
    public void insert() {
        User user = new User();
        user.setName("robin");
        user.setUsername("robinZhang");
        user.setEmail("robin@zhyea.com");

        Long id = userService.insert(user);
        System.out.println(id);
        Assertions.assertTrue(id > 0);
    }


    @Test
    @Order(1)
    public void get() {
        User user = userService.get(1L);
        System.out.println(user);
        Assertions.assertNotNull(user);
    }


    @Test
    public void batchInsert() {
        List<User> users = newUsers();
        int count = userService.batchInsert(users);
        System.out.println(count);
        Assertions.assertEquals(100, count);
    }


    private List<User> newUsers() {
        List<User> users = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            User u = new User();
            u.setUsername(ShortCode.gen());
            u.setEmail("robin" + i + "@zhyea.com");
            u.setName("robin-" + StrKit.format("00", i));
            users.add(u);
        }
        return users;
    }

}
