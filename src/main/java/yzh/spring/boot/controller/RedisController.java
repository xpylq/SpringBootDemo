package yzh.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by youzhihao on 16/5/12.
 * 针对集成redis的测试类
 */
@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate template;

    /**
     * get方法
     */
    @RequestMapping("/get")
    @ResponseBody
    public String get(@RequestParam String key) {
        return template.opsForValue().get(key);
    }

    /**
     * getAndset方法
     */
    @RequestMapping("/set")
    @ResponseBody
    public String getAndSet(@RequestParam String key, @RequestParam String value) {
        return template.opsForValue().getAndSet(key, value);
    }

    /**
     * 通过template,获取connection从而完全控制redis
     */
    @RequestMapping("/fullControl")
    @ResponseBody
    public String fullControlRedis() {
        StringBuilder sb = template.execute(new RedisCallback<StringBuilder>() {
            public StringBuilder doInRedis(RedisConnection connection) throws DataAccessException {
                StringBuilder sb = new StringBuilder();
                sb.append("当前redis服务器存储大小:" + connection.dbSize() + "</br>");
                //因为是StringRedisTemplate所以connection可以转化成StringRedisConnection
                String name = ((StringRedisConnection) connection).get("name");
                sb.append("键name的值是:" + name + "</br>");
                return sb;
            }
        });
        return sb.toString();
    }

    /**
     * 发送消息到channel
     */
    @RequestMapping("/pubMessage")
    @ResponseBody
    public String pubMessage(@RequestParam String message) {
        return "开一个redis-cli来接受频道消息!!!";
    }

}
