package yzh.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import yzh.spring.boot.domain.User;

/**
 * Created by youzhihao on 16/4/10.
 */
@Configuration
/**  @Profile这个注释的作用是根据spring.profiles.active属性来指定那些配置是需要加载的
 *   spring.profiles.active这个配置可以是在启动参数里配置,也可以在application.properties里配置
 *
 * */
@Profile("test")
public class TestConfig {
    @Bean
    public User getUser()
    {
        User user=new User();
        user.setUserName("youzhihao");
        user.setUserAccount("xpylq");
        user.setUserPassword("123456");
        return user;
    }
}
