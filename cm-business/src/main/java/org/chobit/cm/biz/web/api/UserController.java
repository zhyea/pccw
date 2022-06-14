package org.chobit.cm.biz.web.api;

import org.chobit.cm.biz.service.UserService;
import org.chobit.cm.common.constants.UserState;
import org.chobit.cm.common.entity.User;
import org.chobit.cm.common.model.PageReq;
import org.chobit.cm.common.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author robin
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public User get(@PathVariable("id") Long id) {
        return userService.get(id);
    }


    @GetMapping("/get/{username}")
    public User getByUsername(@PathVariable("username") String username) {
        return userService.getByUsername(username);
    }


    @PostMapping("/data")
    public PageResult<User> findInPage(@RequestBody PageReq req) {
        return userService.findInPage(req);
    }


    @PostMapping("")
    public Long insert(@RequestBody User user) {
        return userService.insert(user);
    }


    @PutMapping("")
    public boolean update(@RequestBody User user) {
        return userService.update(user);
    }


    @PostMapping("/batch")
    public int batchInsert(@RequestBody List<User> users) {
        return userService.batchInsert(users);
    }


    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }


    @PutMapping("/changeState")
    public boolean updateState(@RequestParam("id") Long id,
                               @RequestParam("state") UserState state) {
        return userService.updateState(id, state);
    }


    @GetMapping("/find-by-state/{state}")
    public List<User> findByState(@PathVariable("state") UserState state) {
        return userService.findByState(state);
    }

}
