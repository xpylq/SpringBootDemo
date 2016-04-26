package main.java.yzh.spring.boot.config;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.Charset;

/**
 * Created by youzhihao on 16/4/18.
 * 这个类是真正是用来尝试MVC的各种配置的
 */
@Configuration
public class MvcConfig {


    /**
     * 自定义转换器,这里在保持默认的http转化器的基础上增加一个object对json转化器,默认的配置是存在这个
     * 转化器的,如果需要自己定制的话，需要自己制定一系列的converters
     */
    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        HttpMessageConverters converters = new HttpMessageConverters(jsonConverter, stringHttpMessageConverter);
        return converters;
    }

    /**
     * 这个指定请求的编码方式的filter在默认配置里是存在的，详见HttpEncodingAutoConfiguration类，如果自己定制web的话需要自己加上
     */
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        registrationBean.setFilter(filter);

        return filter;
    }
}
