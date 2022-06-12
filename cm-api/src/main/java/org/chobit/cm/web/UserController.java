package org.chobit.cm.web;

import org.chobit.cm.common.entity.User;
import org.chobit.cm.service.UserService;
import org.chobit.cm.spring.ResponseWrapperBody;
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
        return userService.get(id);
    }


    @GetMapping("/get/{username}")
    public User getByUsername(@PathVariable("username") String username) {
        return userService.getByUsername(username);
    }


    @PostMapping("")
    public Long insert(@RequestBody User user) {
        return userService.insert(user);
    }



    @PutMapping("")
    public boolean update(@RequestBody User user){
        return userService.update(user);
    }


    @PostMapping("/batch")
    public int batchInsert(@RequestBody List<User> users){
        return userService.batchInsert(users);
    }


    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id")Long id){
        return userService.delete(id);
    }

}
