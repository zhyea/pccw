package org.chobit.cm.service;

import org.chobit.cm.common.entity.User;
import org.chobit.cm.service.feign.UserFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


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


    public int batchInsert(List<User> users) {
        return userApi.batchInsert(users);
    }


    public Long insert(User user) {
        return userApi.insert(user);
    }


    @CacheEvict(allEntries = true)
    public boolean update(User user) {
        return userApi.update(user);
    }

    @CacheEvict(allEntries = true)
    public boolean delete(Long id) {
        return userApi.delete(id);
    }

}
