package yzh.spring.boot.redis;

/**
 * Created by youzhihao on 16/5/13.
 * 自定义一个redis消息处理类,由代理类MessageListenerAdapter来调用
 */
public class CustomRedisMessageHandler {

    public void handleMessage(String text, String channelorPattern) {
        System.out.println(text);
        System.out.println(channelorPattern);
    }

}
