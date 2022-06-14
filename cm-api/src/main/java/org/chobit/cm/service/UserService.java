package org.chobit.cm.service;

import org.chobit.cm.common.constants.UserState;
import org.chobit.cm.common.entity.User;
import org.chobit.cm.common.model.PageReq;
import org.chobit.cm.common.model.PageResult;
import org.chobit.cm.service.feign.UserFeignApi;
import org.chobit.cm.tools.Args;
import org.chobit.cm.tools.DES;
import org.chobit.cm.tools.MailSender;
import org.chobit.common.codec.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.chobit.cm.common.constants.UserState.INIT;
import static org.chobit.cm.common.constants.UserState.VERIFIED;
import static org.chobit.common.utils.StrKit.isBlank;
import static org.chobit.common.utils.StrKit.toLong;

@Service
@CacheConfig(cacheNames = "userSer")
public class UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    private final UserFeignApi userApi;


    @Autowired
    public UserService(UserFeignApi userApi) {
        this.userApi = userApi;
    }


    @Cacheable(key = "'get:' + #id", unless = "#result == null")
    public User get(Long id) {
        return userApi.get(id);
    }


    @Cacheable(key = "'getByUsername:' + #username", unless = "#result == null")
    public User getByUsername(String username) {
        return userApi.getByUsername(username);
    }


    @Cacheable(key = "'findInPage:' + #req", unless = "#result == null")
    public PageResult<User> findInPage(PageReq req) {
        return userApi.findInPage(req);
    }


    @CacheEvict(allEntries = true)
    public int batchInsert(List<User> users) {
        return userApi.batchInsert(users);
    }


    @CacheEvict(allEntries = true)
    public Long insert(User user) {
        User his = userApi.getByUsername(user.getUsername());
        Args.checkNull(his, "用户名" + user.getUsername() + "已存在");

        user.setPassword(MD5.encode(user.getPassword() + SECRET_KEY));
        Long id = userApi.insert(user);
        user.setId(id);

        inform(user);
        userApi.updateState(user.getId(), UserState.INFORMED);
        return id;
    }


    @CacheEvict(allEntries = true)
    public boolean update(User user) {
        return userApi.update(user);
    }


    @CacheEvict(allEntries = true)
    public boolean delete(Long id) {
        return userApi.delete(id);
    }


    public void reInform() {
        List<User> users = userApi.findByState(INIT);
        for (User user : users) {
            inform(user);
            userApi.updateState(user.getId(), UserState.INFORMED);
        }
    }


    public User check(String username, String password) {
        password = MD5.encode(password + SECRET_KEY);
        User user = userApi.getByUsername(username);
        if (null == user) {
            return null;
        }
        if (!password.equals(user.getPassword())) {
            return null;
        }
        return user;
    }


    private static final String SECRET_KEY = "两只烤鸭往北走";

    @CacheEvict(allEntries = true)
    public User verify(String key) {
        String idStr = DES.decrypt(key, SECRET_KEY);
        if (isBlank(idStr)) {
            return null;
        }
        long id = toLong(idStr);
        if (id <= 0) {
            return null;
        }
        User user = userApi.get(id);
        if (null == user) {
            return null;
        }
        userApi.updateState(user.getId(), VERIFIED);
        return user;
    }


    public void inform(User user) {
        // 对用户ID加密，避免恶意尝试
        String key = DES.encrypt(String.valueOf(user.getId()), SECRET_KEY);
        // 跳到前端，由前端使用加密后的ID异步调用verify接口完成验证
        String urlVerify = "https://cms.chobit.org/portal/" + key;
        String content = String.format("你好：%s！欢迎注册。请点击<a href='%s'>按钮</a>完成验证", user.getName(), urlVerify);
        String title = "欢迎注册CMS";
        MailSender.send(title, content, user.getEmail());
    }

}
