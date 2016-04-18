package yzh.spring.boot.config;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by youzhihao on 16/4/18.
 * 这个类是真正是用来尝试MVC的各种配置的
 */
@Configuration
public class MvcConfig {


    /**
     * 自定义转换器,这里在保持默认的http转化器的基础上增加一个object对json转化器
     */
    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        HttpMessageConverters converters = new HttpMessageConverters(jsonConverter);
        return converters;
    }
}
