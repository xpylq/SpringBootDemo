package yzh.spring.boot.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by youzhihao on 16/4/18.
 * 这个类是真正是用来尝试MVC的各种配置的
 */
@Configuration
/**使用@ImportResource注解来导入xml配置文件,这里导入一个spring-data-redis(sdr)配置文件*/
@ImportResource("classpath:config/spring-data-redis.xml")
public class MvcConfig {


    /**
     * 自定义转换器,这里在保持默认的http转化器的基础上增加一个object对json转化器,默认的配置是存在这个
     * 转化器的,如果需要自己定制的话，需要自己制定一系列的converters
     */
//    @Bean
//    public HttpMessageConverters customConverters() {
//        HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        HttpMessageConverters converters = new HttpMessageConverters(jsonConverter, stringHttpMessageConverter);
//        return converters;
//    }

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

    /***
     * 这里注入一个WebMvcConfigurer接口的包装类WebMvcConfigurerAdapter，可以自己定制自己的web配置，上面的httpMessageConvertor
     * 可以在这里一并定制了。DelegatingWebMvcConfiguration复合类中的WebMvcConfigurerComposite里类中存在的一个集合WebMvcConfigurer，
     * 自动注入所有装配的WebMvcConfigurer，并且叠加这些WebMvcConfigurer配置，而不是覆盖，顾如果你使用@EnableAutoConfiguration注解
     * 会默认装配一个WebMvcConfigurer类，这里面包含一系列默认的组件，自己再增加一个则是叠加效果，如果想覆盖，则使用@EnableWebMvc配置
     * 则不会生成默认的WebMvcConfigurer
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter() {

            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                //这里没必要加,因为EnableAutoConfiguration注解的配置都已经加过了这些转化器
                //converters.add(new MappingJackson2HttpMessageConverter());
                //增加一个自定义的StringHttpMessageConverter,因为注解自动加的默认是ISO-8859-1编码,也就是如果是以String返回到客户端的
                //字符串会默认加上ISO-8859-1编码,中文会出现乱码,这里增加一个,用utf-8
                //converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
            }

            /**
             * 增加一个静态文件映射，感觉没什么用，用默认的/**即可
             */
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                //registry.addResourceHandler("/public1/**").addResourceLocations();
            }
        };
        return webMvcConfigurerAdapter;
    }

}
