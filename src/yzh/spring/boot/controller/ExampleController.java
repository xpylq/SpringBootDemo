package yzh.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yzh.spring.boot.config.JDBCSettings;

/**
 * Created by youzhihao on 16/4/3.
 */
@RestController
public class ExampleController {
    @Autowired
    private JDBCSettings jdbcSettings;
    //这里使用占位符,获取spring-boot配置的属性值,这个属性可能是启动参数,也可能是application.properties里的属性,也可能是通过
    @Value("${name}")
    private String name;
    @RequestMapping("/")
    String world(){
        return name;
    }
    @RequestMapping("/index")
    String index(){
        return "index!";
    }
    @RequestMapping("/home")
    String home(){
        return "home!";
    }
    @RequestMapping("/home1")
    String home1(){
        return "home1!";
    }
    @RequestMapping("/jdbc")
    String jdbc(){
       // System.out.println(1111);
        //return jdbcSettings.getUrl();
        return "2345555";
    }

}
