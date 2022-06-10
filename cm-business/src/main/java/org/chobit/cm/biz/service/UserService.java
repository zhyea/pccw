package org.chobit.cm.biz.service;


import org.chobit.cm.biz.tools.MyBatisBatchOperator;
import org.chobit.cm.common.entity.User;
import org.chobit.cm.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author robin
 */
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
        return MyBatisBatchOperator.operate(users, UserMapper.class, (user, mapper) -> mapper.insert(user));
    }


    /**
     * 批量新增用户
     * (小规模用户用这个，大量用户用前者)
     *
     * @param users 用户集合
     * @return 新增用户数
     */
    public int batchInsert2(List<User> users) {
        return userMapper.batchInsert(users);
    }


    public Long insert(User user) {
        userMapper.insert(user);
        return user.getId();
    }


    public boolean update(User user) {
        return userMapper.update(user);
    }


    public boolean delete(Long id) {
        return userMapper.delete(id);
    }


}
