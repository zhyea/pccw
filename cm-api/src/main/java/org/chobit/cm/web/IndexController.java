package org.chobit.cm.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class IndexController {


    @GetMapping("")
    public String index(@RequestParam("name") String name) {
        return "Hello " + name + "!";
    }

}
