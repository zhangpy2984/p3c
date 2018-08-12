package cn.xyz.message.template;

import cn.xyz.message.IMessageTemplateChooser;
import cn.xyz.message.MsgTemplate;
import cn.xyz.message.data.UnderwMsgData;

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
