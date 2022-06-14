package org.chobit.cm.web;

import org.chobit.cm.common.entity.User;
import org.chobit.cm.common.model.PageReq;
import org.chobit.cm.common.model.PageResult;
import org.chobit.cm.service.UserService;
import org.chobit.cm.spring.response.ResponseWrapperBody;
import org.chobit.cm.tools.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author robin
 */
@RestController
@RequestMapping("/user")
@ResponseWrapperBody
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public User get(@PathVariable("id") Long id) {
        Args.checkPositive(id, "ID必须大于0");
        return userService.get(id);
    }


    @GetMapping("/get/{username}")
    public User getByUsername(@PathVariable("username") String username) {
        Args.checkNotBlank(username, "username不能为空");
        return userService.getByUsername(username);
    }


    @PostMapping("/data")
    public PageResult<User> findInPage(@RequestBody PageReq req) {
        Args.checkPositive(req.getPageNo(), "pageNo需要大于0");
        Args.checkPositive(req.getPageSize(), "pageSize需要大于0");
        return userService.findInPage(req);
    }


    @PostMapping("")
    public Long insert(@RequestBody User user) {
        Args.checkNull(user, "用户不能为空");
        Args.checkNotBlank(user.getUsername(), "username不能为空");
        Args.checkNotBlank(user.getEmail(), "邮箱不能为空");
        Args.checkEmail(user.getEmail());
        return userService.insert(user);
    }


    @PutMapping("")
    public boolean update(@RequestBody User user) {
        Args.checkNull(user, "用户不能为空");
        Args.checkPositive(user.getId(), "用户ID大于0");
        Args.checkNotBlank(user.getUsername(), "username不能为空");
        Args.checkNotBlank(user.getEmail(), "邮箱不能为空");
        return userService.update(user);
    }


    @PostMapping("/batch")
    public int batchInsert(@RequestBody List<User> users) {
        Args.checkNull(users, "用户集合不能为空");
        return userService.batchInsert(users);
    }


    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        Args.checkPositive(id, "ID必须大于0");
        return userService.delete(id);
    }


    @GetMapping("/verify/{key}")
    public User verify(@PathVariable("key") String key) {
        Args.checkNotBlank(key, "参数错误");
        return userService.verify(key);
    }


    @PostMapping("/check")
    public User check(@RequestParam("username") String username,
                      @RequestParam("password") String password) {
        Args.checkNotBlank(username, "用户名为空");
        Args.checkNotBlank(password, "密码为空");
        return userService.check(username, password);
    }

}
