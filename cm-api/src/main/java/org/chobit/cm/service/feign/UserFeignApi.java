package org.chobit.cm.service.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.chobit.cm.common.constants.UserState;
import org.chobit.cm.common.entity.User;
import org.chobit.cm.common.model.PageReq;
import org.chobit.cm.common.model.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "cm-business")
public interface UserFeignApi {


    /**
     * 获取用户记录
     *
     * @param id 记录ID
     * @return 用户记录
     */
    @GetMapping("/user/{id}")
    @CircuitBreaker(name = "userFeignApi", fallbackMethod = "fbGet")
    User get(@PathVariable("id") Long id);

    default User fbGet(Long id, Throwable t) {
        return new User();
    }


    /**
     * 根据用户名获取用户记录
     *
     * @param username 用户名
     * @return 用户记录
     */
    @GetMapping("/user/get/{username}")
    User getByUsername(@PathVariable("username") String username);


    /**
     * 分页查询用户数据
     *
     * @param req 请求实例
     * @return 查询结果
     */
    @PostMapping("/user/data")
    PageResult<User> findInPage(@RequestBody PageReq req);


    /**
     * 新增用户记录
     *
     * @param user 用户实例
     * @return 新增记录ID
     */
    @PostMapping("/user")
    Long insert(@RequestBody User user);


    /**
     * 更新用户记录
     *
     * @param user 用户实例
     * @return 是否更新成功
     */
    @PutMapping("/user")
    boolean update(@RequestBody User user);


    /**
     * 批量新增用户
     *
     * @param users 用户集合
     * @return 新增用户数量
     */
    @PostMapping("/user/batch")
    int batchInsert(@RequestBody List<User> users);


    /**
     * 删除用户记录
     *
     * @param id 记录ID
     * @return 是否删除成功
     */
    @DeleteMapping("/user/{id}")
    boolean delete(@PathVariable("id") Long id);


    /**
     * 更新状态
     *
     * @param id    记录ID
     * @param state 状态
     * @return 是否更新成功
     */
    @PutMapping("/user/changeState")
    boolean updateState(@RequestParam("id") Long id,
                        @RequestParam("state") UserState state);


    /**
     * 根据状态查询用户记录
     * @param state 状态
     * @return 用户记录
     */
    @GetMapping("/user/find-by-state/{state}")
    List<User> findByState(@PathVariable("state") UserState state);
}
