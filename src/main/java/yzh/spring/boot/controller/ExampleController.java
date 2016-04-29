package yzh.spring.boot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yzh.spring.boot.config.JDBCSettings;
import yzh.spring.boot.config.editor.CustomDatePropertyEditor;
import yzh.spring.boot.domain.User;

import java.util.*;

/**
 * Created by youzhihao on 16/4/3.
 */
@Controller
public class ExampleController {
    /**自定义一个请求参数转化器,@InitBinder注解中的value值表示只对具体名字的参数对象进行转化*/
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        /**自定义一个date类型的参数转化器*/
        webDataBinder.registerCustomEditor(Date.class, new CustomDatePropertyEditor());
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

    @RequestMapping("/convertString2Date")
    @ResponseBody
    Date convertString2Date(Date date) {
        return date;
    }

    @RequestMapping("/paramsConvert2List")
    @ResponseBody
    public List paramsConvert2List(@RequestBody  ArrayList<String> list) {
        return list;
    }


}
