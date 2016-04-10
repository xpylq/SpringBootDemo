package yzh.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by youzhihao on 16/4/9.
 * spring boot启动类
 */

/**
 * Spring Boot auto-configuration attempts to automatically configure your Spring application based on the jar dependencies that you have added.
 * 在一个项目只能有一个EnableAutoConfiguration,他会自动根据class下的jar包做一些配置.可以在启动参数中加入--debug,在console中打印所有自动完成配置
 */
@EnableAutoConfiguration
/**自动扫描所有子包下的spring注解bean,这个注解是包含@Configure注解的,所以不需要再加一个*/
@ComponentScan
/**@SpringBootApplication 这个注解是以下三个注解之和@Configuration @EnableAutoConfiguration @ComponentScan*/
@EnableConfigurationProperties
/**项目唯一的启动类,这个类官方不建议放在默认的default包下,可能会出问题*/
public class Application {
    public static void main(String[] args) {
        //  默认的启动方式
        SpringApplication.run(Application.class, args);
        //可自定义的启动方式
        //SpringApplication sp=new SpringApplication(Application.class);
        //这里可以配置一些需要在spring application启动之前完成启动的监听器
        //sp.addListeners(null);
    }
}
