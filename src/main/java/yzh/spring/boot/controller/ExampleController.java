package main.java.yzh.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import main.java.yzh.spring.boot.config.JDBCSettings;
import main.java.yzh.spring.boot.editor.CustomListPropertyEditor;
import main.java.yzh.spring.boot.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by youzhihao on 16/4/3.
 */
@Controller
public class ExampleController {
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(List.class, new CustomListPropertyEditor());
    }
    @Autowired
    private JDBCSettings jdbcSettings;
    //这里测试@profile注解
    //@Autowired
    //private User user;
    //这里使用占位符,获取spring-boot配置的属性值,这个属性可能是启动参数,也可能是application.properties里的属性,也可能是通过
    @Value("${name}")
    private String name;

    @RequestMapping("/123")
    @ResponseBody
    String world() {
        return name;
    }

    @ResponseBody
    @RequestMapping("/jdbc")
    String jdbc() {
        String str = new String("尤智浩");
        return str;
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
    //测试object转json,在配置里增加了一个MappingJackson2HttpMessageConverter
    @RequestMapping("/object2Json")
    @ResponseBody
    Map<String, User> object2Json() {
        Map<String, User> map = new HashMap<String, User>();
        User user = new User();
        user.setUserName("尤智浩");
        user.setUserAccount("xpylq");
        user.setUserPassword("123456");
        map.put("user", user);
        return map;
    }

    @RequestMapping("/addUser")
    @ResponseBody
    User addUser(User user) {
        return user;
    }


}
