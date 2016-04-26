package main.java.yzh.spring.boot.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by youzhihao on 16/4/9.
 * 实现ApplicationRunner接口,当boot启动后,这个类的run方法会运行一次
 */
@Component
public class MyApplicationRunner implements ApplicationRunner{
    public void run(ApplicationArguments args) throws Exception {
       System.out.println("ApplicationRunner");
    }
}
