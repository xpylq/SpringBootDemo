package yzh.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        template.convertAndSend("channel-1", message);
        return "开一个redis-cli来接受频道消息!!!";
    }

    /**
     * 手动的redis事物
     */
    @RequestMapping("/manualTransacation")
    @ResponseBody
    public String manualTransacation() {
        StringBuilder sb = new StringBuilder();
        //SessionCallback可以保证execute中的所有操作都在一个redisconnection中,详见该类api文档
        //在sdr1.1版本之后,默认的转化器会把byte数组转化成需要的对象,且会忽略OK等提示,如果需要获得OK等提示,需要将
        //JedisConnectionFactory中的convertPipelineAndTxResults设为false
        List<Object> txResults = template.execute(new SessionCallback<List<Object>>() {
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                //增加监听key:a3
                operations.watch("a3");
                //开启事物
                operations.multi();
                operations.opsForValue().set("a1", "a1");
                operations.opsForValue().set("a2", "a2");
                operations.opsForValue().set("a3", "a3");
                operations.opsForValue().get("a3");
                //执行事物
                return operations.exec();
            }
        });
        //如果监听key后,key被改变,事物则执行失败,返回null
        if (txResults == null) {
            return "事物执行失败";
        }
        for (Object result : txResults) {
            sb.append(result.toString() + "</br>");
        }
        return sb.toString();
    }

    @RequestMapping("/manualPipelining")
    @ResponseBody
    public String manualPipelining() {
        //第一种方法,使用execute方法,pipeline参数设为true,则会使用pipelining一次性发送所有命令,
        //因为是一次性发送,所以get等命令不会得到任何返回值
        String result = template.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
                stringRedisConn.set("b1", "b1");
                stringRedisConn.set("b2", "b2");
                stringRedisConn.set("b3", "b3");
                stringRedisConn.get("b3");
                //使用管道时,这个值是没有意义的,所以返回null
                return null;
            }
        }, false, true);
        List<Object> result1 = template.executePipelined(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
                stringRedisConn.set("c1", "c1");
                stringRedisConn.set("c2", "c2");
                stringRedisConn.set("c3", "c3");
                stringRedisConn.get("b3");
                //使用管道时,这个值是没有意义的,所以返回null
                return null;
            }
        });
        for (Object o : result1) {
            System.out.println(o);
        }
        return result;
    }

}