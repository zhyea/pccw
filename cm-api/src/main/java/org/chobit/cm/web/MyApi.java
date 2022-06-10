package org.chobit.cm.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author robin
 */
@RestController
@RequestMapping(value = "/api/zhyea")
public class MyApi {

    @GetMapping("/{id}")
    public int get(@PathVariable("id") int id) {
        System.out.println(1);
        return id;
    }

}