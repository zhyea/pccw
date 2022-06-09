package org.chobit.cm.biz.service;


import org.chobit.cm.common.entity.User;
import org.chobit.cm.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@CacheConfig(cacheNames = "userSer")
@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    @Cacheable(key = "'get:' + #id", unless = "#result == null")
    public User get(Long id) {
        return userMapper.get(id);
    }


    @Cacheable(key = "'getByUsername:' + #username", unless = "#result == null")
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }


    public int batchInsert(List<User> users) {
        return userMapper.batchInsert(users);
    }


    public Long insert(User user) {
        userMapper.insert(user);
        return user.getId();
    }


    public boolean update(User user) {
        return userMapper.update(user);
    }


}
