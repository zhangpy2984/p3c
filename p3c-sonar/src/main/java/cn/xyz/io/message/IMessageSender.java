package cn.xyz.io.message;

import java.util.Map;

/**
 * @author zhangpengyue
 * @date 2018/8/12
 */
public interface IMessageSender {
    /**
     * 发送消息
     *
     * @param msgType 消息类型
     * @param keyMap 消息发送的key
     */
    void sendMessage(int msgType, Map<String, Object> keyMap);
}
