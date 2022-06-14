package org.chobit.cm.biz.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.chobit.cm.biz.tools.MyBatisBatchOperator;
import org.chobit.cm.common.constants.UserState;
import org.chobit.cm.common.entity.User;
import org.chobit.cm.common.model.PageReq;
import org.chobit.cm.common.model.PageResult;
import org.chobit.cm.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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


    @Cacheable(key = "'findInPage:' + #req", unless = "#result == null")
    public PageResult<User> findInPage(PageReq req) {
        String key = "%" + req.getKeywords() + "%";
        PageHelper.startPage(req.getPageNo(), req.getPageSize(), req.getSort()).count(true);
        List<User> data = userMapper.findByKey(key);
        PageInfo<User> r = new PageInfo<>(data);
        PageResult<User> result = new PageResult<>();

        result.setPageNo(req.getPageNo());
        result.addData(data);
        result.setTotal(r.getTotal());
        result.setTotalPage(r.getPageSize());
        return result;
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


    @CacheEvict(allEntries = true)
    public boolean update(User user) {
        return userMapper.update(user);
    }


    @CacheEvict(allEntries = true)
    public boolean delete(Long id) {
        return userMapper.delete(id);
    }


    @CacheEvict(allEntries = true)
    public boolean updateState(Long id, UserState state) {
        return userMapper.updateState(id, state.code);
    }


    public List<User> findByState(UserState state) {
        return userMapper.findByState(state.code);
    }

}
