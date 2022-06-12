package org.chobit.cm.service.feign;

import org.chobit.cm.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("user")
public interface UserFeignApi {


    /**
     * 获取用户记录
     *
     * @param id 记录ID
     * @return 用户记录
     */
    @GetMapping("/{id}")
    User get(@PathVariable("id") Long id);


    /**
     * 根据用户名获取用户记录
     *
     * @param username 用户名
     * @return 用户记录
     */
    @GetMapping("/get/{username}")
    User getByUsername(@PathVariable("username") String username);


    /**
     * 新增用户记录
     *
     * @param user 用户实例
     * @return 新增记录ID
     */
    @PostMapping("")
    Long insert(@RequestBody User user);


    /**
     * 更新用户记录
     *
     * @param user 用户实例
     * @return 是否更新成功
     */
    @PutMapping("")
    boolean update(@RequestBody User user);


    /**
     * 批量新增用户
     *
     * @param users 用户集合
     * @return 新增用户数量
     */
    @PostMapping("/batch")
    int batchInsert(@RequestBody List<User> users);


    /**
     * 删除用户记录
     *
     * @param id 记录ID
     * @return 是否删除成功
     */
    @DeleteMapping("/{id}")
    boolean delete(@PathVariable("id") Long id);
}
