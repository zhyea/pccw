package org.chobit.cm.biz.service;

import org.chobit.cm.biz.TestBase;
import org.chobit.cm.common.entity.User;
import org.chobit.cm.common.model.PageReq;
import org.chobit.cm.common.model.PageResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.chobit.cm.biz.tools.InstanceGenerator.genUsers;


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
        List<User> users = genUsers();
        int count = userService.batchInsert(users);
        System.out.println(count);
        Assertions.assertEquals(100, count);
    }


    @Test
    public void findInPage(){
        PageReq req = new PageReq();
        req.setPageSize(6);
        req.setPageNo(1);
        req.setKeywords("rob");

        PageResult<User> r = userService.findInPage(req);
        Assertions.assertNotNull(r);
    }


}
