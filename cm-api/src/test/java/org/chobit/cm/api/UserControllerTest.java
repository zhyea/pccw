package org.chobit.cm.api;

import com.fasterxml.jackson.core.type.TypeReference;
import org.chobit.cm.ApiTestBase;
import org.chobit.cm.common.entity.User;
import org.chobit.cm.common.model.PageReq;
import org.chobit.cm.common.model.PageResult;
import org.chobit.cm.tools.DES;
import org.chobit.cm.tools.InstanceGenerator;
import org.chobit.common.model.ParamMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;


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
        user.setUsername("robinZhang02");
        user.setEmail("robin@zhyea.com");
        user.setPassword("robinPwd");

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
    @Order(2)
    public void update() {
        User user = new User();
        user.setId(1L);
        user.setName("robin-1");
        user.setUsername("robinZhyea");
        user.setPassword("test11112");
        user.setEmail("robin@zhyea.com");

        String json = testPut("", null, user);

        Boolean r = fromResult(json, Boolean.class);
        Assertions.assertTrue(r);
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
    public void batchInsert() {
        List<User> users = InstanceGenerator.genUsers(4);
        String json = testPost("/batch", users);

        int count = fromResult(json, Integer.class);
        Assertions.assertEquals(4, count);
    }


    @Test
    @Order(3)
    public void findInPage() {
        PageReq req = new PageReq();
        req.setPageSize(6);
        req.setPageNo(1);
        req.setKeywords("rob");

        String json = testPost("/data", req);
        String json2 = testPost("/data", req);

        PageResult<User> result = fromResult(json, new TypeReference<PageResult<User>>() {
        });
        Assertions.assertNotNull(result);
    }


    @Test
    @Order(5)
    public void delete() {
        long id = 108L;
        String json = testDelete("/" + id);

        Boolean r = fromResult(json, Boolean.class);
        Assertions.assertNotNull(r);
    }


    @Test
    @Order(6)
    public void verify() {
        String key = DES.encrypt(String.valueOf(1), "两只烤鸭往北走");
        String json = testGet("/verify/" + key);

        User r = fromResult(json, User.class);
        Assertions.assertNotNull(r);
    }


    @Test
    @Order(6)
    public void check() {
        String username = "robinZhyea";
        String password = "test11112";

        ParamMap<Object> params = new ParamMap<>(2);
        params.put("username", username);
        params.put("password", password);

        String json = testPostForm("/check", null, params);

        User r = fromResult(json, User.class);
        Assertions.assertNotNull(r);
    }

}
