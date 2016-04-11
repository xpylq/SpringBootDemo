package yzh.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yzh.spring.boot.config.JDBCSettings;
import yzh.spring.boot.model.User;

/**
 * Created by youzhihao on 16/4/3.
 */
@RestController
public class ExampleController {
    @Autowired
    private JDBCSettings jdbcSettings;
    //这里测试@profile注解
    //@Autowired
    //private User user;
    //这里使用占位符,获取spring-boot配置的属性值,这个属性可能是启动参数,也可能是application.properties里的属性,也可能是通过
    @Value("${name}")
    private String name;

    @RequestMapping("/")
    String world() {
        return name;
    }

    @RequestMapping("/jdbc")
    String jdbc() {
        return jdbcSettings.getUrl();
    }
//    这里测试@profile注解
//    @RequestMapping("/getUser")
//    String getUser()
//    {
//        StringBuilder sb=new StringBuilder();
//        sb.append(user.toString());
//        sb.append("<br/>");
//        sb.append(user.getUserName());
//        sb.append("<br/>");
//        sb.append(user.getUserAccount());
//        sb.append("<br/>");
//        sb.append(user.getUserPassword());
//        return sb.toString();
//    }


}
