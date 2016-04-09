package yzh.spring.boot.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by youzhihao on 16/4/3.
 */
@RestController
public class Example {
    @RequestMapping("/")
    String home(){
        return "Hello World!";
    }

}
