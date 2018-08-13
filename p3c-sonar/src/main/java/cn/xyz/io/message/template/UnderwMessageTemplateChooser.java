package cn.xyz.io.message.template;

import cn.xyz.io.message.IMessageTemplateChooser;
import cn.xyz.io.message.MsgTemplate;
import cn.xyz.io.message.data.UnderwMsgData;

import java.util.List;

/**
 * @author zhangpengyue
 * @date 2018/8/13
 */
public class UnderwMessageTemplateChooser implements IMessageTemplateChooser<UnderwMsgData> {
    @Override
    public List<MsgTemplate<UnderwMsgData>> chooseTemplate(UnderwMsgData data) {
        return null;
    }
}
