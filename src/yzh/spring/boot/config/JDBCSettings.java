package yzh.spring.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by youzhihao on 16/4/9.
 * 这个类是用来演示,@ConfigurationProperties注解类,这个类可以自动从属性文件中注入属性值
 * prefix是这个属性值的前缀,例如jdbc.url   前缀就是jdbc
 */
@Component
@ConfigurationProperties(prefix = "url")
public class JDBCSettings {
    public   String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String show()
    {
        return this.url;
    }
}
