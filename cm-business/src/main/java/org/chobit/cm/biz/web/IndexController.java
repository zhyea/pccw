package org.chobit.cm.biz.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class IndexController {


    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }


}
