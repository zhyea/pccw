package org.chobit.cm.biz.api;

import org.chobit.cm.biz.ApiTestBase;
import org.chobit.cm.common.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.chobit.cm.biz.tools.InstanceGenerator.genUsers;

/**
 * @author robin
 */
public class UserControllerTest extends ApiTestBase {

    @Override
    protected String parentPath() {
        return "/user";
    }


    @Test
    @Order(0)
    public void insert() {
        User user = new User();
        user.setName("robin");
        user.setUsername("robinZhyea");
        user.setEmail("robin@zhyea.com");

        String json = testPost("", user);

        Long id = fromResult(json, Long.class);
        Assertions.assertTrue(id > 0);
    }


    @Test
    @Order(1)
    public void get() {
        long id = 1L;
        String json = testGet("/" + id);

        User user = fromResult(json, User.class);
        Assertions.assertNotNull(user);
    }


    @Test
    @Order(3)
    public void getByUsername() {
        String username = "robinZhyea";
        String json = testGet("/get/" + username);

        User user = fromResult(json, User.class);
        Assertions.assertNotNull(user);
    }


    @Test
    @Order(2)
    public void update() {
        User user = new User();
        user.setId(1L);
        user.setName("robin-1");
        user.setUsername("robinZhyea");
        user.setEmail("robin@zhyea.com");

        String json = testPut("", null, user);

        Boolean r = fromResult(json, Boolean.class);
        Assertions.assertTrue(r);
    }


    @Test
    @Order(4)
    public void batchInsert() {
        List<User> users = genUsers();
        String json = testPost("/batch", users);

        int count = fromResult(json, Integer.class);
        Assertions.assertEquals(100, count);
    }


    @Test
    @Order(5)
    public void delete() {
        long id = 2L;
        String json = testDelete("/" + id);

        Boolean r = fromResult(json, Boolean.class);
        Assertions.assertNotNull(r);
    }

}
