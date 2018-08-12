package cn.xyz.message;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息发送工具类
 *
 * @author zhangpengyue
 * @date 2018/8/12
 */
public class MessageSendUtils {

    private static final Map<String, AbstractMessageProcessor> messageProcessorMap = new HashMap<>();

    /**
     * 添加消息处理类,这个方法在系统初始化的时候调用
     *
     * @param msgType
     * @param processor
     */
    public static void addProcessor(String msgType, AbstractMessageProcessor processor) {
        messageProcessorMap.put(msgType, processor);
    }

    /**
     * 取一个消息处理类
     *
     * @param msgType
     * @return
     */
    public static AbstractMessageProcessor getProcessor(String msgType) {
        return messageProcessorMap.get(msgType);
    }
}
