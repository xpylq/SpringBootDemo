package yzh.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by youzhihao on 16/4/9.
 * spring boot启动类
 */
/**Spring Boot auto-configuration attempts to automatically configure your Spring application based on the jar dependencies that you have added.
 * 在一个项目只能有一个EnableAutoConfiguration,他会自动根据class下的jar包做一些配置.
 * */
@EnableAutoConfiguration
/**自动扫描所有子包下的spring注解bean,这个注解是包含@Configure注解的,所以不需要再加一个*/
@ComponentScan
/**项目唯一的启动类,这个类官方不建议放在默认的default包下,可能会出问题*/
public class Application {
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class,args);
    }
}
