package cn.xyz.io.message.impl;

import cn.xyz.io.message.IMessageSender;
import cn.xyz.io.message.MessageSendUtils;
import cn.xyz.io.message.AbstractMessageProcessor;

import java.util.Map;

/**
 * @author zhangpengyue
 * @date 2018/8/12
 */
public class MessageSenderImpl implements IMessageSender {

    /**
     * 发送消息
     *
     * @param msgType 消息类型
     * @param keyMap  消息发送的key
     */
    @Override
    public void sendMessage(int msgType, Map<String, Object> keyMap) {
        AbstractMessageProcessor processor = MessageSendUtils.getProcessor(String.valueOf(msgType));
        processor.sendMsg(keyMap);
    }

    /**
     * 校验关键词
     *
     * @param keyMap
     * @return
     */
    private boolean checkKeyWord(Map<String, Object> keyMap) {
        // TODO
        return true;
    }
}
